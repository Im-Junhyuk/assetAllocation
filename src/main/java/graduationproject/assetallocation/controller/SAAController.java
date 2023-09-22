package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.AA;
import graduationproject.assetallocation.domain.aa.SAA;
import graduationproject.assetallocation.domain.dto.AAAssetDTO;
import graduationproject.assetallocation.domain.dto.SAADTO;
import graduationproject.assetallocation.service.AAService;
import graduationproject.assetallocation.service.MemberService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SAAController {

    private final AAService aAService;

    private final MemberService memberService;

//    private final AARepository aARepository;

    @GetMapping("/SAA/read/{id}")
    public Map<String, Object> findById(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        Optional<AA> findAA = aAService.findById(id);
        if (findAA.isPresent()){
            response.put("1", findAA.get());
        }
        else {
            response.put("0", "0");
        }

        return response;
    }

    @GetMapping("/SAAs/{userId}")
    public List<AA> findAllByUserId(@PathVariable Long userId){
        Member member = memberService.findById(userId).get();
        List<AA> list = aAService.findByMember(member);
        return list;
    }


    @GetMapping("/SAAs")
    public List<AA> findAll(){
        List<AA> list = aAService.findAll();
        return list;
    }
    @PostMapping("/SAA/save")
    public String saveSAA(@RequestBody SAADTO sAADTO){
        log.info("createSAA Controller");

        Member member = memberService.findById(sAADTO.getMemberId()).get();
        RebalancingPeriod rebalancingPeriodEnum = RebalancingPeriod.valueOf(sAADTO.getRebalancingPeriod());
        long sAAId = aAService.createSAA(sAADTO.getName(), member, sAADTO.getAAAssets(),
                sAADTO.getStartDay(), sAADTO.getEndDay(), sAADTO.getInitialCash(),
                rebalancingPeriodEnum);
        //save(sAA);
        return Long.toString(sAAId);
    }

    @PostMapping("/SAA/check")
    public SAADTO check(@RequestBody SAADTO sAADTO){
        return sAADTO;
    }

    private void save(SAA sAA) {
        if (sAA.getId() == null){
            aAService.setCreateTime(sAA);
        }
        else{
            aAService.setLastModifiedTime(sAA);;
        }
        aAService.save(sAA);
    }

    @PostMapping("SAA/delete/{id}")
    public void deleteOne(@PathVariable Long id){
        aAService.deleteById(id);
    }
}
