package com.crus.tasks.mappers;

import com.crus.tasks.domain.dto.TaskDto;
import com.crus.tasks.domain.entities.Task;

public interface TaskMapper {
    // method will take in a TaskDto and return a Task
    Task fromDto(TaskDto taskDto);

    // method will take in a Task and return a TaskDto
    TaskDto toDto(Task task);

}
