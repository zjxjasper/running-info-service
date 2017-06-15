package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "OUTPUT")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Data
public class Output {
    private String runningId;
    private double totalRunningTime;
    private int heartRate;
    @Id
    private Long userId;

    private String userName;
    private String userAddress;
    private HealthWarningLevel healthWarningLevel;

    public enum HealthWarningLevel{
        ERROR, LOW, NORMAL, HIGH;
    }

    public HealthWarningLevel setHealthWarningLevelByHeartRate(int heartRate) {
        if (heartRate >= 60 && heartRate <= 75) return HealthWarningLevel.LOW;
        else if (heartRate > 75 && heartRate <= 120) return HealthWarningLevel.NORMAL;
        else if (heartRate > 120) return HealthWarningLevel.HIGH;
        else return HealthWarningLevel.ERROR;
    }

    public Output() {
    }


    @JsonCreator
    public Output(RunningInfo runningInfo) {
        this.userId = runningInfo.getId();
        this.heartRate = runningInfo.getHeartRate();
        this.userName = runningInfo.getUserName();
        this.runningId = runningInfo.getRunningId();
        this.totalRunningTime = runningInfo.getTotalRunningTime();
        this.userAddress = runningInfo.getUserAddress();
        this.healthWarningLevel = setHealthWarningLevelByHeartRate(this.heartRate);
    }


}
