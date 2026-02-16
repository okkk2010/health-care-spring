package inhatc.cse.inhive.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.user.dto.PatientDTO;
import inhatc.cse.inhive.user.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<Response<Void>> registerPatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.registerPatient(patientDTO));
    }
}
