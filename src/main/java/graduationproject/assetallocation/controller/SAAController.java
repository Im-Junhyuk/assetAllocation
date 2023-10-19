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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;
import java.util.*;

import static graduationproject.assetallocation.domain.dto.AaAssetDTO.from;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SAAController {

    private final AaService aaService;
    private final MemberService memberService;

    private final JwtUtil jwtUtil;
    // 새 자산배분 저장
    @PostMapping("/user/saa")
    public String saveSAA(@RequestBody SaaDTO saaDTO, HttpServletRequest request){
        log.info("createSAA Controller");

        // get user from token
        String token = jwtUtil.getToken(request);

        RebalancingPeriod rebalancingPeriodEnum = saaDTO.getRebalancingPeriod();

        long saaId = aaService.createSAA(saaDTO.getName(), memberService.findById(jwtUtil.extractId(token)).get(),
                saaDTO.getAAAssets(),
                saaDTO.getStartDay(), saaDTO.getEndDay(), saaDTO.getInitialCash(),
                rebalancingPeriodEnum);
        //save(sAA);
        return String.valueOf(saaId);
    }

    // saaid로 1개 조회
    @GetMapping("/user/saa/{saaId}")
    public ResponseEntity<SaaDTO> findOneById(@PathVariable Long saaId,
                                              @RequestHeader("Authorization") String auth) {
        Aa aa = aaService.findById(saaId).get();
        // check authorization
        String token = jwtUtil.getToken(auth);
        if (aa.getMember().getId() != jwtUtil.extractId(token))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        return ResponseEntity.ok(SaaDTO.from(aa));
    }

    @DeleteMapping("/user/saa/{saaId}")
    public ResponseEntity<String> deleteOneById(@PathVariable Long saaId,
                                @RequestHeader("Authorization") String auth) {
        // check auth
        if (aaService.findById(saaId).get().getMember().getId() != jwtUtil.getIdFromAuth(auth))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("forbidden");

        aaService.deleteById(saaId);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/user/saa/{saaId}")
    public String updateOne(@PathVariable Long saaId){

        return "ok";
    }


    // 정적, 동적 구분해서 id, name, createdDay, type만 모두 전달
    @GetMapping("/user/aas")
    public List<Aa> findAllByUser(HttpServletRequest request){
        String token = jwtUtil.getToken(request);
        Long id = jwtUtil.extractId(token);
        Member member = memberService.findById(id).get();
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

}
