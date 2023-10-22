package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.AaDTO;
import graduationproject.assetallocation.domain.dto.DaaDTO;
import graduationproject.assetallocation.service.AaService;
import graduationproject.assetallocation.service.MemberService;
import graduationproject.assetallocation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DaaController {
    private final AaService aaService;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    // save
    @PostMapping("/user/daa")
    public AaDTO createDaa(@RequestBody DaaDTO daaDTO,
                           @RequestHeader("Authorization") String auth){

        return aaService.createDaa(jwtUtil.getIdFromAuth(auth), daaDTO);
    }

    //find

    //delete

    //update

    //find all
}
