package com.microoservices.ratingService.repository;

import com.microoservices.ratingService.dto.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating,Integer> {
    List<Rating> findByCustomerId(int customerId);
    List<Rating> findByHotelId(int hotelId);

}
