package inhatc.cse.inhive.specialty.service;

import java.util.List;

import org.springframework.stereotype.Service;

import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.exceptions.BadRequestException; 
import inhatc.cse.inhive.specialty.entity.SpecialtyEntity;
import inhatc.cse.inhive.specialty.repository.SpecialtyRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public Response<List<String>> getAllSpecialties() {
        List<String> specialties = specialtyRepository.allSpecialtyNames();

        return Response.<List<String>>builder()
                .statusCode(200)
                .message("전공 목록 조회 성공")
                .data(specialties)
                .build();
    }

    public Response<String> getSpecialtyByCode(String code) {
        SpecialtyEntity specialty = specialtyRepository.findByCode(Long.valueOf(code))
                            .orElseThrow(() -> new BadRequestException("전공 코드에 해당하는 전공을 찾을 수 없습니다."));

        return Response.<String>builder()
                .statusCode(200)
                .message("전공 조회 성공")
                .data(specialty.getName())
                .build();
    }
}
