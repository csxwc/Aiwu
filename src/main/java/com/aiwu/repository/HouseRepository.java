package com.aiwu.repository;

import com.aiwu.bean.House;
import com.aiwu.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface HouseRepository extends CrudRepository<House, String>,JpaRepository<House, String>,PagingAndSortingRepository<House,String>,JpaSpecificationExecutor<House> {

    // 关于spring data repository : https://blog.csdn.net/youngsend/article/details/51832581
    // 官方文档 : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
    House findAllById(int id);
    Page<House> findAllByCity(String city,@PageableDefault(page=0, size=10000)Pageable pageable);
    Page<House> findAllByBedAndCity(int bed,String city,@PageableDefault(page=0, size=10000)Pageable pageable);
    Page<House> findAllByToiletAndCity(int toiet,String city,@PageableDefault(page=0, size=10000)Pageable pageable);
    Page<House> findAllByGuestAndCity(int guest,String city,@PageableDefault(page=0, size=10000)Pageable pageable);
    Page<House> findAllByTypeAndCity(String type,String city,@PageableDefault(page=0, size=10000)Pageable pageable);
    Page<House> findByCityAndRoom(String city,int room,@PageableDefault(page=0, size=10000)Pageable pageable);

    List<House> findAllByCity(String city);
    List<House> findAllByBedAndCity(int bed,String city);
    List<House> findAllByToiletAndCity(int toiet,String city);
    List<House> findAllByGuestAndCity(int guest,String city);
    List<House> findAllByTypeAndCity(String type,String city);
    List<House> findByCityAndRoom(String city,int room);
}
