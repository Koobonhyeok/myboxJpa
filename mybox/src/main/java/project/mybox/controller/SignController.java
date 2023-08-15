package project.mybox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.mybox.dto.SignDto;
import project.mybox.dto.UserDto;
import project.mybox.security.JwtTokenProvider;
import project.mybox.service.CustomUserDetailService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SignController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired(required=false)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signIn")
//    @ResponseBody
    public ResponseEntity<?> signInUser(HttpServletRequest request, @ModelAttribute UserDto userDto){
        UserDto result = customUserDetailService.findById(userDto.getUserId());
        SignDto signDto = new SignDto();
//        if( !passwordEncoder.matches(userDto.getPassword(), result.getPassword()) ){

        if( !userDto.getPassword().equals(result.getPassword()) ){
            signDto.setResult("Login Fail");
            signDto.setMessage("ID or Password is invalid");
            return new ResponseEntity<>(signDto, HttpStatus.OK);
        }
        List<String> roleList = Arrays.asList(result.getRoles().split(","));
        signDto.setResult("login Success");
        signDto.setToken(jwtTokenProvider.createToken(result.getUserId(), roleList));

        System.out.println("토큰 생성 확인  ::   " + signDto.getToken() );
        return new ResponseEntity<>(signDto, HttpStatus.OK);
    }

    // signup,
    @PostMapping(value = "/signUp")
//    @ResponseBody
    public ResponseEntity<?> addUser(HttpServletRequest request, @ModelAttribute UserDto signupUser) {

        UserDto user = signupUser;
        user.setRoles("ROLE_USER");
        user.setPassword(signupUser.getPassword());
//        user.setPassword(passwordEncoder.encode(signupUser.getPassword()));
        SignDto signDto = new SignDto();
        int result = customUserDetailService.signInUser(user);
        if (result == 1) {
            signDto.setResult("success");
            signDto.setMessage("SignUp complete");
            return new ResponseEntity<>(signDto, HttpStatus.OK);
        } else if (result == -1) {
            signDto.setResult("fail");
            signDto.setMessage("There is the same name, please change your name.");
            return new ResponseEntity<>(signDto, HttpStatus.OK);
        } else {
            signDto.setResult("fail");
            signDto.setMessage("Ask system admin");
            return new ResponseEntity<>(signDto, HttpStatus.OK);
        }
    }

    @GetMapping("/test")
    public void test(){
        System.out.println("여기도 확인하자");
    }

}
