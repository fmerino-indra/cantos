package org.fmm.cantos.model.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.cantos.model.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
    @Query("SELECT DISTINCT l FROM Label l "
    		+ "ORDER BY l.label")
    List<Label> listCantos();
    
//    List<Canto> findByOrderByDateAsc();
    
    Optional<Label> findById(Integer id);
    Optional<Label> findByLabel(String label);
}
