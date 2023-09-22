package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.Member;
import graduationproject.assetallocation.domain.aa.Aa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AaRepository extends JpaRepository<Aa, Long> {

    Optional<Aa> findByIdAndMember(Long id, Member member);

    List<Aa> findByMember(Member member);

    void deleteByMember(Member member);

    void deleteById(Long id);

    List<Aa> findAll();
}
