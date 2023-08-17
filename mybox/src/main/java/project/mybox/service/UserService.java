package project.mybox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.mybox.domain.User;
import project.mybox.dto.UserDto;
import project.mybox.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Map<String, Object> userRegForm(String userId, String password){
        Map<String, Object> pMap = new HashMap<>();
        try {
            // 아이디 중복 체크
            List<User> userList = userRepository.findById(userId);
            if( userList.size() == 0 ){
                User user = User.builder()
                        .userId( userId )
                        .password( password )
                        .storage(0L)
                        .regDttm( LocalDateTime.now() )
                        .build();


                userRepository.userRegForm(user);
                pMap.put("status", "ok");
                pMap.put("message", "회원가입이 완료되었습니다.");
            }else{
                pMap.put("status", "fail");
                pMap.put("message", "회원가입이 가능하지 않습니다.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return pMap;
    }

    @Transactional
    public Map<String, Object> getUserInfo( String userId ){
        Map<String, Object> pMap = new HashMap<>();
        try {
            List<User> userList = userRepository.findById(userId);
            if( userList.size() > 0 ){
                pMap.put("status" , "success");
                pMap.put("data", userList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pMap;
    }

}
