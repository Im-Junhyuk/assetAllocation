package graduationproject.assetallocation.domain.dto;

import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaaDTO {

    String name;
    List<AaAssetDTO> aAAssets;
    String startDay;
    String endDay;
    Long initialCash;
    RebalancingPeriod rebalancingPeriod;

    public static SaaDTO from(Aa saa){
        if (saa == null){
            return null;
        }

        return SaaDTO.builder()
                .name(saa.getName())
                .aAAssets(saa.getAaAssets().stream()
                        .map((s)-> AaAssetDTO.from(s))
                        .collect(Collectors.toList()))
                .startDay(saa.getStartDay())
                .endDay(saa.getEndDay())
                .initialCash(saa.getInitialCash())
                .rebalancingPeriod(saa.getRebalancingPeriod())
                .build();
    }

}

