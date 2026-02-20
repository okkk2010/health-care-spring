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
public class ResponseAppointmentDTO {
    private Long appointmentCode;

    private String doctorEmail;
    private String doctorName;
    private String doctorSpecialty;

    private String patientEmail;

    private OffsetDateTime appointmentTime;

    private String title;
    private String detail;
}
