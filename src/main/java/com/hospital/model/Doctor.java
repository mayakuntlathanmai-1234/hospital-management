package com.hospital.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;
    private int experience;
    private String email;

    public Doctor(String name, String specialization, int experience, String email) {
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.email = email;
    }
}
