package graduationproject.assetallocation.domain;

import graduationproject.assetallocation.domain.aa.Aa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aa> aaList = new ArrayList<>();

    private String password;

    private LocalDateTime joinDay;

    public Member(){
        this.setJoinDay(LocalDateTime.now());
    }
}
