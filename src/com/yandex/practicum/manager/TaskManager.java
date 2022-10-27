package com.yandex.practicum.manager;

import com.yandex.practicum.task.Epic;
import com.yandex.practicum.task.SubTask;
import com.yandex.practicum.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, SubTask> subtasks = new HashMap<>();
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private int generatorId = 1;


    public ArrayList<SubTask> getSubTasksByEpicId(int epicId) {
        ArrayList<SubTask> subTasks = new ArrayList<>();
        for (Integer key : subtasks.keySet()) {
            if (key.equals(epicId)) {
                subTasks.add(subtasks.get(key));
            }
        }
        return subTasks;
    }

    public void removeSubTasks() {
        subtasks.clear();
        for (Integer key : epics.keySet()) {
            epics.get(key).clearSubtaskIds();
        }
        updateEpicStatus();
    }

    public void removeEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void createTask(Task task) {
        Task newTask = new Task(task.getName(), task.getStatus(),
                task.getId(), task.getDescription());
        newTask.setId(generatorId);
        tasks.put(generatorId, newTask);
        generatorId += 1;
    }

    public void createEpic(Epic epic) {
        Epic newEpic = new Epic(epic.getName(), epic.getStatus(),
                epic.getId(), epic.getDescription());
        newEpic.setId(generatorId);
        newEpic.setStatus("NEW");
        epics.put(generatorId, newEpic);
        generatorId += 1;
    }

    public void createSubTask(SubTask subTask) {
        SubTask newSubTask = new SubTask(subTask.getName(), subTask.getStatus(),
                generatorId, subTask.getEpicId(), subTask.getDescription());
        newSubTask.setId(generatorId);
        subtasks.put(generatorId, newSubTask);
        generatorId += 1;
        Epic epic = epics.get(subTask.getEpicId());
        epic.addSubTaskId(newSubTask.getId());
        updateEpicStatus();
    }

    public void removeTask(Task task) {
        tasks.remove(task.getId());
    }

    public void removeEpic(Epic epic) {
        for (int id : epic.getSubTaskIds()) {
            subtasks.remove(id);
        }
        epics.remove(epic.getId());
    }

    public void removeSubTask(SubTask subTask) {
        epics.get(subTask.getEpicId()).removeSubtaskIdFromEpic(subTask.getId());
        subtasks.remove(subTask.getId());
        updateEpicStatus();
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        updateEpicStatus();
    }

    public void updateSubtask(SubTask subTask) {
        subtasks.put(subTask.getId(), subTask);
        updateEpicStatus();
    }

    public ArrayList<SubTask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    private void updateEpicStatus(){
        for (Integer key : epics.keySet()) {
            Epic epic = epics.get(key);
            int counter = 0;
            int doneCount = 0;
            int newCount = 0;
            boolean isInProgress = false;
            for (Integer id : epic.getSubTaskIds()) {
                counter += 1;
                switch (subtasks.get(id).getStatus()) {
                    case ("DONE"):
                        doneCount += 1;
                        break;
                    case ("IN_PROGRESS") :
                        isInProgress = true;
                        break;
                    case ("NEW") :
                        newCount += 1;
                        break;
                }
            }
            if (isInProgress) {
                epic.setStatus("IN_PROGRESS");
            }
            else if (counter == doneCount && counter != 0) {
                epic.setStatus("DONE");
            }
            else if (counter == newCount || counter == 0) {
                epic.setStatus("NEW");
            }
        }
    }
}
