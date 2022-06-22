package zorginstellingen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zorginstellingen.model.Result;
import zorginstellingen.model.User;
import zorginstellingen.service.ResultService;

import static zorginstellingen.global.GlobalService.getUserFromToken;

@RestController
@RequestMapping( "/results")
public class ResultController {
    @Autowired
    private ResultService resultService;


    @GetMapping("getAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("getAllByDoctorId")
    public ResponseEntity<?> getAllByDoctorId(){
        User user = getUserFromToken();
        return ResponseEntity.ok(resultService.getAllResultsByDoctorId(user.getId()));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Result result){
        try{
            return ResponseEntity.ok(resultService.addResult(result));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Result result){
        try{
            return ResponseEntity.ok(resultService.updateResult(result));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable( name= "id") Long id){
        try{
            return ResponseEntity.ok(resultService.deleteResult(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable( name= "id") Long id){
        try{
            return ResponseEntity.ok(resultService.findResultById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
