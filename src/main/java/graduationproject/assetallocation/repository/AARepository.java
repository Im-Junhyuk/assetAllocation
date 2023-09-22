package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.aa.AA;
import graduationproject.assetallocation.domain.aa.SAA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AARepository extends JpaRepository<AA, Long> {

    Optional<AA> findByIdAndMember(Long id, Member member);

    List<AA> findByMember(Member member);

    void deleteByMember(Member member);

    void deleteById(Long id);

    List<AA> findAll();
}
