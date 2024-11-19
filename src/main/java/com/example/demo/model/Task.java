package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDate createdAt = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.TO_DO;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate dueDate;

    // Getter para `createdAt`
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // Setter para `createdAt`
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    // Getters e Setters para os outros campos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Enum para status
    public enum Status {
        TO_DO,
        IN_PROGRESS,
        DONE
    }

    // Enum para prioridade
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
}
