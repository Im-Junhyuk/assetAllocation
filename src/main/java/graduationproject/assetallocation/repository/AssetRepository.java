package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findAll();
    Optional<Asset> findByName(String assetName);
}
