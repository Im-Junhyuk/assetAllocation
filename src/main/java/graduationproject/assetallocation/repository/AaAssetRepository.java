package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.AaAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AaAssetRepository extends JpaRepository<AaAsset, Long> {
    void deleteById(Long id);

}
