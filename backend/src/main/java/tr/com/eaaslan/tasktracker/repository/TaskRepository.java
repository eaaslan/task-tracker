package tr.com.eaaslan.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.eaaslan.tasktracker.entity.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
