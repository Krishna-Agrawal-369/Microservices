package com.microoservices.hotelService.services;

import com.microoservices.hotelService.dto.Hotel;
import com.microoservices.hotelService.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    void addHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    ResponseEntity<Response> getHotelById(int id);

}
