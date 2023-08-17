package project.mybox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// Data 어노테이션은 Getter, Setter, ToString, EqualsAndHashCode,
// RequiredArgsConstructor를 합쳐 놓은 어노테이션
public class UserDto implements UserDetails {

    /**
     *  This VO is for security.
     */
    private static final long serialVersionUID = 1L;

    private Long userNo;

    private String userId;

    private String password;

    private Long storage;

    private String roles;

    private LocalDateTime regDttm;

    @Builder
    public UserDto(Long userNo, String userId, String password, Long storage, String roles){
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.storage = storage;
        this.roles = roles;
//        this.regDttm = regDttm;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(roles));
        return auth;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
