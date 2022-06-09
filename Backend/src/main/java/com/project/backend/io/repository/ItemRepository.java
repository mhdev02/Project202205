package com.project.backend.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.backend.io.entity.ItemEntity;
import com.project.backend.io.entity.UserEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
	
	List<ItemEntity> findAll();
	
	List<ItemEntity> findAllBySeller(UserEntity userEntity);

	ItemEntity findByItemId(String itemId);
}