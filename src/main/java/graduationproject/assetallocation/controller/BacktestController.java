package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.service.AaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bt")
public class BacktestController {

    private final AaService aaService;
    public String SAABT(Long aAId){
        Optional<Aa> aA = aaService.findById(aAId);
        // 장고 호출

        return "ok";
    }

}
