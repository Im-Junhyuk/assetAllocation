package graduationproject.assetallocation.util;

import graduationproject.assetallocation.jwt.TokenProvider;
import graduationproject.assetallocation.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    public String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    public Long extractId(String token){
        String loginId = tokenProvider.extractLoginId(token);
        return memberRepository.findByLoginId(loginId).get().getId();
    }
}
