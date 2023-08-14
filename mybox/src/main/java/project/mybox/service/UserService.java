package project.mybox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.mybox.domain.User;
import project.mybox.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String userRegForm(String userId, String password){
        try {
            // 아이디 중복 체크
            List<User> userList = userRepository.findById(userId);
            if( userList.size() == 0 ){
                User user = User.builder()
                        .userId( userId )
                        .password( password )
                        .storage(0)
                        .regDttm( LocalDateTime.now() )
                        .build();


                userRepository.userRegForm(user);
            }else{
                System.out.println("회원가입이 가능하지 않습니다.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

}
