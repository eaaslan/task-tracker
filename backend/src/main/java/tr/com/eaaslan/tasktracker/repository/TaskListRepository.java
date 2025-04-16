package tr.com.eaaslan.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.eaaslan.tasktracker.entity.TaskList;

import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
