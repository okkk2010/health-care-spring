package inhatc.cse.inhive.specialty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDTO {
    private Long id;
    private String name;
    private Long code;
}
