package org.fmm.cantos.model.util;

import org.fmm.cantos.model.entity.FileType;
import org.fmm.cantos.model.repository.FileTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class FileTypeUtil extends AbstractEnumJPAUtil<FileType, FileTypeRepository> {

}
