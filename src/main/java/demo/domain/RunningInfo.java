package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Random;

@Table(name = "RUNNING_ANALYSIS")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Data
public class RunningInfo {

    @Id
    @GeneratedValue
    private Long Id;



    @JsonProperty("runningId")
    private String runningId;
    public enum HealthWarningLevel{
        ERROR, LOW, NORMAL, HIGH;
    }

    @JsonIgnore
    private double latitude;
    @JsonIgnore
    private double longitude;

    @JsonIgnore
    private double runningDistance;
    private int heartRate = 0;
    private HealthWarningLevel healthWarningLevel = HealthWarningLevel.ERROR;
    private double totalRunningTime;

    @JsonIgnore
    private Date timestamp = new Date();

    @JsonProperty("userId")
    public Long getId(){
        return this.Id;
    }

    @JsonProperty("userName")
    public String getUserName() {
        return userInfo.getUsername();
    }


    @JsonProperty("userAddress")
    public String getUserAddress() {
        return userInfo.getAddress();
    }


    public void generateHeartRate() {
        Random rand = new Random();
        this.heartRate = rand.nextInt(141) + 60;
        this.healthWarningLevel = setHealthWarningLevelByHeartRate(heartRate);
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
        this.healthWarningLevel = setHealthWarningLevelByHeartRate(heartRate);
    }

    @Embedded
    private UserInfo userInfo;


    public RunningInfo(){
    }

    @JsonCreator
    public RunningInfo(@JsonProperty("Id") Long Id) {
        this.Id = Id;
    }

    public RunningInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public HealthWarningLevel setHealthWarningLevelByHeartRate(int heartRate) {
        if (heartRate >= 60 && heartRate <= 75) return HealthWarningLevel.LOW;
        else if (heartRate > 75 && heartRate <= 120) return HealthWarningLevel.NORMAL;
        else if (heartRate > 120) return HealthWarningLevel.HIGH;
        else return HealthWarningLevel.ERROR;
    }
}
