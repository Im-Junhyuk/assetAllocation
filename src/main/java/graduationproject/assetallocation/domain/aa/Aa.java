package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Aa {
    @Id @GeneratedValue
    @Column(name = "AA_ID")
    private Long id;

    private String name;

    private LocalDateTime createdTime;

    private  LocalDateTime lastModifiedTime;

    private long initialCash;

    @Enumerated(EnumType.STRING)
    private RebalancingPeriod rebalancingPeriod;

    private String startDay;

    private String endDay;

    @OneToMany(mappedBy = "aa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AaAsset> aaAssets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    // 연관관계 메소드
    public void setMember(Member member){
        this.member = member;
    }

    public void addAaAsset(AaAsset aaAsset){
        this.aaAssets.add(aaAsset);
        aaAsset.setAa(this);
    }
    public void setCreatedTime(){
        this.setCreatedTime(LocalDateTime.now());
    }

    public void setLastModifiedTime(){
        this.setLastModifiedTime(LocalDateTime.now());
    }

    public static Aa createAA() {
        return null;
    }
}



