package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.aa.AA;
import graduationproject.assetallocation.service.AAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bt")
public class BacktestController {

    private final AAService aAService;
    public String SAABT(Long aAId){
        Optional<AA> aA = aAService.findById(aAId);
        // 장고 호출

        return "ok";
    }

}
