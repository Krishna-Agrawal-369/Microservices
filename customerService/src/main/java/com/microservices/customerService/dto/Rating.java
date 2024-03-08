package com.microservices.customerService.dto;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating implements Serializable {
    @Serial
    private static final long serialVersionUID = 871491611998041161L;
    private int ratingId;
    private int customerId;
    private int hotelId;
    @Min(1)
    @Max(5)
    private int rating;
    @NotEmpty(message = "Text cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{5,255}$", message = "Input string length must be greater than 5 and string must be alphanumeric only")
    private String feedbacks;
    private Hotel hotel;
}
