package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.dto.LoginDTO;
import graduationproject.assetallocation.domain.dto.TokenDTO;
import graduationproject.assetallocation.jwt.JwtFilter;
import graduationproject.assetallocation.jwt.TokenProvider;
import graduationproject.assetallocation.service.MemberService;
import graduationproject.assetallocation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authorize(@RequestBody LoginDTO loginDTO){
        log.info("{}login request", loginDTO.getLoginId());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getLoginId(), loginDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " +jwt);

        return new ResponseEntity<>(new TokenDTO(jwt), httpHeaders, HttpStatus.OK);
    }

//    @PostMapping("/logout")
//    public String logout(@RequestHeader("Authorization") String auth){
//        Long id = jwtUtil.getIdFromAuth(auth);
//        log.info("{}:logout request", id);
//
//        return memberService.logout(id, auth);
//    }
    @PostMapping("/log")
    public String logout(@RequestHeader("Authorization") String auth){
        Long id = jwtUtil.getIdFromAuth(auth);
        log.info("{}:logout request", id);

        return memberService.logout(id, jwtUtil.getToken(auth));
    }

    @PostMapping("/logout")
    public String logout2(@RequestHeader("Authorization") String auth){
        Long id = jwtUtil.getIdFromAuth(auth);
        log.info("{}:logout request", id);

        return memberService.logout(id, jwtUtil.getToken(auth));
    }
}
