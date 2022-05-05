package com.project.backend.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.backend.io.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);  // query method

}