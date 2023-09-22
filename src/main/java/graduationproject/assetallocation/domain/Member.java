package graduationproject.assetallocation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Member {
    // id, login_id, password, 가입일, 수정일
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;

    @Column(name = "login_id")
    private String loginId;

    private String password;

    private LocalDateTime joinDay;

    public Member(){
        this.setJoinDay(LocalDateTime.now());
    }
}
