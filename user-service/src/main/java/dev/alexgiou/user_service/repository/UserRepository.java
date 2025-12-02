package dev.alexgiou.user_service.repository;

import dev.alexgiou.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
