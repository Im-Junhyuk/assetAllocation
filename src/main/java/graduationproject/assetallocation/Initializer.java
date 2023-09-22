package graduationproject.assetallocation;

import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.repository.AssetRepository;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    private final AssetRepository assetRepository;

    public Initializer(AssetRepository assetRepository){
        this.assetRepository = assetRepository;

        String[] assetArray = {"SPY", "QQQ","VT", "VEU", "EFA", "EEM", "VWO", "IWD", "IFW"
        , "IWM", "IWN", "IWO", "MTUM","SCZ", "XLE", "XLB", "XLI", "XLY", "XLP", "XLV", "XLF"
        , "XLK", "XLU", "VGK", "EZU", "EWJ" // STOCK LIST
        , "IEF", "TLT", "TIP", "LQD", "HYG", "BNDX", "EMB", "BWX" // BOND LIST
        , "GSG", "PDBC", "GLD", "VNQ", "IYR", "SCHH", "REM", "RWX" // ALTERNATIVE
        };

        for (String assetName : assetArray){
            Asset asset = new Asset(assetName);
            save(asset);
        }
    }

    private void save(Asset asset){
        assetRepository.save(asset);
    }
}
