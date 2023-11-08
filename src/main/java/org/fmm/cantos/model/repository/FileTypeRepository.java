package org.fmm.cantos.model.repository;

import java.util.List;
import java.util.Optional;

import org.fmm.cantos.model.entity.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
    @Query("SELECT DISTINCT tf FROM FileType tf "
    		+ "ORDER BY tf.typeName")
    List<FileType> listFileType();
    
//    List<Canto> findByOrderByDateAsc();
    
    Optional<FileType> findById(Integer id);
}
