package inhatc.cse.inhive.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import inhatc.cse.inhive.appointment.dto.BookAppointmentDTO;
import inhatc.cse.inhive.appointment.dto.DoctorAppointmentDTO;
import inhatc.cse.inhive.appointment.dto.InsertAppointmentDTO;
import inhatc.cse.inhive.appointment.dto.ResponseAppointmentDTO;
import inhatc.cse.inhive.appointment.entity.AppointmentEntity;
import inhatc.cse.inhive.appointment.repository.AppointmentRepository;
import inhatc.cse.inhive.appointment_comment.entity.AppointmentCommentEntity;
import inhatc.cse.inhive.appointment_comment.repository.AppointmentCommentRepository;
import inhatc.cse.inhive.exceptions.BadRequestException;
import inhatc.cse.inhive.mapper.AppointmentMapper;
import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.specialty.entity.SpecialtyEntity;
import inhatc.cse.inhive.specialty.repository.SpecialtyRepository;
import inhatc.cse.inhive.user.entity.UserEntity;
import inhatc.cse.inhive.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final SpecialtyRepository specialtyRepository;
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

    public Response<List<ResponseAppointmentDTO>> getMyAppointments(String email) {
        
        List<ResponseAppointmentDTO> responseAppointments = new ArrayList<>();

        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByPatientEmail(email);
        for(AppointmentEntity appointment : appointmentEntities) {
            ResponseAppointmentDTO responseAppDTO = new ResponseAppointmentDTO();
            responseAppDTO.setAppointmentCode(appointment.getAppointmentCode());
            responseAppDTO.setDoctorEmail(appointment.getDoctorEmail());
            responseAppDTO.setPatientEmail(appointment.getPatientEmail());
            responseAppDTO.setAppointmentTime(appointment.getAppointmentTime());
            

            UserEntity doctor = userRepository.findByEmailAndRoleCode(appointment.getDoctorEmail(), 102L)
                    .orElseThrow(() -> new BadRequestException("의사 정보를 찾을 수 없습니다."));
            responseAppDTO.setDoctorName(doctor.getName());

            SpecialtyEntity specialtyEntity = specialtyRepository.findByCode(doctor.getSpecialtyCode())
                    .orElseThrow(() -> new BadRequestException("전문 분야 정보를 찾을 수 없습니다."));

            responseAppDTO.setDoctorSpecialty(specialtyEntity.getName());

            AppointmentCommentEntity appointmentCommentEntity = appointmentCommentRepository.findByAppointmentCode(appointment.getAppointmentCode())
                    .orElseThrow(() -> new BadRequestException("예약 상세 정보를 찾을 수 없습니다."));
            responseAppDTO.setTitle(appointmentCommentEntity.getTitle());
            responseAppDTO.setDetail(appointmentCommentEntity.getDetail());

            responseAppointments.add(responseAppDTO);
        }

        return Response.<List<ResponseAppointmentDTO>>builder()
                .statusCode(200)
                .message("예약 목록 조회 성공")
                .data(responseAppointments)
                .build();
    }

    public Response<List<DoctorAppointmentDTO>> getDoctorAppointments(String email) {
        List<DoctorAppointmentDTO> doctorAppointments = new ArrayList<>();

        List<AppointmentEntity> appointmentEntities = appointmentRepository.findByDoctorEmail(email);
        for(AppointmentEntity appointment : appointmentEntities) {
            DoctorAppointmentDTO doctorAppDTO = new DoctorAppointmentDTO();
            doctorAppDTO.setAppointmentCode(appointment.getAppointmentCode());
            doctorAppDTO.setPatientEmail(appointment.getPatientEmail());
            doctorAppDTO.setAppointmentTime(appointment.getAppointmentTime());

            UserEntity patient = userRepository.findByEmailAndRoleCode(appointment.getPatientEmail(), 101L)
                    .orElseThrow(() -> new BadRequestException("환자 정보를 찾을 수 없습니다."));
            doctorAppDTO.setPatientName(patient.getName());

            AppointmentCommentEntity appointmentCommentEntity = appointmentCommentRepository.findByAppointmentCode(appointment.getAppointmentCode())
                    .orElseThrow(() -> new BadRequestException("예약 상세 정보를 찾을 수 없습니다."));
            doctorAppDTO.setTitle(appointmentCommentEntity.getTitle());
            doctorAppDTO.setDetail(appointmentCommentEntity.getDetail());

            doctorAppointments.add(doctorAppDTO);
        }

        return Response.<List<DoctorAppointmentDTO>>builder()
                .statusCode(200)
                .message("의사 예약 목록 조회 성공")
                .data(doctorAppointments)
                .build();
    }
}
