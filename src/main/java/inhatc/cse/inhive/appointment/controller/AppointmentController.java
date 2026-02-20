package inhatc.cse.inhive.appointment.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inhatc.cse.inhive.appointment.dto.BookAppointmentDTO;
import inhatc.cse.inhive.appointment.service.AppointmentService;
import inhatc.cse.inhive.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/book")
    public Response<Void> bookAppointment(@RequestBody BookAppointmentDTO bookAppointmentDTO) {
        return appointmentService.bookAppointment(bookAppointmentDTO);
    }
}
