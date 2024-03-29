package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.domain.dto.lost.list.DetailLostInfoRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentListRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentRegisterRes;
import hongik.ce.LostAndFound.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;

@RestController
@RequestMapping(value = "/lost")
@RequiredArgsConstructor
public class LostController {

    private final LostService lostService;

    @GetMapping("")
    public Response<List<LostListRes>, Object> getLostList() {
        List<LostListRes> list;

        list = lostService.getLostList();
        return new Response<>(list);
    }

    @GetMapping("/{lostId}")
    public Response<DetailLostInfoRes, List<LostCommentListRes>> getLostInfoByLostId(@PathVariable Long lostId) {
        try {
            lostService.updateLostHit(lostId);
            List<LostCommentListRes> commentList = lostService.findAllCommentsByLostId(lostId);
            DetailLostInfoRes lostInfoRes = lostService.findByLostId(lostId);
            return new Response<>(lostInfoRes, commentList);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @PostMapping("/register")
    public Response<LostRegisterRes, Object> registerLost(@RequestBody LostRegisterReq lostRegisterReq) {

        try {
            if (lostRegisterReq.getUserId() == null) {
                return new Response<>(EMPTY_USER_ID);
            }
            if (lostRegisterReq.getTitle().equals("") || lostRegisterReq.getTitle() == null) {
                return new Response<>(EMPTY_TITLE);
            }
            if (lostRegisterReq.getContents().equals("") || lostRegisterReq.getContents() == null) {
                return new Response<>(EMPTY_CONTENTS);
            }
            if (lostRegisterReq.getLocation().equals("") || lostRegisterReq.getLocation() == null) {
                return new Response<>(EMPTY_LOCATION);
            }

            LostRegisterRes lostRegisterRes = lostService.registerLost(lostRegisterReq);
            return new Response<>(lostRegisterRes);

        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @PostMapping("/comment")
    public Response<LostCommentRegisterRes, Object> registerLostComment(@RequestBody LostCommentRegisterReq lostCommentRegisterReq) {
        try {
            if (lostCommentRegisterReq.getLostId() == null) {
                return new Response<>(EMPTY_USER_ID);
            }

            if (lostCommentRegisterReq.getUserId() == null) {
                return new Response<>(EMPTY_USER_ID);
            }

            if (lostCommentRegisterReq.getContents().equals("") || lostCommentRegisterReq.getContents() == null) {
                return new Response<>(EMPTY_CONTENTS);
            }
            LostCommentRegisterRes lostCommentRegisterRes = lostService.registerLostComment(lostCommentRegisterReq);
            return new Response<>(lostCommentRegisterRes);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }


}
