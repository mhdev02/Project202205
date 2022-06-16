package com.project.backend.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.backend.io.entity.ImageEntity;
import com.project.backend.io.entity.ItemEntity;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
	
	ImageEntity findAllByItem(ItemEntity itemEntity);
	
	ImageEntity findByImageId(String imageId);

}