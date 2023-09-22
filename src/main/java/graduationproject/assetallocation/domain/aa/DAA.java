package graduationproject.assetallocation.domain.aa;

import graduationproject.assetallocation.domain.aa.AA;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("D")
public class DAA extends AA {


    private String tradeStrategy; // abs, rel etc..
}
