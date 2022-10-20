package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListRes;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterReq;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Found;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.repository.JpaCategoryRepository;
import hongik.ce.LostAndFound.repository.JpaFoundRepository;
import hongik.ce.LostAndFound.repository.JpaLostCommentRepository;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
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
public class FoundService {
    private JpaFoundRepository jpaFoundRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaLostCommentRepository jpaFoundCommentRepository;

    public FoundRegisterRes registerFound(FoundRegisterReq foundRegisterReq) throws BaseException {
        Long userId = foundRegisterReq.getUserId();
        String title = foundRegisterReq.getTitle();
        String category = foundRegisterReq.getCategory();
        String title = foundRegisterReq.getTitle();
        String contents = foundRegisterReq.getContent();

        private Long userId;
        private String title;
        private String category;
        private String lost_location;
        private String lost_detail;
        private String store_location;
        private String store_detail;
        private String content;
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

        Lost result = jpaFoundRepository.save(new Found(user,categoryResult,title,contents,date));
        return new LostRegisterRes(result);
    }



}
