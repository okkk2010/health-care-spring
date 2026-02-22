package inhatc.cse.inhive.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import inhatc.cse.inhive.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll();

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email AND u.roleCode = :roleCode")
    Optional<UserEntity> findByEmailAndRoleCode(@Param("email") String email, @Param("roleCode") Long roleCode);
}
