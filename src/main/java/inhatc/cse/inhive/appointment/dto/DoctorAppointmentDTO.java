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
public class DoctorAppointmentDTO {
    private Long appointmentCode;

    private String patientEmail;
    private String patientName;

    private OffsetDateTime appointmentTime;
    private String title;
    private String detail;
}
