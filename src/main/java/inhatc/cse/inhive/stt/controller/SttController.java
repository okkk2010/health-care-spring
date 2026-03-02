package inhatc.cse.inhive.stt.controller;

import inhatc.cse.inhive.stt.service.SttService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stt")
@RequiredArgsConstructor
public class SttController {

    private final SttService sttService;

@GetMapping
public String convert(@RequestParam String filename) throws Exception {
    return sttService.transcribe(filename);
}}