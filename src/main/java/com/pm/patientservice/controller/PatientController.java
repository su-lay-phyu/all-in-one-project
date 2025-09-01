package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name="Patient",description = "API for meanaging Patients")
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> responses = patientService.getPatients();
        return ResponseEntity.ok().body(responses);
    }

    @PostMapping
    @Operation(summary = "Create a new Pateint")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO dto = patientService.createPatient(requestDTO);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO dto = patientService.updatePatient(id, requestDTO);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient")
    public ResponseEntity<?>deletePatient(@PathVariable UUID id)
    {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted");
    }

}
