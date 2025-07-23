package task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task4.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
