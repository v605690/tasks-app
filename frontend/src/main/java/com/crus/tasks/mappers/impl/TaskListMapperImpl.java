package com.crus.tasks.mappers.impl;

import com.crus.tasks.domain.dto.TaskDto;
import com.crus.tasks.domain.dto.TaskListDto;
import com.crus.tasks.domain.entities.Task;
import com.crus.tasks.domain.entities.TaskList;
import com.crus.tasks.domain.entities.TaskStatus;
import com.crus.tasks.mappers.TaskListMapper;
import com.crus.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// @Component declared TaskListMapperImpl as a bean to allow dependencies to be injected
@Component
public class TaskListMapperImpl implements TaskListMapper {

    // now we need a way of converting from a task DTO to a task;
    // we are going to use TaskMapper and going to inject it into this class
    private final TaskMapper taskMapper;

    // Its through this constructor that our task mapper will be injected and available to use
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                // optional handles if taskListDto is nullable
                Optional.ofNullable(taskListDto.tasks())
                        // Int the case that its not null, call tasks dot stream dot map
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                // we still have a stream now converted over as toList
                                .toList()
                        ).orElse(null),
                        null,
                        null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                // using Optional.ofNullable as it could be null
                Optional.ofNullable(taskList.getTasks())
                        // call map function to handle the case where its not null, so we actually have a list
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                // Now need a task list of Dto's
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks ->
                                tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)
        );
    }
    private Double calculateTaskListProgress(java.util.List<Task> tasks) {
        if (null == tasks) {
            return null;
        }
        // tasks on stream, we filter on task; task-status dot closed equals task dot-get status, then we use a count method to get the count
        long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()
        ).count();

        return (double) closedTaskCount / tasks.size();
    }
}
