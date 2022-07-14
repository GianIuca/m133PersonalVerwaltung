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
    @Size(min = 2, max = 220)
    @Getter
    @Setter
    String firstname;

    @NotNull
    @Size(min = 2, max = 220)
    @Getter
    @Setter
    private String lastname;

    @Email
    @Getter
    @Setter
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Getter
    @Setter
    private LocalDate birthdate;

    @NotNull
    @Getter
    @Setter
    char gender;

    @Transient
    @Getter
    @Setter
    private String today;

}

