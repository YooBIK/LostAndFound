package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.config.ResponseStatus;
import hongik.ce.LostAndFound.domain.dto.found.FoundListByLocationRes;
import hongik.ce.LostAndFound.domain.dto.found.list.FoundListRes;
import hongik.ce.LostAndFound.domain.dto.lost.LostListByLocationRes;
import hongik.ce.LostAndFound.domain.dto.lost.list.LostListRes;
import hongik.ce.LostAndFound.service.FoundService;
import hongik.ce.LostAndFound.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UtilController {

    private final LostService lostService;
    private final FoundService foundService;

    @GetMapping("/location")
    public Response<List<LostListByLocationRes>, List<FoundListByLocationRes>> getListsByLocation() throws BaseException {
        List<LostListByLocationRes> lostListByLocation;
        List<FoundListByLocationRes> foundListByLocation;
        try {
            lostListByLocation = lostService.countAllByLocation();
            foundListByLocation = foundService.countAllByLocation();
            return new Response<>(lostListByLocation, foundListByLocation);
        } catch (BaseException e) {
            return new Response<>(ResponseStatus.NOT_EXIST_ACCOUNT);
        }
    }

    @GetMapping("/location/{location}")
    public Response<List<LostListRes>, List<FoundListRes>> getListsByLocation(@PathVariable String location) throws BaseException {
        List<LostListRes> lostList;
        List<FoundListRes> foundList;
        try {
            lostList = lostService.findAllByLocation(location);
            foundList = foundService.findAllByLocation(location);
            return new Response<>(lostList, foundList);
        } catch (BaseException e) {
            return new Response<>(ResponseStatus.FAIL);
        }
    }
}
