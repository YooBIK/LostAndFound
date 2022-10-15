package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.service.FoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/found")
@RequiredArgsConstructor
public class FoundController {
    private final FoundService foundService;
}
