package inhatc.cse.inhive.appointment.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_email")
    private String doctorEmail;
    @Column(name = "patient_email")
    private String patientEmail;
    @Column(name = "appointment_time")
    private OffsetDateTime appointmentTime;

    @Column(name = "appointment_code", insertable = false, updatable = false)
    private Long appointmentCode;
}
