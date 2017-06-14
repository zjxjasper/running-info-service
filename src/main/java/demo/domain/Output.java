package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;



@Data
public class Output {

    @javax.persistence.Id
    private Long Id;

    private String runningId;
    public enum HealthWarningLevel{
        ERROR, LOW, NORMAL, HIGH;
    }


    private int heartRate = 0;
    private RunningInfo.HealthWarningLevel healthWarningLevel = RunningInfo.HealthWarningLevel.ERROR;
    private double totalRunningTime;

}
