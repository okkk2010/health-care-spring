package inhatc.cse.inhive.appointment.repository;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import inhatc.cse.inhive.appointment.entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findAll();

    @Query("SELECT a.appointmentCode FROM AppointmentEntity a WHERE a.id = :id")
    Long findByIdForCode(@Param("id")Long id);

    List<AppointmentEntity> findByDoctorEmail(String doctorEmail);
    List<AppointmentEntity> findByPatientEmail(String patientEmail);

}
