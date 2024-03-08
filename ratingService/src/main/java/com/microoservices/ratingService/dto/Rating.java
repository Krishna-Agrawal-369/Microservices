package com.microoservices.ratingService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Rating implements Serializable {
    @Serial
    private static final long serialVersionUID = 871491611998041161L;
    @Id
    private int ratingId;
    private int customerId;
    private int hotelId;
    @Min(1)
    @Max(5)
    private int rating;
    @NotEmpty(message = "Text cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{5,255}$", message = "Input string length must be greater than 5 and string must be alphanumeric only")
    private String feedbacks;


}
