package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.jwt.JwtFilter;
import graduationproject.assetallocation.jwt.TokenProvider;
import graduationproject.assetallocation.repository.MemberJpaRepository;
import graduationproject.assetallocation.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    @PostMapping("/signup")
    public String createMember(@RequestBody MemberDTO memberDTO){
        memberService.signup(memberDTO);
        return "ok";
    }

//    @PostMapping("logout")
//    public String logout(HttpServletRequest request){
//        String token = getToken(request);
//        // logout
//
//
//        return "ok";
//    }
//    @PostMapping("/delete")
//    @PreAuthorize("hasRole('USER')")
//    public String deleteMember(HttpServletRequest request){
//        // return memberId with request
//        String token = getToken(request);
//
//        // get loginId
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//        Long memberId =
//
//        // logout
//
//        // delete
//
//        memberService.deleteMember(memberId);
//        return "ok";
//    }

    private static String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    @GetMapping("/{memberId}")
    public Member findOneById(@PathVariable Long memberId){
        return memberService.findById(memberId).get();
    }

    @GetMapping("/id/{loginId}")
    public Member findOneByLoginId(@PathVariable String loginId){
        return memberService.findByLoginId(loginId).get();
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDTO> getUserInfo(HttpServletRequest request){
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities());
    }

    @GetMapping("/user/id")
    @PreAuthorize("hasRole('USER')")
    public String getMyId(HttpServletRequest request){
        String token = getToken(request);
        return tokenProvider.extractLoginId(token);
    }
}
