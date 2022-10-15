package hongik.ce.LostAndFound.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.ResponseStatus;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.repository.JpaCategoryRepository;
import hongik.ce.LostAndFound.repository.JpaLostRepository;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static hongik.ce.LostAndFound.config.ResponseStatus.DATABASE_ERROR;
import static hongik.ce.LostAndFound.config.ResponseStatus.NOT_EXIST_ACCOUNT;

@Service
@Transactional
@RequiredArgsConstructor
public class LostService {

    private final JpaLostRepository jpaLostRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaCategoryRepository jpaCategoryRepository;


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


}
