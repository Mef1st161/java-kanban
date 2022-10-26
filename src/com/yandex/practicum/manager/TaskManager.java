package com.yandex.practicum.manager;

import com.yandex.practicum.task.Epic;
import com.yandex.practicum.task.SubTask;
import com.yandex.practicum.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, SubTask> subtasks = new HashMap<>();
    HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

     private int generatorId = 1;


    private void updateEpicStatus(){
        for (Integer key : epics.keySet()) {
            Epic epic = epics.get(key);
            int counter = 0;
            int doneCount = 0;
            int newCount = 0;
            boolean isInProgress = false;
            for (Integer j : epic.getSubTaskIds()) {
                counter += 1;
                switch (subtasks.get(j).getStatus()) {
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

    public String getListEpicSubTasks(int epicId) {
        StringBuilder result = new StringBuilder();
        for (Integer key : subtasks.keySet()) {
            if (key.equals(epicId)) {
                result.append(subtasks.get(key)).append(", ");
            }
        }
        return result.toString();
    }

    public void removeSubTask() {
        subtasks.clear();
    }

    public void removeEpic() {
        subtasks.clear();
        epics.clear();
    }

    public void removeTask() {
        tasks.clear();
    }

    public void createTask(Task task) {
        Task newTask = new Task(task.getName(), task.getStatus(), task.getId(), task.getDescription());
        newTask.setId(generatorId);
        tasks.put(generatorId, newTask);
        generatorId += 1;
    }

    public void createEpic(Epic epic) {
        Epic newEpic = new Epic(epic.getName(), epic.getStatus(), epic.getId(), epic.getDescription());
        newEpic.setId(generatorId);
        newEpic.setStatus("NEW");
        epics.put(generatorId, newEpic);
        generatorId += 1;
    }

    public void createSubTask(SubTask subTask) {
        SubTask newSubTask = new SubTask(subTask.getName(), subTask.getStatus(), generatorId, subTask.getEpicId(), subTask.getDescription());
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
        ArrayList<SubTask> subTasksArray = new ArrayList<>();
        for (int key : subtasks.keySet()) {
            subTasksArray.add(subtasks.get(key));
        }
        return subTasksArray;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskArray = new ArrayList<>();
        for (int key : tasks.keySet()) {
            taskArray.add(tasks.get(key));
        }
        return taskArray;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicArray = new ArrayList<>();
        for (int key : epics.keySet()) {
            epicArray.add(epics.get(key));
        }
        return epicArray;
    }


}
