package graduationproject.assetallocation.repository;

import graduationproject.assetallocation.domain.aa.AA;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AAJpaRepository {

    private final EntityManager em;

    public void save(AA aA){
        if(aA.getId() == null) {
            em.persist(aA);
        }
        else {
            em.merge(aA);
        }
    }

    public void delete(Long aAId){
        em.remove(findById(aAId));
    }

    public AA findById(Long aAId){
        return em.find(AA.class, aAId);
    }
    // user_id를 어떻게 받는가?
//    public AA findByName(String name){
//        return findAA;
//    }
//
//    public List<AA> findAll(){
//
//    }
}
