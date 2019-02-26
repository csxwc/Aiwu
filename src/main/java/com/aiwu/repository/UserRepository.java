package com.aiwu.repository;

import com.aiwu.bean.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    // 关于spring data repository : https://blog.csdn.net/youngsend/article/details/51832581
    // 官方文档 : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details

    User findByUsername(String name);

    // 按id逆序返回users
    List<User> findByUsernameOrderByIdDesc(String name);
}
