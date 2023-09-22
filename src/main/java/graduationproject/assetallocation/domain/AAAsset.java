package graduationproject.assetallocation.domain;

import graduationproject.assetallocation.domain.aa.AA;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class AAAsset {

    @Id @GeneratedValue
    @Column(name = "AAASSET_ID")
    private Long id;

    private int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AA_ID")
    private AA aA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID")
    private Asset asset;

    private String assetName;

    // creation method
    public static AAAsset createAAAsset(Asset asset, int rate){
        AAAsset aAAsset = new AAAsset();
        setAsset(asset, rate, aAAsset);

        return aAAsset;
    }

    // relation method
    private static void setAsset(Asset asset, int rate, AAAsset aAAsset) {
        aAAsset.setAsset(asset);
        aAAsset.setAssetName(asset.getName());
        aAAsset.setRate(rate);
    }
}
