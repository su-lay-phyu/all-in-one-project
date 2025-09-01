package com.pm.patientservice.service.impl;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOS = patients.stream().map(PatientMapper::toDTO).toList();
        return patientResponseDTOS;
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {
        if (patientRepository.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists" + requestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(requestDTO));

        return PatientMapper.toDTO(newPatient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO requestDTO) {
        Patient foundedPatient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not Found with ID :" + id));
        if (patientRepository.existsByEmailAndIdNot(requestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists" + requestDTO.getEmail());
        }
        foundedPatient.setName(requestDTO.getName());
        foundedPatient.setEmail(requestDTO.getEmail());
        foundedPatient.setAddress(requestDTO.getAddress());
        foundedPatient.setDateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth()));
        foundedPatient.setRegisteredDate(LocalDate.parse(requestDTO.getRegisteredDate()));
        Patient updatedPatient = patientRepository.save(foundedPatient);
        return PatientMapper.toDTO(updatedPatient);


    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
