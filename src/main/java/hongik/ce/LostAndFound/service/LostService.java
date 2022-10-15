package hongik.ce.LostAndFound.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.config.ResponseStatus;
import hongik.ce.LostAndFound.domain.dto.lost.list.DetailLostInfoRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentListRes;
import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.LostComment;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.repository.JpaCategoryRepository;
import hongik.ce.LostAndFound.repository.JpaLostCommentRepository;
import hongik.ce.LostAndFound.repository.JpaLostRepository;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class LostService {

    private final JpaLostRepository jpaLostRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaLostCommentRepository jpaLostCommentRepository;


    public List<LostListRes> getLostList(){
        List<Lost> list = jpaLostRepository.findAll();
        List<LostListRes> result = new ArrayList<>();
        for( Lost l : list){
            result.add(new LostListRes(l));
        }
        return result;
    }

    public List<LostListRes> getLostList(String category, String year, String month){

        Category c = jpaCategoryRepository.findByCategory(category);
        String yearMonth = year + "-" + month;

        List<Lost> list = jpaLostRepository.findByCategoryAndYearMonth(c,yearMonth);
        List<LostListRes> result = new ArrayList<>();
        for(Lost l : list){
            result.add(new LostListRes(l));
        }
        return result;
    }

    public List<LostListRes> getLostListByCategory(String category){
        Category c = jpaCategoryRepository.findByCategory(category);
        List<Lost> list = jpaLostRepository.findByCategory(c);
        List<LostListRes> result = new ArrayList<>();
        for(Lost l : list){
            result.add(new LostListRes(l));
        }
        return result;
    }

    public List<LostListRes> getLostListByYearMonth(String year, String month){
        String yearMonth = year + "-" + month;
        List<Lost> list = jpaLostRepository.findByYearMonth(yearMonth);
        List<LostListRes> result = new ArrayList<>();
        for(Lost l : list){
            result.add(new LostListRes(l));
        }
        return result;
    }



    public LostRegisterRes registerLost(LostRegisterReq lostRegisterReq)throws BaseException {
        Long userId = lostRegisterReq.getUserId();
        String category = lostRegisterReq.getCategory();
        String title = lostRegisterReq.getTitle();
        String contents = lostRegisterReq.getContents();

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(now);

        User user;
        try{
            user = jpaUserRepository.findByUserId(userId);
        }catch(Exception e){
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        Category categoryResult = jpaCategoryRepository.findByCategory(category);

        if(user==null){
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        Lost result = jpaLostRepository.save(new Lost(user,categoryResult,title,contents,date));
        return new LostRegisterRes(result);
    }

    public DetailLostInfoRes findByLostId(Long lostId) throws BaseException{
        Lost lost;
        try{
            lost = jpaLostRepository.findByLostId(lostId);
        }catch(Exception e){
            throw new BaseException(NOT_EXIST_LOST);
        }
        return new DetailLostInfoRes(lost);

    }

    public List<LostCommentListRes> findAllCommentsByLostId(Long lostId){
        List<LostComment> list = jpaLostCommentRepository.findByLostId(lostId);
        List<LostCommentListRes> result = new ArrayList<>();
        for(LostComment l : list){
            result.add(new LostCommentListRes(l));
        }
        return result;
    }


}
