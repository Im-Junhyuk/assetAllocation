package graduationproject.assetallocation;

import graduationproject.assetallocation.domain.Asset;
import graduationproject.assetallocation.domain.Authority;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.repository.AssetRepository;
import graduationproject.assetallocation.repository.AuthorityJPARepository;
import graduationproject.assetallocation.repository.MemberRepository;
import graduationproject.assetallocation.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandLineRunnerInitializer implements CommandLineRunner {

    private final AssetRepository assetRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AuthorityJPARepository authorityJPARepository;
    @Override
    public void run(String... args) throws Exception {
        String[] assetArray = {"SPY", "QQQ","VT", "VEU", "EFA", "EEM", "VWO", "IWD", "IFW"
                , "IWM", "IWN", "IWO", "MTUM","SCZ", "XLE", "XLB", "XLI", "XLY", "XLP", "XLV", "XLF"
                , "XLK", "XLU", "VGK", "EZU", "EWJ" // STOCK LIST
                , "IEF", "TLT", "TIP", "LQD", "HYG", "BNDX", "EMB", "BWX" // BOND LIST
                , "GSG", "PDBC", "GLD", "VNQ", "IYR", "SCHH", "REM", "RWX" // ALTERNATIVE
        };

        for (String assetName : assetArray){
            Asset asset = new Asset(assetName);
            assetRepository.save(asset);
        }

        Authority authority1 = new Authority("ROLE_ADMIN");
        Authority authority2 = new Authority("ROLE_USER");

        authorityJPARepository.save(authority1);
        authorityJPARepository.save(authority2);

        MemberDTO memberDTO1 = MemberDTO.builder()
                .loginId("admin")
                .password("admin")
                .build();
        memberService.signupAdmin(memberDTO1);

        MemberDTO memberDTO2 = MemberDTO.builder()
                .loginId("user1")
                .password("user1")
                .build();
        memberService.signup(memberDTO2);

        MemberDTO memberDTO3 = MemberDTO.builder()
                .loginId("user2")
                .password("user2")
                .build();

        memberService.signup(memberDTO3);
    }
}
