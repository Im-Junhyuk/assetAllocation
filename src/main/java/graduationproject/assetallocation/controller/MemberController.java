package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.repository.MemberJpaRepository;
import graduationproject.assetallocation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/create")
    public String createMember(@RequestBody Member member){
        memberService.saveMember(member);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return "ok";
    }

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
}
