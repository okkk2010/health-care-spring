package inhatc.cse.inhive.appointment.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookAppointmentDTO {
    private String doctorEmail;
    private String patientEmail;
    private OffsetDateTime appointmentTime;

    private String title;
    private String detail;
}
