package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterReq;
import hongik.ce.LostAndFound.domain.dto.found.register.FoundRegisterRes;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterReq;
import hongik.ce.LostAndFound.domain.dto.lost.register.LostRegisterRes;
import hongik.ce.LostAndFound.service.FoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;
import static hongik.ce.LostAndFound.config.ResponseStatus.EMPTY_CONTENTS;

@RestController
@RequestMapping("/found")
@RequiredArgsConstructor
public class FoundController {
    private final FoundService foundService;

    @PostMapping("/register")
    public Response<FoundRegisterRes,Object> registerFound(@RequestBody FoundRegisterReq foundRegisterReq){

        try{
            if(foundRegisterReq.getUserId().equals("") || foundRegisterReq.getUserId() == null){
                return new Response<>(EMPTY_USER_ID);

            }if(foundRegisterReq.getCategory().equals("") || foundRegisterReq.getCategory() == null){
                return new Response<>(EMPTY_CATEGORY);
            }
            if(foundRegisterReq.getTitle().equals("") || foundRegisterReq.getTitle() == null) {
                return new Response<>(EMPTY_TITLE);
            }
            if(foundRegisterReq.getContent().equals("") || foundRegisterReq.getContent() == null){
                return new Response<>(EMPTY_CONTENTS);
            }

            FoundRegisterRes foundRegisterRes = foundService.registerFound(foundRegisterReq);
            return new Response<>(foundRegisterRes);

        }catch(BaseException e){
            return new Response<>(e.getResponseStatus());
        }
    }


}
