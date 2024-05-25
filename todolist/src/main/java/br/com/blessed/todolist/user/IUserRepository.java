package br.com.blessed.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    
    // Allows to find the username of an user according to the UserModel
    UserModel findByUsername(String username);
}
