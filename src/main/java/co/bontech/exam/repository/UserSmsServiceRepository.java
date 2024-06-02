package co.bontech.exam.repository;

import co.bontech.exam.domain.UserSmsServices;
import co.bontech.exam.repository.projection.ServiceCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSmsServiceRepository extends JpaRepository<UserSmsServices, Long> {

    @Query(value = """
            SELECT new co.bontech.exam.repository.projection.ServiceCredit(s.serviceId, SUM(s.useCounter)) 
            FROM UserSmsServices s GROUP BY s.serviceId
            """)
    List<ServiceCredit> report();

    List<UserSmsServices> findAllByUserId(Long userId);

    Optional<UserSmsServices> findOneByUserIdAndServiceId(Long userId, Long serviceId);
}
