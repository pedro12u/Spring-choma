package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatusOrderByPriority(Task.Status status);

    List<Task> findByPriorityOrderByDueDate(Task.Priority priority);

    List<Task> findByDueDateBefore(LocalDate date);

    List<Task> findByDueDateBeforeAndStatusNot(LocalDate date, Task.Status status);
}
