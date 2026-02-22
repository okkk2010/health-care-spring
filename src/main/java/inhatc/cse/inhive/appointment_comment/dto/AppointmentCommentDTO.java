package inhatc.cse.inhive.appointment_comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCommentDTO {
    private String title;
    private String detail;
    private Long appointmentCode;
}
