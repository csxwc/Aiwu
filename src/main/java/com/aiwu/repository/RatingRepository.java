package com.aiwu.repository;

import com.aiwu.bean.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

    Rating findByUserIdAndHouseId(Integer userId, Integer houseId);

}
