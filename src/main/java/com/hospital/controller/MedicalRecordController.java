package com.hospital.controller;

import com.hospital.model.MedicalRecord;
import com.hospital.service.MedicalRecordService;
import com.hospital.service.PatientService;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/records")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listRecords(Model model) {
        model.addAttribute("records", medicalRecordService.getAllRecords());
        return "records";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        MedicalRecord record = new MedicalRecord();
        record.setPatient(new com.hospital.model.Patient());
        record.setDoctor(new com.hospital.model.Doctor());
        model.addAttribute("record", record);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "record-form";
    }

    @PostMapping("/save")
    public String saveRecord(@ModelAttribute("record") MedicalRecord record) {
        medicalRecordService.saveRecord(record);
        return "redirect:/records";
    }

    @GetMapping("/patient/{id}")
    public String listPatientRecords(@PathVariable Long id, Model model) {
        model.addAttribute("records", medicalRecordService.getRecordsByPatientId(id));
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient-records";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
        return "redirect:/records";
    }
}
