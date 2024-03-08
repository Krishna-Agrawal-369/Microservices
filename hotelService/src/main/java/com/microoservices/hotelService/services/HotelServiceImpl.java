package com.microoservices.hotelService.services;

import com.microoservices.hotelService.dto.Hotel;
import com.microoservices.hotelService.dto.Response;
import com.microoservices.hotelService.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void addHotel(Hotel hotel) {
        log.info("Hotel Service :: addHotel()");
        this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        log.info("Hotel Service :: getAllHotels()");
        return this.hotelRepository.findAll();
    }

    @Override
    public ResponseEntity<Response> getHotelById(int id) {
        log.info("Hotel Service :: getHotelById()");
        Optional<Hotel> hotel = this.hotelRepository.findById(id);
        return hotel.map(value -> new ResponseEntity<>(new Response(true, 0, "Hotel details fetched successfully with given hotel ID : " + id, value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Response(false, -1, "Hotel not found"), HttpStatus.BAD_REQUEST));
    }
}
