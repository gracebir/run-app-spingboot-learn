package dev.grace.runnerzs.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
@CrossOrigin
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run getById(@PathVariable Integer id){
        Optional<Run> run =  runRepository.findById(id);
        if(run.isEmpty()){
            throw  new RunNotFoundException();
        }

        return  run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
         runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    void update(@PathVariable Integer id, @RequestBody Run run){
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }
}
