package br.com.blessed.todolist.user;

//libraries
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Lombok library "Data" annotation making everything in UserModel have a Get and Set method.
@Data

// Jakarta from Spring Boot 'Entity' annotation that establishes the name of the database
@Entity(name ="tb_users")


//class 'UserModel' that defines things that an User will have
public class UserModel {
    
   // Jakarta from Spring Boot 'Id' annotation that specifies the primary key of an entity 
   @Id 
   // Jakarta from Spring Boot 'GeneratedValue' annotation that generates automaticaly the value of the UUID (primary key) of the user.
   @GeneratedValue(generator = "UUID")

   // A private (modifier, can only be acessed at his own class) class 'UUID' that is a identifier (PK) of the user. 
   private UUID id;
    
   // Jakarta from Spring Boot 'Column' annotation that, in this case, will ensure if the specified property or field is not already existent, if so, it does not allow it to create another one equal to the already existent.
   @Column(unique = true)
   
   // Classes of the user: name, username and password. When opened with a database, if the user is created, it should display his name, username and password, if existent.
   private String username;
   private String name;
   private String password;

   // Hibernate 'CreationTimestamp' annotation that specifies that the annotated field of property is a generated creation timestamp.
   @CreationTimestamp
   // Class 'LocalDateTime' called "createdAt" that displays the info of the generated timestamp of the user in the moment that an entity is inserted at the database.
   private LocalDateTime createdAt;

  
}

