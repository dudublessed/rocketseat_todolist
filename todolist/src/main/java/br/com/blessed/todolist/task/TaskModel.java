package br.com.blessed.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;




@Data
@Entity(name ="tb_tasks")

public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID idUser;

    @Column(length = 50)
    private String title;
    private String description;
    private String priority;


    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle (String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("The title field must have at least 50 characters.");
        }
        this.title = title;
    }
}
