package graduationproject.assetallocation;

import graduationproject.assetallocation.domain.*;
import graduationproject.assetallocation.domain.aa.Aa;
import graduationproject.assetallocation.domain.dto.AaDTO;
import graduationproject.assetallocation.domain.dto.DaaDTO;
import graduationproject.assetallocation.domain.dto.MemberDTO;
import graduationproject.assetallocation.repository.AaRepository;
import graduationproject.assetallocation.repository.AssetRepository;
import graduationproject.assetallocation.repository.AuthorityJPARepository;
import graduationproject.assetallocation.repository.MemberRepository;
import graduationproject.assetallocation.service.MemberService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static graduationproject.assetallocation.domain.aa.Daa.createDaa;
import static graduationproject.assetallocation.domain.aa.Saa.createAa;

@Component
@AllArgsConstructor
public class CommandLineRunnerInitializer implements CommandLineRunner {

    private final AssetRepository assetRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AuthorityJPARepository authorityJPARepository;
    private final AaRepository aaRepository;
    private final EntityManager em;
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

        Member signup3 = memberService.signup(memberDTO3);

        // aaAsset
        List<AaAsset> aaAssets = new ArrayList<>();
        AaAsset aaAsset1 = AaAsset.createAaAsset(assetRepository.findByName("SPY").get(), null);
        AaAsset aaAsset2 = AaAsset.createAaAsset(assetRepository.findByName("QQQ").get(), null);
        aaAssets.add(aaAsset2);
        aaAssets.add(aaAsset1);

        // aaDTO

        // aa init
//        Aa aa = createDaa(DaaDTO.builder().lastModifiedDay(LocalDate.ofEpochDay(2000-10-10))
//                .createdDay(LocalDate.ofEpochDay(1999-10-20))
//                .initialCash(2000L)
//                .rebalancingPeriod(RebalancingPeriod.Q)
//                .strategyType("abs")
//                        .build(),
//                aaAssets,
//                signup3);
//
//        aaRepository.save(aa);

        em.clear();
    }
}
