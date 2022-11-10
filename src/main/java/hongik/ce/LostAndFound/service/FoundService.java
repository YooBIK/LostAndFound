package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.domain.dto.found.list.DetailFoundInfoRes;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListRes;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterReq;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterRes;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentListRes;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentRegisterReq;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.DetailLostInfoRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentListRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentRegisterRes;
import hongik.ce.LostAndFound.domain.entity.*;
import hongik.ce.LostAndFound.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hongik.ce.LostAndFound.config.ResponseStatus.NOT_EXIST_ACCOUNT;
import static hongik.ce.LostAndFound.config.ResponseStatus.NOT_EXIST_LOST;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoundService {
    private final JpaFoundRepository jpaFoundRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaFoundCommentRepository jpaFoundCommentRepository;

//    public DetailFoundInfoRes getBuildingCnt() throws BaseException{
//        long TNum = 0;
//        long RNum = 0;
//        long KNum = 0;
//        long LNum = 0;
//        long INum = 0;
//        long ANum = 0;
//        long BNum = 0;
//        long CNum = 0;
//        long DNum = 0;
//        long Z2Num = 0;
//        long Z3Num = 0;
//        long LibraryNum = 0;
//        long ArtNum = 0;
//        long ENum = 0;
//        long SNum = 0;
//
//        try{
//            List<Found> list = jpaFoundRepository.findAll();
//                 = jpaFoundRepository.findByLost_location(lost_location);
//
//        }catch(Exception e){
//            throw new BaseException(NOT_EXIST_LOST);
//        }
//        return new DetailFoundInfoRes(found);
//    }


    public List<FoundListRes> getFoundList() {
        List<Found> list = jpaFoundRepository.findAll();
        List<FoundListRes> result = new ArrayList<>();
        for (Found f : list) {
            result.add(new FoundListRes(f));
        }
        return result;
    }


//    public List<FoundBuildingListRes> findByFoundLocation(String foundlocation) throws BaseException{
//        Found found;
//        try{
//            found = jpaFoundRepository.findByFoundId(foundId);
//        }catch(Exception e){
//            throw new BaseException(NOT_EXIST_LOST);
//        }
//        return new DetailFoundInfoRes(found);
//    }


    public void updateFoundHit(Long foundId){
        jpaFoundRepository.updateHit(foundId);
    }

    public List<FoundCommentListRes> findAllCommentsByFoundId(Long foundId) throws BaseException{
        List<FoundComment> list;
        Found found;
        try{
            found = jpaFoundRepository.findByFoundId(foundId);
        }catch (Exception e){
            throw new BaseException(NOT_EXIST_LOST);
        }

        list = jpaFoundCommentRepository.findByFound_FoundId(found.getFoundId());
        List<FoundCommentListRes> result = new ArrayList<>();
        for(FoundComment fc : list){
            result.add(new FoundCommentListRes(fc));
        }
        return result;
    }

    public FoundRegisterRes registerFound(FoundRegisterReq foundRegisterReq) throws BaseException {
        Long userId = foundRegisterReq.getUserId();
        String title = foundRegisterReq.getTitle();
        String category = foundRegisterReq.getCategory();
        String lost_location = foundRegisterReq.getLost_location();
        String lost_detail = foundRegisterReq.getLost_detail();
        String store_location = foundRegisterReq.getStore_location();
        String store_detail = foundRegisterReq.getStore_detail();
        String content = foundRegisterReq.getContent();

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

        Found result = jpaFoundRepository.save(new Found(user,title,categoryResult,lost_location,lost_detail,store_location,store_detail,content,date));
        return new FoundRegisterRes(result);
    }

    public DetailFoundInfoRes findByFoundId(Long foundId) throws BaseException{
        Found found;
        try{
            found = jpaFoundRepository.findByFoundId(foundId);
        }catch(Exception e){
            throw new BaseException(NOT_EXIST_LOST);
        }
        return new DetailFoundInfoRes(found);
    }

    public FoundCommentRegisterRes registerFoundComment(FoundCommentRegisterReq foundCommentRegisterReq) throws BaseException{
        Long userId = foundCommentRegisterReq.getUserId();
        Long foundId = foundCommentRegisterReq.getFoundId();

        User user = jpaUserRepository.findByUserId(userId);
        Found found = jpaFoundRepository.findByFoundId(foundId);
        String contents =foundCommentRegisterReq.getContents();

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(now);

        FoundComment result = jpaFoundCommentRepository.save(new FoundComment(user,found,contents,date));

        return new FoundCommentRegisterRes(result.getFoundCommentId());
    }
}
