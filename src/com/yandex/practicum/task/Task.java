package com.yandex.practicum.task;

public class Task {
    private String name;
    private String status;
    private int id;
    private String description;

    public Task(String name, String status, int id, String description) {
        this.name = name;
        this.status = status;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
