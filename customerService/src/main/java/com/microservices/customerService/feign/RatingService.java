package com.microservices.customerService.feign;

import com.microservices.customerService.dto.Rating;
import com.microservices.customerService.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating/create/rating")
    ResponseEntity<Response> createRating(@RequestBody Rating rating);

}
