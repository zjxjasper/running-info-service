package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource(path = "outputRepo")
public interface OutputRepository extends JpaRepository<Output,Long> {

    @RestResource(rel = "findAllByWarning")
    Page<Output> findAllByOrderByHealthWarningLevelDescHeartRateDesc(Pageable pageable);

    @RestResource(rel = "findByRunningId")
    List<Output> findAllByRunningId(String runningId);
}
