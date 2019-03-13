package com.aiwu;

import com.aiwu.controller.HouseController;
import com.aiwu.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AiwuApplicationTests {

	@Autowired
	HouseService houseService;

	@Autowired
	HouseController houseController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testselect() {
		List<String> list = new ArrayList<>();
		list.add("热门景点");
		list.add("极致性价比");
		System.out.println(houseService.intelligentSelect("成都",80,80,40,90,list));
	}

	@Test
	public void testLenskit() {
			System.out.println(1);
	}

}
