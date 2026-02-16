package inhatc.cse.inhive.user.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Long roleCode;
    private Long specialtyCode;
}
