package project.mybox.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// Data 어노테이션은 Getter, Setter, ToString, EqualsAndHashCode,
// RequiredArgsConstructor를 합쳐 놓은 어노테이션
public class UserDto {

    private Long userNo;

    private String userId;

    private String password;

    private int storage;

    private LocalDateTime regDttm;

    @Builder
    public UserDto(Long userNo, String userId, String password, int storage){
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.storage = storage;
//        this.regDttm = regDttm;
    }

}
