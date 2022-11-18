package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.FileStore;
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
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
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

    public List<FoundListRes> getFoundList() {
        List<Found> list = jpaFoundRepository.findAll();
        List<FoundListRes> result = new ArrayList<>();
        for (Found f : list) {
            result.add(new FoundListRes(f));
        }
        return result;
    }

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
        MultipartFile multipartFile = foundRegisterReq.getImagefile();
        log.info("title = {}",title);
        log.info("multipartFile = {}",multipartFile);
        FileStore fileStore = new FileStore();
        UploadFile imageFile;
        System.out.println("here2 ????????");
        try{
            System.out.println("here3 ????????");
            imageFile = fileStore.storeFile(multipartFile);
            System.out.println("here4 ????????");
        }catch(Exception e){
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }


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

        Found result = jpaFoundRepository.save(new Found(user,title,categoryResult,lost_location,lost_detail,store_location,store_detail,content,imageFile,date));
        return new FoundRegisterRes(result);
    }

    public DetailFoundInfoRes findByFoundId(Long foundId) throws BaseException{
        Found found;
        UrlResource urlResource;
        try{
            found = jpaFoundRepository.findByFoundId(foundId);

            String storeFilename = found.getImageFile().getStoreFilename();
            String uploadFilename = found.getImageFile().getUploadFilename();

            FileStore fileStore = new FileStore();

            urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFilename));

            // 업로드 한 파일명이 한글인 경우 아래 작업을 안해주면 한글이 깨질 수 있음
            String encodedUploadFileName = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);
            String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        }catch(Exception e) {
            throw new BaseException(NOT_EXIST_LOST);
        }
        System.out.println(urlResource.toString());
        return new DetailFoundInfoRes(found,urlResource);
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

    public List<FoundListByLocationRes> countAllByLocation() throws BaseException{
        return jpaFoundRepository.countAllByLocation();
    }

    public List<FoundListRes> findAllByLocation(String foundLocation) throws BaseException{
        List<Found> list = jpaFoundRepository.findAllByLostLocation(foundLocation);
        List<FoundListRes> result = new ArrayList<>();
        for(Found f : list){
            result.add(new FoundListRes(f));
        }
        return result;
    }
}
