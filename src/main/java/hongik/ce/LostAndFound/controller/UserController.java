package hongik.ce.LostAndFound.controller;


import hongik.ce.LostAndFound.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;


}
