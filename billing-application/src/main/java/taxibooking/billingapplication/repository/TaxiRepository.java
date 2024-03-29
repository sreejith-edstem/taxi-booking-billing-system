package taxibooking.billingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taxibooking.billingapplication.model.Taxi;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {}
