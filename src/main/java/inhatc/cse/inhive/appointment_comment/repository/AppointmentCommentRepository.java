package inhatc.cse.inhive.appointment_comment.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import inhatc.cse.inhive.appointment_comment.entity.AppointmentCommentEntity;

@Repository
public interface AppointmentCommentRepository extends JpaRepository<AppointmentCommentEntity, Long> {
    Optional<AppointmentCommentEntity> findByAppointmentCode(Long appointmentCode);
}
