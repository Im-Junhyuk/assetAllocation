package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.dto.AaAssetDTO;
import graduationproject.assetallocation.domain.dto.SaaDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("S")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
        saa.setCreatedDay();

        return saa;
    }

    public static Saa updateFromDTO(Saa saa, SaaDTO saaDTO, List<AaAsset> aaAssets){
        saa.setName(saaDTO.getName());
        saa.setStartDay(saaDTO.getStartDay());
        saa.setEndDay(saaDTO.getEndDay());
        saa.setInitialCash(saaDTO.getInitialCash());
        saa.setRebalancingPeriod(saaDTO.getRebalancingPeriod());
        saa.setLastModifiedDay();

        saa.getAaAssets().clear();
        saa.getAaAssets().addAll(aaAssets);

        return saa;

    }
}
