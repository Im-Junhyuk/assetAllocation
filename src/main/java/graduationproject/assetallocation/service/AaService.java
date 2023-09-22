package graduationproject.assetallocation.service;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Saa;
import graduationproject.assetallocation.domain.dto.AaAssetDTO;
import graduationproject.assetallocation.repository.AaRepository;
import graduationproject.assetallocation.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AaService {
    private final AaRepository aaRepository;

    private final AssetRepository assetRepository;

    public void save(Aa aA){
        aaRepository.save(aA);
    }

    public void deleteById(Long aAId){
        aaRepository.deleteById(aAId);
    }

    public Optional<Aa> findById(Long aAId){
        return aaRepository.findById(aAId);
    }

    public List<Aa> findByMember(Member member){ return aaRepository.findByMember(member);}

    public void deleteByMember(Member member){ aaRepository.deleteByMember(member);}

    public List<Aa> findAll(){ return aaRepository.findAll();}

    public void setCreateTime(Aa aA){
        aA.setCreatedTime();
    }

    public void setLastModifiedTime(Aa aA){
        aA.setLastModifiedTime();
    }


    public long createSAA(String name, Member member, List<AaAssetDTO> aaAssetDTOs, String startDay,
                          String endDay, Long initialCash, RebalancingPeriod rebalancingPeriod){

        log.info("AAService createSAA");
        // create AAAsset
        List<AaAsset> aaAssets = new ArrayList<>();
        for(AaAssetDTO aaAssetDTO:aaAssetDTOs){
            Asset asset = assetRepository.findByName(aaAssetDTO.getAssetName()).get();
            AaAsset aaAsset = AaAsset.createAaAsset(asset, aaAssetDTO.getRate());
            aaAssets.add(aaAsset);
        }

        // create AA
        Aa aA = Saa.createAA(name, member, aaAssets, startDay, endDay, initialCash, rebalancingPeriod);

        save(aA);

        return aA.getId();
    }
}
