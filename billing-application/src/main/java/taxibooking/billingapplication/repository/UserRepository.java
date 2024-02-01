package taxibooking.billingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxibooking.billingapplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
