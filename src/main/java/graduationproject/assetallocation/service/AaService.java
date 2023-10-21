package graduationproject.assetallocation.service;

import graduationproject.assetallocation.domain.AaAsset;
import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Saa;
import graduationproject.assetallocation.domain.dto.AaAssetDTO;
import graduationproject.assetallocation.domain.dto.SaaDTO;
import graduationproject.assetallocation.repository.AaAssetRepository;
import graduationproject.assetallocation.repository.AaRepository;
import graduationproject.assetallocation.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static graduationproject.assetallocation.domain.aa.Saa.updateFromDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class AaService {
    private final AaRepository aaRepository;
    private final AaAssetRepository aaAssetRepository;
    private final AssetRepository assetRepository;


    public void deleteById(Long aAId){
        aaRepository.deleteById(aAId);
    }

    public Optional<Aa> findById(Long aAId){
        return aaRepository.findById(aAId);
    }

    public List<Aa> findByMember(Member member){ return aaRepository.findByMember(member);}

    public void deleteByMember(Member member){ aaRepository.deleteByMember(member);}

    public List<Aa> findAll(){ return aaRepository.findAll();}


    private List<AaAsset> createAaAssetList(List<AaAssetDTO> aaAssetDTOList){
        List<AaAsset> aaAssets = new ArrayList<>();
        for(AaAssetDTO aaAssetDTO:aaAssetDTOList){
            Asset asset = assetRepository.findByName(aaAssetDTO.getAssetName()).get();
            AaAsset aaAsset = AaAsset.createAaAsset(asset, aaAssetDTO.getRate());
            aaAssets.add(aaAsset);
        }
        return aaAssets;
    }

    public Long createSAA(String name, Member member, List<AaAssetDTO> aaAssetDTOs, String startDay,
                          String endDay, Long initialCash, RebalancingPeriod rebalancingPeriod){

        log.info("AAService createSAA");
        // create AAAsset
        List<AaAsset> aaAssets = createAaAssetList(aaAssetDTOs);


        // create AA
        Aa aA = Saa.createAA(name, member, aaAssets, startDay, endDay, initialCash, rebalancingPeriod);

        return aaRepository.save(aA).getId();
    }

    //@Transactional
    public SaaDTO updateSaa(SaaDTO saaDTO) {

        Saa saa = (Saa) aaRepository.findById(saaDTO.getId()).get();

        //update AaAsset
        List<AaAsset> aaAssetList = createAaAssetList(saaDTO.getAaAssets());
        //update saa

        log.info("before clear");
        Saa updatesaa = updateFromDTO(saa, saaDTO, aaAssetList);
        aaRepository.save(updatesaa);
        return SaaDTO.from(updatesaa);
    }
}
