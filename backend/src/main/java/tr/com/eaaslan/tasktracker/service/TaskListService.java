package tr.com.eaaslan.tasktracker.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskListDto;
import tr.com.eaaslan.tasktracker.entity.TaskList;
import tr.com.eaaslan.tasktracker.mapper.TaskListMapper;
import tr.com.eaaslan.tasktracker.mapper.TaskMapper;
import tr.com.eaaslan.tasktracker.repository.TaskListRepository;

import java.util.List;
import java.util.Optional;
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
    public TaskListDto getTaskListById(UUID id) {
      return taskListRepository.findById(id)
              .map(taskListMapper::toDto)
              .orElseThrow(()->new EntityNotFoundException("TaskList not found with id: "+id));
    }

    public TaskListDto updateTaskList(UUID taskListUuid, TaskListDto taskListDto) {
        TaskList entity= taskListRepository.findById(taskListUuid).orElseThrow();
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
