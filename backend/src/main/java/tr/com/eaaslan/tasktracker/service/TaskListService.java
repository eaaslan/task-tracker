package tr.com.eaaslan.tasktracker.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskListDto;
import tr.com.eaaslan.tasktracker.entity.TaskList;
import tr.com.eaaslan.tasktracker.mapper.TaskListMapper;
import tr.com.eaaslan.tasktracker.repository.TaskListRepository;
import java.util.List;
import java.util.UUID;

@Service
public class TaskListService {

    private final TaskListMapper taskListMapper;
    private final TaskListRepository taskListRepository;

    public TaskListService(TaskListMapper taskListMapper, TaskListRepository taskListRepository) {
        this.taskListMapper = taskListMapper;
        this.taskListRepository = taskListRepository;
    }

    public TaskListDto createTaskList(TaskListDto taskListDto) {
        TaskList entity=taskListMapper.fromDto(taskListDto);
        taskListRepository.save(entity);
        return taskListDto;
    }

    public List<TaskListDto> getAllTaskList() {
       return taskListRepository.findAll().stream().map(taskListMapper::toDto).toList();
    }

    //TODO create custom exception handler and return 404 instead 500
    public TaskListDto getTaskListById(Long id) {
      return taskListRepository.findById(id)
              .map(taskListMapper::toDto)
              .orElseThrow(()->new EntityNotFoundException("TaskList not found with id: "+id));
    }

    public TaskListDto updateTaskList(TaskListDto taskListDto) {
        TaskList entity= taskListRepository.findById(taskListDto.id()).orElseThrow();
        TaskList updatedEntity= taskListMapper.fromDto(taskListDto);
        if(entity.getTasks()==null){
            entity.setTasks(updatedEntity.getTasks());
        }

        entity.setDescription(updatedEntity.getDescription());
        entity.setTitle(updatedEntity.getTitle());
        taskListRepository.save(entity);
        return taskListMapper.toDto(updatedEntity);
    }
}
