package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.RebalancingPeriod;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.aa.Saa;
import graduationproject.assetallocation.domain.dto.AaAssetDTO;
import graduationproject.assetallocation.domain.dto.SaaDTO;
import graduationproject.assetallocation.jwt.TokenProvider;
import graduationproject.assetallocation.service.AaService;
import graduationproject.assetallocation.service.MemberService;
import graduationproject.assetallocation.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static graduationproject.assetallocation.domain.dto.AaAssetDTO.from;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SAAController {

    private final AaService aaService;
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    // 새 자산배분 저장
    @PostMapping("/user/saa")
    public String saveSAA(@RequestBody SaaDTO saaDTO, HttpServletRequest request){
        log.info("createSAA Controller");

        // get user from token
        String token = JwtUtil.getToken(request);
        String loginId = tokenProvider.extractLoginId(token);
        Member member = memberService.findByLoginId(loginId).get();
        RebalancingPeriod rebalancingPeriodEnum = saaDTO.getRebalancingPeriod();

        long saaId = aaService.createSAA(saaDTO.getName(), member, saaDTO.getAAAssets(),
                saaDTO.getStartDay(), saaDTO.getEndDay(), saaDTO.getInitialCash(),
                rebalancingPeriodEnum);
        //save(sAA);
        return String.valueOf(saaId);
    }

    // saaid로 1개 조회
    @GetMapping("/user/saa/{saaId}")
    public ResponseEntity<SaaDTO> findOneById(@PathVariable Long saaId){

        return ResponseEntity.ok(SaaDTO.from(aaService.findById(saaId).get()));
    }

    // 정적, 동적 구분해서 id, name, createdDay, type만 모두 전달
    @GetMapping("/user/aas")
    public List<Aa> findAllByUser(HttpServletRequest request){
        String token = JwtUtil.getToken(request);
        String loginId = tokenProvider.extractLoginId(token);

        Member member = memberService.findByLoginId(loginId).get();
        return aaService.findByMember(member);
    }


    @GetMapping("/admin/saas")
    public List<Aa> findAll(){
        List<Aa> list = aaService.findAll();
        return list;
    }


    // service
    private void save(Saa saa) {
        if (saa.getId() == null){
            aaService.setCreateTime(saa);
        }
        else{
            aaService.setLastModifiedTime(saa);;
        }
        aaService.save(saa);
    }

    @PutMapping("/user/saa/{saaId}")
    public void updateById(@PathVariable Long saaId){

    }
    @PostMapping("saa/delete/{saaId}")
    public void deleteById(@PathVariable Long saaId){
        aaService.deleteById(saaId);
    }
}
