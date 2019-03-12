package com.aiwu.service;

import com.aiwu.bean.Rating;
import com.aiwu.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    public void addRating(Integer userId, Integer houseId, int score) {

        Rating rating = ratingRepository.findByUserIdAndHouseId(userId, houseId);
        if (rating != null) {
            rating.setScore(rating.getScore() + score);
        } else {
            rating = new Rating();
            rating.setUserId(userId);
            rating.setHouseId(houseId);
            rating.setScore(score);
        }
        ratingRepository.save(rating);

    }

}
