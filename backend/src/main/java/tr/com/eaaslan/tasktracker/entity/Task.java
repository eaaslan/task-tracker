package tr.com.eaaslan.tasktracker.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private TaskStatus status;

    @Column(nullable = false)
    private TaskPriority taskPriority;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false )
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "task_list_id") // its already set "task_list_id " on default
    private TaskList taskList;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
