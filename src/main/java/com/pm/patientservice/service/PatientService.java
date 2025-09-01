package com.pm.patientservice.service;


import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    public List<PatientResponseDTO> getPatients();

    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO);

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO requestDTO);

    void deletePatient(java.util.UUID id);
}
