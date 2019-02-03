package at.jugger.tracker.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "userId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long userId;
    @Column(name = "firstName", nullable = false, length = 20)
    @NonNull
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 20)
    @NonNull
    private String lastName;
    @Column(name = "email", nullable = false, length = 50)
    @NonNull
    private String email;
}
