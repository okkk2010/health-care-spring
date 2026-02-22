package inhatc.cse.inhive.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import inhatc.cse.inhive.exceptions.BadRequestException;
import inhatc.cse.inhive.response.Response;
import inhatc.cse.inhive.role.entity.RoleEntity;
import inhatc.cse.inhive.role.repository.RoleRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public Response<List<String>> getAllRoles() {
        List<String> roles = roleRepository.allRoleNames();

        return Response.<List<String>>builder()
                .statusCode(200)
                .message("역할 목록 조회 성공")
                .data(roles)
                .build();
    }

    public Response<String> getRoleByCode(String code) {
        RoleEntity role = roleRepository.findByCode(Long.valueOf(code))
                            .orElseThrow(() -> new BadRequestException("역할 코드에 해당하는 역할을 찾을 수 없습니다."));

        return Response.<String>builder()
                .statusCode(200)
                .message("역할 조회 성공")
                .data(role.getName())
                .build();
    }

    public Response<String> getRoleByName(String name) {
        RoleEntity role = roleRepository.findByName(name)
                            .orElseThrow(() -> new BadRequestException("역할 이름에 해당하는 역할을 찾을 수 없습니다."));

        return Response.<String>builder()
                .statusCode(200)
                .message("역할 조회 성공")
                .data(role.getCode().toString())
                .build();
    }
}
