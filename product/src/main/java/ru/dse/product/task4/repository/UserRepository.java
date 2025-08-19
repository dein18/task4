package ru.dse.product.task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dse.product.task4.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
