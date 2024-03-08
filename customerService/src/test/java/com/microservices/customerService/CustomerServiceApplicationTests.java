package com.microservices.customerService;

import com.microservices.customerService.dto.Rating;
import com.microservices.customerService.dto.Response;
import com.microservices.customerService.feign.RatingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest
class CustomerServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void testCreateRating() {
		Rating rating = Rating.builder().customerId(4).hotelId(1).rating(4).feedbacks("This is created by feign client").build();
		ResponseEntity<Response> response = ratingService.createRating(rating);
		Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode(),"Rating not created");
	}

}
