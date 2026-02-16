package inhatc.cse.inhive.user.service;


import org.springframework.stereotype.Service;
import inhatc.cse.inhive.mapper.UserMapper;
import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.user.dto.DoctorDTO;
import inhatc.cse.inhive.user.dto.PatientDTO;
import inhatc.cse.inhive.user.entity.UserEntity;
import inhatc.cse.inhive.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import inhatc.cse.inhive.exceptions.BadRequestException;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Response<Void> registerDoctor(DoctorDTO doctorDTO) 
    {
        if(userRepository.findByEmail(doctorDTO.getEmail()).isPresent()) {
            throw new BadRequestException("이미 존재하는 이메일입니다.");
        }
        
        UserEntity userEntity = userMapper.toEntity(doctorDTO);
        userRepository.save(userEntity);

        return Response.<Void>builder()
                .statusCode(201)
                .message("의사 등록 성공")
                .build();
    }
}
