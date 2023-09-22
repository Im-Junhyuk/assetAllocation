package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Saa;
import graduationproject.assetallocation.domain.dto.SaaDTO;
import graduationproject.assetallocation.service.AaService;
import graduationproject.assetallocation.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SAAController {

    private final AaService aaService;
    private final MemberService memberService;

//    private final AARepository aARepository;

    @GetMapping("/saa/read/{id}")
    public Map<String, Object> findById(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        Optional<Aa> findAA = aaService.findById(id);
        if (findAA.isPresent()){
            response.put("1", findAA.get());
        }
        else {
            response.put("0", "0");
        }

        return response;
    }

    @GetMapping("/saas/{userId}")
    public List<Aa> findAllByUserId(@PathVariable Long userId){
        Member member = memberService.findById(userId).get();
        List<Aa> list = aaService.findByMember(member);
        return list;
    }


    @GetMapping("/saas")
    public List<Aa> findAll(){
        List<Aa> list = aaService.findAll();
        return list;
    }
    @PostMapping("/saa/save")
    public String saveSAA(@RequestBody SaaDTO sAADTO){
        log.info("createSAA Controller");

        Member member = memberService.findById(sAADTO.getMemberId()).get();
        RebalancingPeriod rebalancingPeriodEnum = RebalancingPeriod.valueOf(sAADTO.getRebalancingPeriod());
        long sAAId = aaService.createSAA(sAADTO.getName(), member, sAADTO.getAAAssets(),
                sAADTO.getStartDay(), sAADTO.getEndDay(), sAADTO.getInitialCash(),
                rebalancingPeriodEnum);
        //save(sAA);
        return Long.toString(sAAId);
    }

    @PostMapping("/saa/check")
    public SaaDTO check(@RequestBody SaaDTO saaDTO){
        return saaDTO;
    }

    private void save(Saa saa) {
        if (saa.getId() == null){
            aaService.setCreateTime(saa);
        }
        else{
            aaService.setLastModifiedTime(saa);;
        }
        aaService.save(saa);
    }

    @PostMapping("saa/delete/{id}")
    public void deleteOne(@PathVariable Long id){
        aaService.deleteById(id);
    }
}
