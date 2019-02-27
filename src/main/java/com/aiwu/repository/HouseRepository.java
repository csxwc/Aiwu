package com.aiwu.repository;

import com.aiwu.bean.House;
import com.aiwu.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HouseRepository extends CrudRepository<House, String>,JpaRepository<House, String>,PagingAndSortingRepository<House,String>,JpaSpecificationExecutor<House> {

    // 关于spring data repository : https://blog.csdn.net/youngsend/article/details/51832581
    // 官方文档 : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
    House findAllById(String id);
}
