package ch.bbw.m133personenverwaltung.model;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter

    private Long id;

    @NotNull
    @Size(min = 1, max = 5)
    @Getter
    @Setter

    private String firstname;

    @NotNull
    @Size(min = 2, max = 20)
    @Getter
    @Setter

    private String lastname;

    @NotNull
    @Size(min = 2, max = 20)
    @Getter
    @Setter

    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Size(min = 10, max = 70)
    @Getter
    @Setter

    char gender;

    @Email
    @Getter
    @Setter

    private LocalDate birthdate;

    @Transient
    @Getter
    @Setter

    private String today;


}

