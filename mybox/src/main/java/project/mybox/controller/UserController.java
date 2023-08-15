package project.mybox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.mybox.domain.User;
import project.mybox.dto.UserDto;
import project.mybox.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/userRegForm")
    public ResponseEntity<?> userRegForm(@RequestParam String userId, @RequestParam String password){
        Map<String, Object> pMap= userService.userRegForm( userId, password );
        return new ResponseEntity<>(pMap, HttpStatus.OK);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String userId){
        Map<String, Object> pMap = userService.getUserInfo( userId );
        return new ResponseEntity<>(pMap ,HttpStatus.OK);
    }

}
