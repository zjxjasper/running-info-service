package demo.service;


import demo.domain.Output;
import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RunningInfoService {

    List<RunningInfo> saveRunningInfos(List<RunningInfo> runningLocations);

    List<Output> saveOutputs(List<Output> outputs);

    void deleteAll();

    Page<Output> findAllByOrderByHealthWarningLevelDescHeartRateDesc(Pageable pageable);

    void deleteByRunningId(String runningId);

}
