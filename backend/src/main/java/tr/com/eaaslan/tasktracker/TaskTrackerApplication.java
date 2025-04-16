package tr.com.eaaslan.tasktracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import tr.com.eaaslan.tasktracker.entity.Task;
import tr.com.eaaslan.tasktracker.entity.TaskList;
import tr.com.eaaslan.tasktracker.entity.TaskPriority;
import tr.com.eaaslan.tasktracker.entity.TaskStatus;
import tr.com.eaaslan.tasktracker.repository.TaskListRepository;
import tr.com.eaaslan.tasktracker.repository.TaskRepository;

import java.util.UUID;

@SpringBootApplication
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(TaskListRepository taskListRepository,TaskRepository taskRepository1) {
        return args -> {

//
//                TaskList taskList = TaskList.builder()
//                        .title("Task Tracker")
//                        .build();
//
//            taskListRepository.save(taskList);
//
//            Task task = Task.builder()
//                    .taskList(taskList)
//                    .title("Task Title")
//                    .taskPriority(TaskPriority.HIGH)
//                    .status(TaskStatus.OPEN)
//                    .build();
//
//           taskRepository1.save(task);

          //  taskListRepository.deleteAll();
            taskRepository1.deleteAll();
        };
    }
}
