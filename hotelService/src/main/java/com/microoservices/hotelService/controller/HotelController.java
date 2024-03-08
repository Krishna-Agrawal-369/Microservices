package com.microoservices.hotelService.controller;

import com.microoservices.hotelService.dto.Hotel;
import com.microoservices.hotelService.dto.Response;
import com.microoservices.hotelService.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/getAllHotels")
    public ResponseEntity<Response> getAllHotels() {
        log.info("Hotel Controller :: getAllHotels()");
        return new ResponseEntity<>(new Response(true, 0, "Hotels details fetched successfully", this.hotelService.getAllHotels()), HttpStatus.OK);
    }

    @GetMapping("/getHotelById/{id}")
    public ResponseEntity<Response> getHotelById(@PathVariable int id) {
        log.info("Hotel Controller :: getHotelById()");
        return this.hotelService.getHotelById(id);
    }

    @PostMapping("/save/hotel")
    private ResponseEntity<Response> saveHotel(@RequestBody Hotel hotel) {
        log.info("Hotel Controller :: saveHotel()");
        this.hotelService.addHotel(hotel);
        return new ResponseEntity<>(new Response(true,0,"Hotel added successfully"),HttpStatus.OK);
    }

}
