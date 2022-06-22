package zorginstellingen.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zorginstellingen.model.User;
import zorginstellingen.service.UserService;

@RestController
@RequestMapping( "/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.updateUserInfo(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable( name= "id") Long id){
        try{
            return ResponseEntity.ok(userService.findUserById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable( name= "id") Long id){
        try{
            return ResponseEntity.ok(userService.removeUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
