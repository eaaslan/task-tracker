package tr.com.eaaslan.tasktracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.eaaslan.tasktracker.entity.Dto.TaskListDto;
import tr.com.eaaslan.tasktracker.mapper.TaskListMapper;
import tr.com.eaaslan.tasktracker.repository.TaskListRepository;
import tr.com.eaaslan.tasktracker.service.TaskListService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("task-list")
public class TaskListController {

   private final TaskListService taskListService;

    TaskListController(TaskListService taskListService) {
        this.taskListService=taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getAllTaskList(){
        return ResponseEntity.ok(taskListService.getAllTaskList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDto> getTaskListById(@PathVariable UUID id){
        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskListDto created=taskListService.createTaskList(taskListDto);
        URI location = URI.create("/task-list/"+created.id());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDto>updateTaskList(@RequestBody TaskListDto taskListDto, @PathVariable UUID id){
        return ResponseEntity.ok(taskListService.updateTaskList(id,taskListDto));
    }
}
