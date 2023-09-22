package graduationproject.assetallocation.controller;

import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AssetRepository assetRepository;

    @GetMapping("/Assets")
    List<Asset> findAll(){
        log.info("findAllAsset");
        return assetRepository.findAll();
    }
}
