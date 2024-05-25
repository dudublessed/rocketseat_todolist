package br.com.blessed.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


// A Spring Framework 'RestController' annotation that groups both annotations: Controller (receive and process HTTP requests) and ResponseBody (serialization of the data returned on the class method on the request body of HTTP), making easier the creation of RESTful web services.
@RestController

// A Spring Framework 'RequestMapping' annotation that maps web requests onto methods. 
// http://localhost:8080/users/ ------ change according to the requestmapping and the mapping after
@RequestMapping("/users")


// A public class 'UserController' that will manipulate the UserModel.
public class UserController {

    // A Spring Framework 'Autowired' annotation that makes Spring marks a constructor, field, setter method, or config method as autowired.
    @Autowired

    //Gets the IUserRepository interface and its extension 'JpaRepository'
    private IUserRepository userRepository;
    
    // A Spring Framework 'PostMapping' annotation that maps a HTTP Post request.
    @PostMapping("/")


    // Gets the UserModel classes and other data, bounding a method parameter to the body of the web request 'RequestBody'
    public ResponseEntity create(@RequestBody UserModel userModel) {

        // Finds and gets the user from the repository according to the existent userModel. 
        var user = this.userRepository.findByUsername(userModel.getUsername());

        // Condition that returns a negative answer on the body, showcasing a BADREQUEST HTTP (400), indicating that the user already exists and it cant be created another one like this.
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST! User already existent.");
        }

        // BCrypt library that makes the password hashed, making it more secure. It's needed to convert String to a Char array, Strings can't be hashed.
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

            userModel.setPassword(passwordHashred);

        // The created user, according to the usermodel, is saved in the userRepository interface.
        var userCreated = this.userRepository.save(userModel);


        // If the user is correctly created, it shows a CREATED HTTP (200) on the API and sends a message on the body. 
        return ResponseEntity.status(HttpStatus.OK).body("User created with sucess!                " + userCreated);
    }
}


// Using: (ApiDog Body Json Request)