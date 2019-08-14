package demo.rest;

import demo.domain.RunningInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RunningInformationRepository extends JpaRepository<RunningInformation, Long> {

    Page<RunningInformation> findByHeartRateGreaterThan(@Param("hearRate") int hearRate, Pageable pageable);

    Page<RunningInformation> findByHeartRate(@Param("heartRate") int heartRate, Pageable pageable);

}
