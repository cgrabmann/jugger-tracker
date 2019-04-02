package at.jugger.tracker.repository;

import at.jugger.tracker.domain.UserEntity;
import org.mapstruct.ObjectFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @ObjectFactory
    UserEntity findByUserId(Long id);

    UserEntity findByEmail(String email);
}
