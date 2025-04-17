package tr.com.eaaslan.tasktracker.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskDto;
import tr.com.eaaslan.tasktracker.entity.Task;
import tr.com.eaaslan.tasktracker.mapper.TaskMapper;
import tr.com.eaaslan.tasktracker.repository.TaskRepository;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper taskMapper,TaskRepository taskRepository){
        this.taskMapper=taskMapper;
        this.taskRepository=taskRepository;
    }

    public TaskDto createTask(TaskDto taskDto){
        Task task=taskMapper.fromDto(taskDto);
        taskRepository.save(task);
        return taskDto;
    }

    public List<TaskDto> getAllTasks(){
        return taskRepository.findAll().stream().map(taskMapper::toDto).toList();
    }

    public TaskDto getTaskById(Long id){
       return taskRepository.findById(id)
                .map(taskMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException("Task could not find with id: "+id));
    }

    public TaskDto updateTask(TaskDto taskDto){
        Task entity= taskRepository.findById(taskDto.id()).orElseThrow(EntityNotFoundException::new);
        entity.setTitle(taskDto.title());
        entity.setDescription(taskDto.description());
        entity.setStatus(taskDto.status());
        entity.setTaskPriority(taskDto.priority());
        entity.setDueDate(taskDto.dueDate());
        taskRepository.save(entity);

        return taskMapper.toDto(entity);
    }


}
