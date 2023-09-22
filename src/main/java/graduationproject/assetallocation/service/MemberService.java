package graduationproject.assetallocation.service;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void saveMember(Member member){

        memberRepository.save(member);
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }
    public void deleteMember(Long memberId){

        memberRepository.deleteById(memberId);
    }


}
