package demo.service.impl;

import demo.domain.RunningInfo;
import demo.domain.RunningInfoRepository;
import demo.service.RunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningInfoServiceImpl implements RunningInfoService{
    private RunningInfoRepository runningInfoRepository;

    @Autowired
    public RunningInfoServiceImpl(RunningInfoRepository runningInfoRepository) {
        this.runningInfoRepository = runningInfoRepository;
    }


    @Override
    public List<RunningInfo> saveRunningInfos(List<RunningInfo> runningInfos) {
        for(RunningInfo runningInfo: runningInfos){
            runningInfo.generateHeartRate();
        }
        return runningInfoRepository.save(runningInfos);
    }


    @Override
    public void deleteAll() {
        runningInfoRepository.deleteAll();
    }

    @Override
    public Page<RunningInfo> findAllByOrderByHealthWarningLevelDescHeartRateDesc(Pageable pageable) {
        return runningInfoRepository.findAllByOrderByHealthWarningLevelDescHeartRateDesc(pageable);
    }

    @Override
    public void deleteByRunningId(String runningId){
        List<RunningInfo> runningInfosToDel = runningInfoRepository.findAllByRunningId(runningId);
        for(RunningInfo runningInfo: runningInfosToDel){
            runningInfoRepository.delete(runningInfo);
        }
    }

}
