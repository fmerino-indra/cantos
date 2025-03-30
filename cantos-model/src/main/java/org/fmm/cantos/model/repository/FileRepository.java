package org.fmm.cantos.model.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.cantos.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    @Query("SELECT DISTINCT f FROM File f "
    		+ "ORDER BY f.name")
    List<File> listFiles();
    
    Optional<File> findById(Integer id);
}
