package graduationproject.assetallocation.domain;

import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.dto.AaAssetDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class AaAsset {

    @Id @GeneratedValue
    @Column(name = "AAASSET_ID")
    private Long id;

    private Integer rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AA_ID")
    private Aa aa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    private String assetName;

    // creation method
    public static AaAsset createAaAsset(Asset asset, int rate){
        AaAsset aaAsset = new AaAsset();
        setAsset(asset, rate, aaAsset);

        return aaAsset;
    }

    // relation method
    private static void setAsset(Asset asset, int rate, AaAsset aAAsset) {
        aAAsset.setAsset(asset);
        aAAsset.setAssetName(asset.getName());
        aAAsset.setRate(rate);
    }

}
