package inhatc.cse.inhive.specialty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import inhatc.cse.inhive.specialty.entity.SpecialtyEntity;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {
    Optional<SpecialtyEntity> findByCode(Long code);

    @Query("SELECT s.name FROM SpecialtyEntity s")
    List<String> allSpecialtyNames();

}
