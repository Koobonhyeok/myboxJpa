package project.mybox.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 생성
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id", length = 20)
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "storage")
    private Long storage;

    @Column(name = "roles")
    private String roles;

    @Column(name = "reg_dttm")
    private LocalDateTime regDttm;

    @Builder // Builder를 선언하면 객체 생성 시 받지 않아야 할 매개변수들도 빌더에 노출이 되는 문제점을 해결할 수 있다.
    public User(Long userNo, String userId, String password, Long storage, String roles, LocalDateTime regDttm) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.storage = storage;
        this.roles = roles;
        this.regDttm = regDttm;
    }
}
