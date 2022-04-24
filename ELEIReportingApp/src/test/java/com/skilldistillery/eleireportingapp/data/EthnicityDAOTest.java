package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class EthnicityDAOTest {
	
	@Autowired
	private EthnicityDAOImpl dao;

	@Test
	@DisplayName("Testing DAO was autowired")
	void test1() {
		assertNotNull(dao);
	}
	
	@Test
	@DisplayName("Testing DAO find id 1")
	void test2() {
		assertNotNull(dao);
		assertNotNull(dao.findById(1));
		
	}

}
