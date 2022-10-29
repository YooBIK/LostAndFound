package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListRes;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterReq;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterRes;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentRegisterReq;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
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

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoundService {
    private final JpaFoundRepository jpaFoundRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaFoundCommentRepository jpaFoundCommentRepository;

    public List<FoundListRes> getFoundList() {
        List<Found> list = jpaFoundRepository.findAll();
        List<FoundListRes> result = new ArrayList<>();
        for (Found f : list) {
            result.add(new FoundListRes(f));
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
