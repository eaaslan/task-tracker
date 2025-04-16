package tr.com.eaaslan.tasktracker.mapper;

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
                    (double) ((tasklist.getTasks().stream().filter(task-> task.getStatus()== TaskStatus.CLOSED).count())/tasklist.getTasks().size()),
                    tasklist.getTasks().stream().map(taskMapper::toDto).toList()
            );
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
