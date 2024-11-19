package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Listar todas as tarefas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Criar uma nova tarefa
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDate.now());
        return taskRepository.save(task);
    }

    // Atualizar uma tarefa existente
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(task);
    }

    // Excluir uma tarefa
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Mover uma tarefa para a próxima coluna
    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));
        switch (task.getStatus()) {
            case TO_DO -> task.setStatus(Task.Status.IN_PROGRESS);
            case IN_PROGRESS -> task.setStatus(Task.Status.DONE);
            default -> throw new RuntimeException("Task já está na coluna DONE");
        }
        return taskRepository.save(task);
    }

    // Listar tarefas por status e ordená-las por prioridade
    public List<Task> getTasksByStatus(Task.Status status) {
        return taskRepository.findByStatusOrderByPriority(status);
    }

    // Filtrar tarefas por prioridade e ordená-las por data limite
    public List<Task> getTasksByPriority(Task.Priority priority) {
        return taskRepository.findByPriorityOrderByDueDate(priority);
    }

    // Filtrar tarefas com data limite anterior a uma data específica
    public List<Task> getTasksBeforeDate(LocalDate date) {
        return taskRepository.findByDueDateBefore(date);
    }

    // Gerar relatório de tarefas atrasadas
    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();
        return taskRepository.findByDueDateBeforeAndStatusNot(today, Task.Status.DONE);
    }
}
