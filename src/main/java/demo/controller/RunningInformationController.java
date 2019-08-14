package demo.controller;

import demo.domain.RunningInformation;
import demo.service.RunningInformationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInformationController {
    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_PAGE_SIZE = "2";
    private static final String DEFAULT_SORT_DIR = "desc";
    private static final String DEFAULT_SORT_BY = "heartRate";

    @Autowired
    private RunningInformationService runningInformationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList) {
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @RequestMapping(value = "purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInformationService.deleteAll();
    }


    @RequestMapping(value = "/heartRate/{heartRate}", method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRate(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return runningInformationService.findByHeartRate(heartRate, pageable);
    }

    @RequestMapping(value = "/heartMappingGreaterThan/{heartRate}",method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> findByHeartMappingGreaterThan(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page",required = false,defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size",required = false,defaultValue = DEFAULT_PAGE_SIZE)Integer size) {
        Pageable pageable = new PageRequest(page,size);
        Page<RunningInformation> rawResult = runningInformationService.findByHeartRateGreaterThan(heartRate,pageable);
        List<RunningInformation> content = rawResult.getContent();

        List<JSONObject> results = new ArrayList<JSONObject>();
        for (RunningInformation item : content) {
            JSONObject info = new JSONObject();
            info.put("runningId", item.getRunningId());
            info.put("totalRunningTime", item.getTotalRunningTime());
            info.put("heartRate", item.getHeartRate());
            info.put("userId", item.getId());
            info.put("userName", item.getUserInfo().getUsername());
            info.put("userAddress", item.getUserInfo().getAddress());
            info.put("healthWarningLevel", item.getHealthWarningLevel());
            results.add(info);
        }

        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);

    }
}
