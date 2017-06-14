package demo.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "repo")
public interface RunningInfoRepository extends JpaRepository<RunningInfo, Long> {

    @RestResource(rel = "PageByWarning")
    Page<RunningInfo> findAllByOrderByHealthWarningLevelDescHeartRateDesc(Pageable pageable);

    @RestResource(rel = "findByRunningId")
    List<RunningInfo> findAllByRunningId(String runningId);
}