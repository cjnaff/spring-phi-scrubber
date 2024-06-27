package org.example.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Entity(name="people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PersonDTO implements Comparable<PersonDTO> {


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
    @Column(columnDefinition = "DATE")
    private LocalDate dob;


    public PersonDTO(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static Comparator<PersonDTO> PERSON_COMPARATOR= Comparator.comparing(PersonDTO::getFirstName)
            .thenComparing(PersonDTO::getLastName)
            .thenComparing(PersonDTO::getDob);

    @Override
    public int compareTo(PersonDTO o) {
        int i = this.firstName.compareTo(o.firstName);
        if(i!=0)  {
            return i;
        }
        i = this.lastName.compareTo(o.lastName);
        if(i!=0) {
            return i;
        }
        i =  this.dob.compareTo(o.dob);
        return i;

    }
}
