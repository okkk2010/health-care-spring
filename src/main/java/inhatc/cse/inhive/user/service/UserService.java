package inhatc.cse.inhive.user.service;

import org.springframework.stereotype.Service;

import inhatc.cse.inhive.exceptions.BadRequestException;
import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.user.dto.LoginRequestDTO;
import inhatc.cse.inhive.user.dto.LoginResponseDTO;
import inhatc.cse.inhive.user.entity.UserEntity;
import inhatc.cse.inhive.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    
    public Response<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 이메일입니다."));

        if (!userEntity.getPassword().equals(loginRequestDTO.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .roleCode(userEntity.getRoleCode())
                .build();

        return Response.<LoginResponseDTO>builder()
                .statusCode(200)
                .message("로그인 성공")
                .data(responseDTO)
                .build();
    }
}
