package graduationproject.assetallocation;

import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.repository.AssetRepository;
import graduationproject.assetallocation.repository.MemberRepository;
import graduationproject.assetallocation.service.MemberService;
import org.springframework.stereotype.Component;


public class Initializer {

    private final AssetRepository assetRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Initializer(AssetRepository assetRepository, MemberRepository memberRepository
    , MemberService memberService){
        this.assetRepository = assetRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;

        String[] assetArray = {"SPY", "QQQ","VT", "VEU", "EFA", "EEM", "VWO", "IWD", "IFW"
        , "IWM", "IWN", "IWO", "MTUM","SCZ", "XLE", "XLB", "XLI", "XLY", "XLP", "XLV", "XLF"
        , "XLK", "XLU", "VGK", "EZU", "EWJ" // STOCK LIST
        , "IEF", "TLT", "TIP", "LQD", "HYG", "BNDX", "EMB", "BWX" // BOND LIST
        , "GSG", "PDBC", "GLD", "VNQ", "IYR", "SCHH", "REM", "RWX" // ALTERNATIVE
        };

        for (String assetName : assetArray){
            Asset asset = new Asset(assetName);
            save(asset);
        }
//
//        MemberDTO memberDTO = MemberDTO.builder()
//                .loginId("user1")
//                .password("user1")
//                .build();
//
//        memberService.signup(memberDTO);
    }



    private void save(Asset asset){
        assetRepository.save(asset);
    }
}
