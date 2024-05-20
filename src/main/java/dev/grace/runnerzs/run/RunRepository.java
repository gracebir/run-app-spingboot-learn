package dev.grace.runnerzs.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return  runs;
    }

    Run create(Run run){
        runs.add(run);
        return run;
    }

    void update(Run run, Integer id){
        Optional<Run> existingRun = findById(id);
        if(existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    Optional<Run> findById(Integer id){
        return  runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now(),
                4,
                Location.OUTDOOR));
        runs.add(new Run(2,
                "Thursday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                Location.OUTDOOR));
    }
}
