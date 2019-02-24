package at.jugger.tracker.repository;

import at.jugger.tracker.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long id);

    UserEntity findByEmail(String email);
}
