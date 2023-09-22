package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AAAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.AA;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("S")
public class SAA extends AA {

    // 생성 메소드
    public static SAA createAA(String name, Member member, List<AAAsset> aAAssets,
                               String startDay, String endDay, Long initialCash,
                               RebalancingPeriod rebalancingPeriod){
        SAA sAA = new SAA();
        sAA.setName(name);
        sAA.setMember(member);
        for (AAAsset aAAsset:aAAssets){
            sAA.addAAAsset(aAAsset);
        }

        sAA.setStartDay(startDay);
        sAA.setEndDay(endDay);
        sAA.setInitialCash(initialCash);
        sAA.setRebalancingPeriod(rebalancingPeriod);
        sAA.setCreatedTime();

        return sAA;
    }
}
