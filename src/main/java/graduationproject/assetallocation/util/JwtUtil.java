package graduationproject.assetallocation.util;

import jakarta.servlet.http.HttpServletRequest;

public class JwtUtil {

    private JwtUtil(){}
    public static String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
}
