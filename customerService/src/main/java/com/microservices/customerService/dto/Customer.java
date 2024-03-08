package com.microservices.customerService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Entity
@Table
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 5277840721767256016L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int customerId;
    @NotEmpty(message = "Name cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z\s]{3,255}$", message = "Input string length must be greater than 3 and string must be alphabetic only")
    private String name;
    @Email
    private String email;
    @NotEmpty(message = "Text cannot be null and must have size greater than 0")
    @Pattern(regexp = "^[a-zA-Z0-9\s]{5,255}$", message = "Input string length must be greater than 5 and string must be alphanumeric only")
    private String about;
    @Transient
    private List<Rating> ratings;
}
