package at.jugger.tracker.repository;

import at.jugger.tracker.domain.LoginTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginTokenRepository extends JpaRepository<LoginTokenEntity, Long> {
    LoginTokenEntity findByTokenId(String tokenId);

    LoginTokenEntity findByUser_UserId(Long userId);
}
