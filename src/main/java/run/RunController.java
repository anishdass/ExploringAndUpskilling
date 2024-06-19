package run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController  {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> getRuns(){
        return runRepository.findAll();
    }

    @GetMapping("/ItemID={id}")
    Run findById(@PathVariable int id){
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }

//    @GetMapping("/Location={location}")
//    List<Run> findByLocation(@PathVariable String location){
//        List<Run> run = runRepository.findByLocation(location);
//        if (run.isEmpty()) {
//            throw new RunNotFoundException();
//        }
//        return run;
//    }

    //Post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createRun(@Valid @RequestBody Run run){
        runRepository.create(run);
    }

    //Put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/ItemID={id}")
    void update(@Valid @RequestBody Run run, @PathVariable int id){
        runRepository.update(run, id);
    }

    //Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/ItemID={id}")
    void delete(@PathVariable int id){
        runRepository.delete(id);
    }
}