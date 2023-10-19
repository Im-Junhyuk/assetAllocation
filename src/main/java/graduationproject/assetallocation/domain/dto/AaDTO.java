package graduationproject.assetallocation.domain.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class AaDTO {
    Long aaId;
    String name;
    LocalDateTime createdDay;
    String type;
}
