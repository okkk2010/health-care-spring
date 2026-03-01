package inhatc.cse.inhive.recording.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import inhatc.cse.inhive.recording.service.RecordingService;
import inhatc.cse.inhive.response.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recordings")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RecordingController {
    final RecordingService recordingService;

    @Value("${recording.upload-dir:uploads/recordings}")
    private String uploadDir;
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<Map<String, Object>>> upload(@RequestParam("file") MultipartFile file, @RequestParam("appointmentCode") String appointmentCode)
    {
        return ResponseEntity.ok(recordingService.upload(uploadDir, file, appointmentCode));
    }
}
