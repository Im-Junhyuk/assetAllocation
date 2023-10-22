package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.AaDTO;
import graduationproject.assetallocation.domain.dto.DaaDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@DiscriminatorValue("D")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Daa extends Aa {

    private String strategyType; // abs, rel

    public static Daa createDaa(AaDTO aaDTO, List<AaAsset> aaAssets, Member member) {
        DaaDTO daaDTO = (DaaDTO) aaDTO;
        return (Daa) Daa.builder()
                .id(daaDTO.getId())
                .name(daaDTO.getName())
                .aaAssets(aaAssets)
                .createdDay(LocalDate.now())
                .startDay(aaDTO.getStartDay())
                .endDay(aaDTO.getEndDay())
                .initialCash(aaDTO.getInitialCash())
                .rebalancingPeriod(aaDTO.getRebalancingPeriod())
                .member(member)
                .strategyType(daaDTO.getStrategyType())
                .build()
                .addAaAssetList(aaAssets);
    }


    @Override
    public Aa updateFromDTO(AaDTO saaDTO, List<AaAsset> aaAssets) {
        return null;
    }

    public AaDTO toDTO(){
        return DaaDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .startDay(this.getStartDay())
                .endDay(this.getEndDay())
                .rebalancingPeriod(this.getRebalancingPeriod())
                .initialCash(this.getInitialCash())
                .lastModifiedDay(this.getLastModifiedDay())
                .createdDay(this.getCreatedDay())
                .aaAssets(this.getAaAssets().stream()
                        .map(AaAsset::toDTO)
                        .collect(Collectors.toList()))
                .strategyType(this.strategyType)
                .build();
    }
}
