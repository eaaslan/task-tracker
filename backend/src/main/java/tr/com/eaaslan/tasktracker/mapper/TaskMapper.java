package tr.com.eaaslan.tasktracker.mapper;

import tr.com.eaaslan.tasktracker.entity.Dto.TaskDto;
import tr.com.eaaslan.tasktracker.entity.Task;

public interface TaskMapper {

    TaskDto toDto(Task task);

    Task fromDto(TaskDto dto);


}
