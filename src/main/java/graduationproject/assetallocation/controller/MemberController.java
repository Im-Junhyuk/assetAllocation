package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.repository.MemberJpaRepository;
import graduationproject.assetallocation.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/signup")
    public String createMember(@RequestBody MemberDTO memberDTO){
        memberService.signup(memberDTO);
        return "ok";
    }

//    @ResponseBody
//    @PostMapping("/delete")
//    public String deleteMember(){
//        Long memberId =
//        memberService.deleteMember(memberId);
//        return "ok";
//    }

    @ResponseBody
    @GetMapping("/{memberId}")
    public Member findOneById(@PathVariable Long memberId){
        return memberService.findById(memberId).get();
    }

    @ResponseBody
    @GetMapping("/id/{loginId}")
    public Member findOneByLoginId(@PathVariable String loginId){
        return memberService.findByLoginId(loginId).get();
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDTO> getUserInfo(HttpServletRequest request){
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities());
    }
}
