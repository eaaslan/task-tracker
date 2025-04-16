package tr.com.eaaslan.tasktracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskListDto;
import tr.com.eaaslan.tasktracker.mapper.TaskListMapper;
import tr.com.eaaslan.tasktracker.repository.TaskListRepository;

@RestController
@RequestMapping("task-list")
public class TaskListController {

    public final TaskListRepository taskListRepository;
    public final TaskListMapper taskListMapper;

    TaskListController(TaskListRepository taskListRepository, TaskListMapper taskListMapper) {
        this.taskListRepository = taskListRepository;
        this.taskListMapper = taskListMapper;
    }


    @PostMapping
    public ResponseEntity<TaskListDto> create(@RequestBody TaskListDto taskListDto) {
         taskListRepository.save(taskListMapper.fromDto(taskListDto));
        return new ResponseEntity<>(taskListDto, HttpStatus.CREATED);
    }
}
