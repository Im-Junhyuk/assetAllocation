package graduationproject.assetallocation.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SAADTO {

    String name;
    Long memberId;
    List<AAAssetDTO> aAAssets;
    String startDay;
    String endDay;
    Long initialCash;
    String rebalancingPeriod;


}

