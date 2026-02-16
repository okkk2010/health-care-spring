package inhatc.cse.inhive.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import inhatc.cse.inhive.user.dto.DoctorDTO;
import inhatc.cse.inhive.user.dto.PatientDTO;
import inhatc.cse.inhive.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public DoctorDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, DoctorDTO.class);
    }

    public PatientDTO toPatientDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, PatientDTO.class);
    }

    public UserEntity toEntity(PatientDTO patientDTO) {
        if (patientDTO == null) {
            return null;
        }
        return modelMapper.map(patientDTO, UserEntity.class);
    }

    public UserEntity toEntity(DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            return null;
        }
        return modelMapper.map(doctorDTO, UserEntity.class);
    }
}
