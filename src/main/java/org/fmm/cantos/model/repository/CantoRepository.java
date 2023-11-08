package org.fmm.cantos.model.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.cantos.model.entity.Canto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface CantoRepository extends JpaRepository<Canto, Integer> {
    @Query("SELECT DISTINCT c FROM Canto c "
    		+ " LEFT JOIN FETCH c.labels l "
    		+ " LEFT JOIN FETCH c.mp3Urls m "
    		+ " ORDER BY c.name")
//    @QueryHints(value = {
//            @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
//            @QueryHint(name = HINT_CACHEABLE, value = "false"),
//            @QueryHint(name = HINT_READONLY, value = "true"),
//            @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")
//    })        
    List<Canto> listCantos();
    
//    List<Canto> findByOrderByDateAsc();
    
    Optional<Canto> findById(Integer id);
}
