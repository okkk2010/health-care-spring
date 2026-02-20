package inhatc.cse.inhive.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import inhatc.cse.inhive.appointment.dto.BookAppointmentDTO;
import inhatc.cse.inhive.appointment.dto.InsertAppointmentDTO;
import inhatc.cse.inhive.appointment.entity.AppointmentEntity;
import inhatc.cse.inhive.appointment_comment.entity.AppointmentCommentEntity;
import inhatc.cse.inhive.user.dto.DoctorDTO;
import inhatc.cse.inhive.user.dto.PatientDTO;
import inhatc.cse.inhive.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {
    private final ModelMapper modelMapper;

    public AppointmentEntity bookAppointmentDTOtoAppointmentEntity(BookAppointmentDTO bookAppointmentDTO) {
        if (bookAppointmentDTO == null) {
            return null;
        }
        
        return modelMapper.map(bookAppointmentDTO, AppointmentEntity.class);
    }

    public AppointmentCommentEntity bookAppointmentDTOtoAppointmentCommentEntity(BookAppointmentDTO bookAppointmentDTO, Long appointmentCode) {
        if (bookAppointmentDTO == null || appointmentCode == null) {
            return null;
        }

        AppointmentCommentEntity appointmentCommentEntity = AppointmentCommentEntity.builder()
                .title(bookAppointmentDTO.getTitle())
                .detail(bookAppointmentDTO.getDetail())
                .appointmentCode(appointmentCode)
                .build();
        return appointmentCommentEntity;
    }
}
