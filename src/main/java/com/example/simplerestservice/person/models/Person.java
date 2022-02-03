package com.example.simplerestservice.person.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"personalId", "birthdate"})
@ToString(of = {"firstName", "lastName"})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "personal_id")
    @NotBlank(message = "Personal ID is mandatory")
    private String personalId;

    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    /*
     *   As per ISO/IEC 5218:
     *   0 - not known
     *   1 - male
     *   2 - female
     *   9 - not applicable
     */
    @Column
    @NotBlank(message = "Gender is mandatory")
    private Integer gender;

    @Column
    @NotBlank(message = "Birthdate is mandatory")
    private LocalDate birthdate;

}
