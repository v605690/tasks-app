package com.crus.tasks.mappers;

import com.crus.tasks.domain.dto.TaskListDto;
import com.crus.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    // method will take a TaskListDto and return a TaskList
    TaskList fromDto(TaskListDto taskListDto);

    // method will take a TaskList and return a TaskListDto
    TaskListDto toDto(TaskList taskList);

}
