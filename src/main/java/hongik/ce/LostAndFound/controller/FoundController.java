package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.domain.dto.found.FoundListByLocationRes;
import hongik.ce.LostAndFound.domain.dto.found.list.DetailFoundInfoRes;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListPhoto;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListRes;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterReq;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterRes;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentListRes;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentRegisterReq;
import hongik.ce.LostAndFound.domain.dto.foundcomment.FoundCommentRegisterRes;
import hongik.ce.LostAndFound.service.FoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;

@RestController
@RequestMapping("/found")
@RequiredArgsConstructor
public class FoundController {
    private final FoundService foundService;

    @GetMapping("")
    public Response<List<FoundListPhoto>, Object> getFoundList() {
        try{    List<FoundListPhoto> list;

            list = foundService.getFoundList();
            return new Response<>(list);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @GetMapping("/{foundId}")
    public Response<DetailFoundInfoRes, List<FoundCommentListRes>> getFoundInfoByFoundId(@PathVariable Long foundId) {
        try {
            foundService.updateFoundHit(foundId);
            List<FoundCommentListRes> commentList = foundService.findAllCommentsByFoundId(foundId);
            DetailFoundInfoRes foundInfoRes = foundService.findByFoundId(foundId);
            if (commentList == null) {
                return new Response<>(foundInfoRes);
            }
            return new Response<>(foundInfoRes, commentList);

        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @PostMapping("/register")
    public Response<FoundRegisterRes, Object> registerFound(FoundRegisterReq foundRegisterReq) {

        try {
            if (foundRegisterReq.getCategory().equals("") || foundRegisterReq.getCategory() == null) {
                return new Response<>(EMPTY_CATEGORY);
            }
            if (foundRegisterReq.getTitle().equals("") || foundRegisterReq.getTitle() == null) {
                return new Response<>(EMPTY_TITLE);
            }
            if (foundRegisterReq.getContent().equals("") || foundRegisterReq.getContent() == null) {
                return new Response<>(EMPTY_CONTENTS);
            }
            FoundRegisterRes foundRegisterRes = foundService.registerFound(foundRegisterReq);
            return new Response<>(foundRegisterRes);

        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }


    @PostMapping("/comment")
    public Response<FoundCommentRegisterRes, Object> registerFoundComment(@RequestBody FoundCommentRegisterReq foundCommentRegisterReq) {
        try {
            if (foundCommentRegisterReq.getUserId() == null) {
                return new Response<>(EMPTY_USER_ID);
            }
            if (foundCommentRegisterReq.getFoundId() == null) {
                return new Response<>(EMPTY_USER_ID);
            }

            FoundCommentRegisterRes foundCommentRegisterRes = foundService.registerFoundComment(foundCommentRegisterReq);
            return new Response<>(foundCommentRegisterRes);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @GetMapping("/location")
    public Response<List<FoundListByLocationRes>, Object> getFoundListByLocation() {
        try {
            List<FoundListByLocationRes> result = foundService.countAllByLocation();
            return new Response<>(result);
        } catch (BaseException e) {
            return new Response<>(DATABASE_ERROR);
        }
    }

    @GetMapping("/location/{foundLocation}")
    public Response<List<FoundListRes>, Object> getFoundListByFoundLocation(@PathVariable String foundLocation) {
        try {
            List<FoundListRes> result = foundService.findAllByLocation(foundLocation);
            return new Response<>(result);
        } catch (BaseException e) {
            return new Response<>(DATABASE_ERROR);
        }

    }
}
