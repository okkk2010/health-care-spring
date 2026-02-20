package inhatc.cse.inhive.role.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import inhatc.cse.inhive.role.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByCode(Long code);
    Optional<RoleEntity> findByName(String name);

    @Query("SELECT r.name FROM RoleEntity r")
    List<String> allRoleNames();
}
