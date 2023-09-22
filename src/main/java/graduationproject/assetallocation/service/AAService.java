package graduationproject.assetallocation.service;

import graduationproject.assetallocation.domain.AAAsset;
import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.AA;
import graduationproject.assetallocation.domain.aa.SAA;
import graduationproject.assetallocation.domain.dto.AAAssetDTO;
import graduationproject.assetallocation.repository.AARepository;
import graduationproject.assetallocation.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AAService {
    private final AARepository aARepository;

    private final AssetRepository assetRepository;

    public void save(AA aA){
        aARepository.save(aA);
    }

    public void deleteById(Long aAId){
        aARepository.deleteById(aAId);
    }

    public Optional<AA> findById(Long aAId){
        return aARepository.findById(aAId);
    }

    public List<AA> findByMember(Member member){ return aARepository.findByMember(member);}

    public void deleteByMember(Member member){ aARepository.deleteByMember(member);}

    public List<AA> findAll(){ return aARepository.findAll();}

    public void setCreateTime(AA aA){
        aA.setCreatedTime();
    }

    public void setLastModifiedTime(AA aA){
        aA.setLastModifiedTime();
    }


    public long createSAA(String name, Member member, List<AAAssetDTO> aAAssetDTOs, String startDay,
                          String endDay, Long initialCash, RebalancingPeriod rebalancingPeriod){

        log.info("AAService createSAA");
        // create AAAsset
        List<AAAsset> aAAssets = new ArrayList<>();
        for(AAAssetDTO aAAssetDto:aAAssetDTOs){
            Asset asset = assetRepository.findByName(aAAssetDto.getAssetName()).get();
            AAAsset aAAsset = AAAsset.createAAAsset(asset, aAAssetDto.getRate());
            aAAssets.add(aAAsset);
        }

        // create AA
        AA aA = SAA.createAA(name, member, aAAssets, startDay, endDay, initialCash, rebalancingPeriod);

        save(aA);

        return aA.getId();
    }
}
