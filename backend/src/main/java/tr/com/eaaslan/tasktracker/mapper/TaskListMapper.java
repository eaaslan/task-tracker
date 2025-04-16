package tr.com.eaaslan.tasktracker.mapper;

import tr.com.eaaslan.tasktracker.entity.Dto.TaskListDto;
import tr.com.eaaslan.tasktracker.entity.Task;
import tr.com.eaaslan.tasktracker.entity.TaskList;

public interface TaskListMapper{

   TaskListDto toDto(TaskList taskList);

   TaskList fromDto(TaskListDto dto);
}
