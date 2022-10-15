package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.config.ResponseStatus;
import hongik.ce.LostAndFound.domain.dto.lost.list.DetailLostInfoRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lostcomment.LostCommentListRes;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInRes;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;

@RestController
@RequestMapping(value = "/lost")
@RequiredArgsConstructor
public class LostController {

    private final LostService lostService;

    @GetMapping("")
    public Response<List<LostListRes>,List<Object>> getLostList(@RequestParam(required = false) String category, @RequestParam(required = false) String year, @RequestParam(required = false) String month){
        List<LostListRes> list;

        if(year == null && month == null && category == null){
            list = lostService.getLostList();
            return new Response<>(list);
        }

        if(category != null){
            list = lostService.getLostListByCategory(category);
            return new Response<>(list);
        }

        if(year != null && month != null){
            list = lostService.getLostListByYearMonth(year, month);
            return new Response<>(list);
        }

        list = lostService.getLostList(category,year,month);
        return new Response<>(list);

    }


    @PostMapping("/register")
    public Response<LostRegisterRes,List<Object>> registerLost(@RequestBody LostRegisterReq lostRegisterReq){

        try{
            if(lostRegisterReq.getUserId().equals("") || lostRegisterReq.getUserId() == null){
                return new Response<>(EMPTY_USER_ID);

            }if(lostRegisterReq.getCategory().equals("") || lostRegisterReq.getCategory() == null){
                return new Response<>(EMPTY_CATEGORY);
            }
            if(lostRegisterReq.getTitle().equals("") || lostRegisterReq.getTitle() == null) {
                return new Response<>(EMPTY_TITLE);
            }
            if(lostRegisterReq.getContents().equals("") || lostRegisterReq.getContents() == null){
                return new Response<>(EMPTY_CONTENTS);
            }

            LostRegisterRes lostRegisterRes = lostService.registerLost(lostRegisterReq);
            return new Response<>(lostRegisterRes);

        }catch(BaseException e){
            return new Response<>(e.getResponseStatus());
        }
    }

    @GetMapping("/{lostId}")
    public Response<DetailLostInfoRes,List<Object>> findByLostId(@PathVariable Long lostId){
        try{
            DetailLostInfoRes detailLostInfoRes = lostService.findByLostId(lostId);
            List<LostCommentListRes> asdf = lostService.findCommentsByLostCommentId(lostId);
            return new Response<>(detailLostInfoRes);
        }catch (BaseException e){
            return new Response<>(e.getResponseStatus());
        }
    }





}
