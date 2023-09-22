package graduationproject.assetallocation.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SaaDTO {

    String name;
    Long memberId;
    List<AaAssetDTO> aAAssets;
    String startDay;
    String endDay;
    Long initialCash;
    String rebalancingPeriod;


}

