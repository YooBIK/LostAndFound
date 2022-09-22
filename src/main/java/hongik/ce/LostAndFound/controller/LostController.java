package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LostController {

    private final LostService lostService;
}
