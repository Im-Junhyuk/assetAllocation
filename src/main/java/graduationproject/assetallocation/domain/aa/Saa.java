package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("S")
public class Saa extends Aa {

    // 생성 메소드
    public static Saa createAA(String name, Member member, List<AaAsset> aaAssets,
                               String startDay, String endDay, Long initialCash,
                               RebalancingPeriod rebalancingPeriod){
        Saa saa = new Saa();
        saa.setName(name);
        saa.setMember(member);
        for (AaAsset aaAsset:aaAssets){
            saa.addAaAsset(aaAsset);
        }

        saa.setStartDay(startDay);
        saa.setEndDay(endDay);
        saa.setInitialCash(initialCash);
        saa.setRebalancingPeriod(rebalancingPeriod);
        saa.setCreatedTime();

        return saa;
    }
}
