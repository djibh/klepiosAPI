package com.jiera.klepiosAPI.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jiera.klepiosAPI.domain.Patient;
import com.jiera.klepiosAPI.domain.HttpResponse;
import com.jiera.klepiosAPI.dto.PatientDTO;
import com.jiera.klepiosAPI.service.PatientService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RestControllerAdvice
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/patient")
    public ResponseEntity<HttpResponse> savepatient(@RequestBody Patient patient) {
        PatientDTO patientDTO = patientService.createPatient(patient);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", patientDTO))
                        .message("patient created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    private URI getUri() {
        return URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getpatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }
}
