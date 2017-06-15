package demo.rest;

import demo.domain.Output;
import demo.domain.OutputRepository;
import demo.domain.RunningInfo;
import demo.service.RunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInfoRestController {
    private RunningInfoService runningInfoService;

    @Autowired
    public RunningInfoRestController(RunningInfoService runningInfoService){
        this.runningInfoService = runningInfoService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInfo> runningInfos){
        this.runningInfoService.saveRunningInfos(runningInfos);
        List<Output> outputs = new ArrayList<>();
        for(RunningInfo runningInfo: runningInfos){
            outputs.add(new Output(runningInfo));
        }
        this.runningInfoService.saveOutputs(outputs);
    }


    @RequestMapping(value = "/delete/all", method = RequestMethod.POST)
    public void purge() {
        this.runningInfoService.deleteAll();
    }

    @RequestMapping(value = "/search/pagingByWarning", method = RequestMethod.GET)
    Page<Output> findAllByOrderByHealthWarningLevelDescHeartRateDesc(@RequestParam(name = "page") int page){
        return runningInfoService.findAllByOrderByHealthWarningLevelDescHeartRateDesc(new PageRequest(page,2));
    }


    @RequestMapping(value = "/delete/{runningId}", method = RequestMethod.POST)
    public void deleteByRunningId(@PathVariable String runningId) {
        this.runningInfoService.deleteByRunningId(runningId);
    }

}
