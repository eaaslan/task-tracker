package tr.com.eaaslan.tasktracker.entity.Dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(

        Long id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks

) {
}
