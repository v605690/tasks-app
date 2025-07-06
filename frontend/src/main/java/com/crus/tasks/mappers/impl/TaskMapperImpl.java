package com.crus.tasks.mappers.impl;

import com.crus.tasks.domain.dto.TaskDto;
import com.crus.tasks.domain.entities.Task;
import com.crus.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

// @Component marks as a bean and inject class wherever its declared throughout the application
@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
