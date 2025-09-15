package com.green;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrjJpa03ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	// 단위(Unit) 테스트를 위한 코드 작성
	@Test
	public void test1() {
		double n1 = 7;
		double n2 = 3;
		double n = n1/n2;
		System.out.println(n);
		
		assertEquals(n, 3.5);
	}

}
