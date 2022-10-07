package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController(value = "/lost")
public class LostController {

    private final LostService lostService;

//    @GetMapping("/add")
//    public String addLost(Model model){
//
//
//    }

}
