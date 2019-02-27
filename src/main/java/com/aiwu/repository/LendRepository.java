package com.aiwu.repository;

import com.aiwu.bean.House;
import com.aiwu.bean.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LendRepository extends CrudRepository<Lend, String>,JpaRepository<Lend, String>,PagingAndSortingRepository<Lend,String>,JpaSpecificationExecutor<Lend> {

    // 关于spring data repository : https://blog.csdn.net/youngsend/article/details/51832581
    // 官方文档 : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details

}
