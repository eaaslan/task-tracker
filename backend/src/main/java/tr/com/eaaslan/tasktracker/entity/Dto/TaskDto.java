package tr.com.eaaslan.tasktracker.entity.Dto;

import tr.com.eaaslan.tasktracker.entity.TaskPriority;
import tr.com.eaaslan.tasktracker.entity.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
        ) {
}
