package graduationproject.assetallocation.domain.aa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("D")
public class Daa extends Aa {


    private String tradeStrategy; // abs, rel etc..
}
