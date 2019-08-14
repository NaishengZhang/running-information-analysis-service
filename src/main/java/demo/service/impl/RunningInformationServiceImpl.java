package demo.service.impl;

import demo.domain.RunningInformation;
import demo.rest.RunningInformationRepository;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningInformationServiceImpl implements RunningInformationService {

    private RunningInformationRepository runningInformationRepository;

    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository){
        this.runningInformationRepository = runningInformationRepository;
    }
    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList) {
        return runningInformationRepository.save(runningInformationList);
    }

    @Override
    public Page<RunningInformation> findByHeartRate(int hearRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRate(hearRate,pageable);
    }

    @Override
    public Page<RunningInformation> findByHeartRateGreaterThan(int hearRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRateGreaterThan(hearRate,pageable);
    }

    @Override
    public Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteAll() {
        runningInformationRepository.deleteAll();
    }
}
