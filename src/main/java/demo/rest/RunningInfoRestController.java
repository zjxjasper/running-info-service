package demo.rest;

import demo.domain.RunningInfo;
import demo.service.RunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    }


    @RequestMapping(value = "/delete/all", method = RequestMethod.POST)
    public void purge() {
        this.runningInfoService.deleteAll();
    }

    @RequestMapping(value = "/search/pagingByWarning", method = RequestMethod.GET)
    Page<RunningInfo> findAllByOrderByHealthWarningLevelDescHeartRateDesc(@RequestParam(name = "page") int page){
        return this.runningInfoService.findAllByOrderByHealthWarningLevelDescHeartRateDesc(new PageRequest(page,2));
    }


    @RequestMapping(value = "/delete/{runningId}", method = RequestMethod.POST)
    public void deleteByRunningId(@PathVariable String runningId) {
        this.runningInfoService.deleteByRunningId(runningId);
    }

}
