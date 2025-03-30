package org.fmm.cantos;

import org.fmm.cantos.model.entity.FileType;
import org.fmm.cantos.model.util.FileTypeUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

//@DataJpaTest(showSql = true)
@SpringBootTest
class EnumUtilTests {
	private static final Logger logger = LoggerFactory.getLogger(EnumUtilTests.class);

	@Autowired
	private FileTypeUtil fileTypeUtil;
	
	@Test
	void guardarCanto() {
		FileType ft = null;
		ft = fileTypeUtil.from("catecumenado");
		Assert.notNull(ft, "Algo fue mal");
	}
}
