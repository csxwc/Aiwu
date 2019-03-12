package com.aiwu.repository;

import com.aiwu.bean.Collection;
//import javolution.io.Struct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, String>,JpaRepository<Collection, String>,PagingAndSortingRepository<Collection,String>,JpaSpecificationExecutor<Collection> {

    // 关于spring data repository : https://blog.csdn.net/youngsend/article/details/51832581
    // 官方文档 : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details

    List<Collection> findAllByPersonId(int personId);

    Collection findByPersonIdAndRoomId(int personId, int roomId);

    int deleteById(Integer id);

    Collection findById(Integer id);
}
