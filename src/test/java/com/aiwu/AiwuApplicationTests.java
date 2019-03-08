package com.aiwu;

import com.aiwu.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AiwuApplicationTests {

	@Autowired
	HouseService houseService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testselect() {
		System.out.println(houseService.intelligentSelect("成都", (float)0.5, (float)0.5, (float)0.5, (float)0.5));
	}

	@Test
	public void testLenskit() {
			System.out.println(1);
	}

}
