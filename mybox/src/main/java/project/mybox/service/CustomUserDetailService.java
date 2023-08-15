package project.mybox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.mybox.domain.User;
import project.mybox.dto.UserDto;
import project.mybox.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    // DB를 조회한다.
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.loadUserByUsername(userId);
        UserDto userDto = new UserDto(user.getUserNo(), user.getUserId(),user.getPassword(), user.getStorage(), user.getRoles());
        return userDto;
    }

    @Transactional
    public UserDto findById(String userId){
        List<User> userList = userRepository.findById(userId);
        UserDto userDto = UserDto.builder()
                    .userId(userList.get(0).getUserId())
                    .password(userList.get(0).getPassword())
                    .roles(userList.get(0).getRoles())
                    .build();
        return userDto;
    }

    @Transactional
    public int signInUser(UserDto userDto){
        List<User> userList = userRepository.findById(userDto.getUserId());
        if( userList.size() > 0 ){
            return -1;
        }else{
            User user = User.builder()
                    .userId(userDto.getUserId())
                    .password(userDto.getPassword())
                    .storage(0)
                    .roles(userDto.getRoles())
                    .regDttm(LocalDateTime.now())
                    .build();

            userRepository.userRegForm(user);
            return 1;
        }
    }


}
