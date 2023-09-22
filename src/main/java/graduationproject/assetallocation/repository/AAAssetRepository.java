package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.AAAsset;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AAAssetRepository extends JpaRepository<AAAsset, Long> {
    void deleteById(Long id);

}
