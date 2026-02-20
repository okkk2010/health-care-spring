package inhatc.cse.inhive.appointment.service;

import org.springframework.stereotype.Service;

import inhatc.cse.inhive.appointment.dto.BookAppointmentDTO;
import inhatc.cse.inhive.appointment.dto.InsertAppointmentDTO;
import inhatc.cse.inhive.appointment.entity.AppointmentEntity;
import inhatc.cse.inhive.appointment.repository.AppointmentRepository;
import inhatc.cse.inhive.appointment_comment.entity.AppointmentCommentEntity;
import inhatc.cse.inhive.appointment_comment.repository.AppointmentCommentRepository;
import inhatc.cse.inhive.exceptions.BadRequestException;
import inhatc.cse.inhive.mapper.AppointmentMapper;
import inhatc.cse.inhive.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentCommentRepository appointmentCommentRepository;
    private final AppointmentMapper appointmentMapper;
    
    public Response<Void> bookAppointment(BookAppointmentDTO bookAppointmentDTO) {

        
        AppointmentEntity appointmentEntity = appointmentMapper.bookAppointmentDTOtoAppointmentEntity(bookAppointmentDTO);
        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);

        if(savedAppointment == null) {
            throw new BadRequestException("예약에 실패하였습니다.");
        }

        Long appointmentCode = appointmentRepository.findByIdForCode(savedAppointment.getId());
        if (appointmentCode == null) {
            throw new BadRequestException("예약 코드 생성에 실패하였습니다.");
        }

        AppointmentCommentEntity appointmentCommentEntity = appointmentMapper.bookAppointmentDTOtoAppointmentCommentEntity(bookAppointmentDTO, appointmentCode);
        appointmentCommentRepository.save(appointmentCommentEntity);

        return Response.<Void>builder()
                .statusCode(200)
                .message("예약이 성공적으로 완료되었습니다.")
                .build();
    }
}
