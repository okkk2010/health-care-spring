package inhatc.cse.inhive.specialty.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.specialty.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/specialties")
@RequiredArgsConstructor
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping("/all")
    public ResponseEntity<Response<List<String>>> getAllSpecialties() {
        return ResponseEntity.ok(specialtyService.getAllSpecialties());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Response<String>> getSpecialtyByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(specialtyService.getSpecialtyByCode(code));
    }
}
