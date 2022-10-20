package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.domain.dto.lost.list.DetailLostInfoRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentListRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentRegisterRes;
import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.LostComment;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.repository.JpaCategoryRepository;
import hongik.ce.LostAndFound.repository.JpaLostCommentRepository;
import hongik.ce.LostAndFound.repository.JpaLostRepository;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void updateLostHit(Long lostId){
        jpaLostRepository.updateHit(lostId);
    }

    public List<LostCommentListRes> findAllCommentsByLostId(Long lostId) throws BaseException{
        List<LostComment> list;
        Lost lost;
        try{
            lost = jpaLostRepository.findByLostId(lostId);
        }catch (Exception e){
            throw new BaseException(NOT_EXIST_LOST);
        }

        list = jpaLostCommentRepository.findByLost_LostId(lost.getLostId());
        List<LostCommentListRes> result = new ArrayList<>();
        for(LostComment lc : list){
            result.add(new LostCommentListRes(lc));
        }
        return result;
    }

    public LostCommentRegisterRes registerLostComment(LostCommentRegisterReq lostCommentRegisterReq) throws BaseException{
        Long userId = lostCommentRegisterReq.getUserId();
        Long lostId = lostCommentRegisterReq.getLostId();

        User user = jpaUserRepository.findByUserId(userId);
        Lost lost = jpaLostRepository.findByLostId(lostId);
        String contents = lostCommentRegisterReq.getContents();

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(now);

        LostComment lostComment = new LostComment(user,lost,contents,date);
        LostComment result = jpaLostCommentRepository.save(lostComment);

        return new LostCommentRegisterRes(result.getLostCommentId());
    }
}
