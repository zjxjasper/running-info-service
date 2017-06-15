package demo.service.impl;

import demo.domain.Output;
import demo.domain.OutputRepository;
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
    private OutputRepository outputRepository;

    @Autowired
    public RunningInfoServiceImpl(RunningInfoRepository runningInfoRepository, OutputRepository outputRepository) {
        this.runningInfoRepository = runningInfoRepository;
        this.outputRepository = outputRepository;
    }



    @Override
    public List<RunningInfo> saveRunningInfos(List<RunningInfo> runningInfos) {
        for(RunningInfo runningInfo: runningInfos){
            runningInfo.generateHeartRate();
        }
        return runningInfoRepository.save(runningInfos);
    }

    @Override
    public List<Output> saveOutputs(List<Output> outputs){
        return outputRepository.save(outputs);
    }


    @Override
    public void deleteAll() {
        runningInfoRepository.deleteAll();
        outputRepository.deleteAll();
    }



    @Override
    public void deleteByRunningId(String runningId){
        List<RunningInfo> runningInfosToDel = runningInfoRepository.findAllByRunningId(runningId);
        for(RunningInfo runningInfo: runningInfosToDel){
            runningInfoRepository.delete(runningInfo);
        }

        List<Output> outputToDel = outputRepository.findAllByRunningId(runningId);
        for(Output output: outputToDel){
            outputRepository.delete(output);
        }
    }

    @Override
    public Page<Output> findAllByOrderByHealthWarningLevelDescHeartRateDesc(Pageable pageable) {
        return outputRepository.findAllByOrderByHealthWarningLevelDescHeartRateDesc(pageable);
    }

}
