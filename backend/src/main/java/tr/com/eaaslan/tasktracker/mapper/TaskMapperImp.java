package tr.com.eaaslan.tasktracker.mapper;

import org.springframework.stereotype.Component;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskDto;
import tr.com.eaaslan.tasktracker.entity.Task;

@Component
public class TaskMapperImp implements TaskMapper {
    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getStatus()
        );
    }

    @Override
    public Task fromDto(TaskDto dto) {
        return new Task(
          dto.id(),
          dto.title(),
          dto.description(),
          dto.dueDate(),
          dto.status(),
          dto.priority(),
                null,
                null,
                null

        );
    }
}
