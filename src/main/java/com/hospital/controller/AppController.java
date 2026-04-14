package com.hospital.controller;

import com.hospital.service.PatientService;
import com.hospital.service.DoctorService;
import com.hospital.service.AppointmentService;
import com.hospital.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("patientCount", patientService.getCount());
        model.addAttribute("doctorCount", doctorService.getCount());
        model.addAttribute("appointmentCount", appointmentService.getCount());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("patientCount", patientService.getCount());
        model.addAttribute("doctorCount", doctorService.getCount());
        model.addAttribute("appointmentCount", appointmentService.getCount());
        model.addAttribute("recordCount", medicalRecordService.getAllRecords().size());
        return "dashboard";
    }
}
