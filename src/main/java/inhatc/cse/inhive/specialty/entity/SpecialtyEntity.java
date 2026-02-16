package inhatc.cse.inhive.specialty.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "specialties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialty_name")
    private String name;

    @Column(name = "specialty_code")
    private Long code;
}
