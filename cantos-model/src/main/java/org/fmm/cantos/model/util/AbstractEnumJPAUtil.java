package org.fmm.cantos.model.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fmm.cantos.model.entity.MasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.annotation.PostConstruct;

public class AbstractEnumJPAUtil<E extends MasterEntity, R extends JpaRepository<E, Integer>> {
	
	@Autowired
	private R repository;
	
	private List<E> list;
	
	private Map<String, E> classMap;
	private Map<Integer, E> idMap;
	
	@PostConstruct
	public void init() {
		list = repository.findAll();
		initMaps();
	}

	private void initMaps() {
		classMap = new HashMap<String, E>();
		idMap = new HashMap<Integer, E>();
		
		for (E element: list) {
			classMap.put(element.getName(), element);
			idMap.put(element.getId(), element);
		}
	}
	
	public E from(String aux) {
		return classMap.get(aux);
	}
	
	public E from(Integer aux) {
		return idMap.get(aux);
	}
}
