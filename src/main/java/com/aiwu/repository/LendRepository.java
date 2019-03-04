package com.aiwu.repository;

import com.aiwu.bean.House;
import com.aiwu.bean.Lend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LendRepository extends CrudRepository<Lend, String>,JpaRepository<Lend, String>,PagingAndSortingRepository<Lend,String>,JpaSpecificationExecutor<Lend> {

    Page<Lend> findAllByPersonId(String personid, Pageable pageable);
}
