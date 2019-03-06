package com.aiwu.repository;

import com.aiwu.bean.House;
import com.aiwu.bean.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, String>,JpaRepository<Picture, String>,PagingAndSortingRepository<Picture,String>,JpaSpecificationExecutor<Picture> {

    // 关于spring data repository : https://blog.csdn.net/youngsend/article/details/51832581
    // 官方文档 : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
    List<String> findAllByHouseid(int houseid);
}
