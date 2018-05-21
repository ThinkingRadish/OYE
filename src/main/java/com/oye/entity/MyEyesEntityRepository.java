package com.oye.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEyesEntityRepository extends JpaRepository<MyEyesEntity, String> {
	public MyEyesEntity findByUsername(String name);
}
