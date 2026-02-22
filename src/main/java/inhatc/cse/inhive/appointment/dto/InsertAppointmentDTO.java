package inhatc.cse.inhive.appointment.dto;

import java.time.OffsetDateTime;

import org.jspecify.annotations.NullMarked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertAppointmentDTO {
    private String doctorEmail;
    private String patientEmail;
    private OffsetDateTime appointmentTime;
}
