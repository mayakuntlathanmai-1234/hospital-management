package com.hospital.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Doctor doctor;
    
    @Column(length = 2000)
    private String diagnosis;
    
    @Column(length = 2000)
    private String treatment;
    
    @Column(length = 2000)
    private String notes;
    
    private LocalDateTime recordDate;

    public MedicalRecord(Patient patient, Doctor doctor, String diagnosis, String treatment, String notes, LocalDateTime recordDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.recordDate = recordDate;
    }
}
