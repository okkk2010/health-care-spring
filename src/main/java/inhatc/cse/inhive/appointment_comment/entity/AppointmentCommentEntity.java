package inhatc.cse.inhive.appointment_comment.entity;

import jakarta.annotation.Generated;
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
@Table(name = "appointment_comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_code")
    private Long appointmentCode;
    @Column(name = "title")
    private String title;
    @Column(name = "detail")
    private String detail;
}
