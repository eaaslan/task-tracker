package tr.com.eaaslan.tasktracker.mapper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskListDto;
import tr.com.eaaslan.tasktracker.entity.Task;
import tr.com.eaaslan.tasktracker.entity.TaskList;
import tr.com.eaaslan.tasktracker.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImp implements TaskListMapper{
    private final TaskMapper taskMapper;

    TaskListMapperImp(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskListDto toDto(TaskList tasklist) {
            return new TaskListDto(
                    tasklist.getId(),
                    tasklist.getTitle(),
                    tasklist.getDescription(),
                    tasklist.getTasks().size(),
                    calculateProgress(tasklist),
                    tasklist.getTasks().stream().map(taskMapper::toDto).toList()
            );
    }

    double calculateProgress(TaskList tasklist) {
      return Optional.ofNullable(tasklist.getTasks()) // use nullable to prevent throw exception
                .map(tasks ->{
                        long closedCount= tasks.stream()
                                .filter(task -> task.getStatus()==TaskStatus.CLOSED)
                                .count();
                            return (double)closedCount/ tasks.size();
                        }
                )
                .orElse(0.0);

    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                null,
                null,
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks-> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList())
                        .orElse(null)
        );
    }
}
