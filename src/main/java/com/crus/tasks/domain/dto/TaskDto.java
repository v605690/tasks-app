package com.crus.tasks.domain.dto;

import com.crus.tasks.domain.entities.TaskPriority;
import com.crus.tasks.domain.entities.TaskStatus;

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
