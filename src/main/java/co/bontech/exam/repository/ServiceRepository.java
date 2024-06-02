package co.bontech.exam.repository;

import co.bontech.exam.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Service entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT e FROM Service e WHERE CURRENT_TIMESTAMP > e.lastStartAvailability AND CURRENT_TIMESTAMP < e.lastEndAvailability")
    List<Service> findAllAvailableNow();
}
