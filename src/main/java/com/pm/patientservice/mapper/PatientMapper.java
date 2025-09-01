package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAddress(request.getAddress());
        patient.setEmail(request.getEmail());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(request.getRegisteredDate()));
        return patient;
    }
}