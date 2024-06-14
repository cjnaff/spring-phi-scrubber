package org.example.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name="people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PersonDTO {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    private String lastName;

//    @Column(nullable = false)
    private LocalDate dob;


    public PersonDTO(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
