package com.aiwu.repository;

import com.aiwu.bean.House;
import org.springframework.data.domain.Page;
import com.aiwu.bean.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RentRepository extends CrudRepository<Rent, String>,JpaRepository<Rent, String>,PagingAndSortingRepository<Rent,String>,JpaSpecificationExecutor<Rent> {

    Page<Rent> findAllById(int id, Pageable pageable);

    List<Rent> findAllByPersonid(int id);

    Page<Rent> findAllByRoomid(int id,Pageable pageable);

    List<Rent> findAll();
}
