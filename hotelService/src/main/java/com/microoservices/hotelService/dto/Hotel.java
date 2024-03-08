package com.microoservices.hotelService.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Hotel implements Serializable {
    @Serial
    private static final long serialVersionUID = 4517001996195541173L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int hotelId;
    @NotEmpty(message = "Name cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z\s]{3,255}$", message = "Input string length must be greater than 3 and string must be alphabetic only")
    private String name;
    @NotEmpty(message = "Text cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{3,255}$", message = "Input string length must be greater than 3 and string must be alphanumeric only")
    private String location;
    @NotEmpty(message = "Text cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{5,255}$", message = "Input string length must be greater than 5 and string must be alphanumeric only")
    private String about;
}
