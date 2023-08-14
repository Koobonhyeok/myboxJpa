package project.mybox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.mybox.service.UserService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/userRegForm")
    public ResponseEntity<?> userRegForm(@RequestParam String userId, @RequestParam String password ){
        Map<String, Object> s= new HashMap<>();
        System.out.println("===============  "  +userId);
        userService.userRegForm( userId, password );
        s.put("rnqhsgur" , "lratnwjd");
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
