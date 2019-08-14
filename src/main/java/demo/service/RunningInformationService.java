package demo.service;

import demo.domain.RunningInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RunningInformationService {
    List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList);

    Page<RunningInformation> findByHeartRate(int hearRate, Pageable pageable);

    Page<RunningInformation> findByHeartRateGreaterThan(int hearRate, Pageable pageable);

    Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(Pageable pageable);

    public void deleteAll();
}
