package com.yandex.practicum.todo;

import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, SubTask> subtasks = new HashMap<>();
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

     private int generatorId = 1;
     private int generatorEcpicId = 1;
     private int generatorSubTaskId = 1;

    public void showEpicsSubTaskIds() {
        System.out.println(getTasks().toString());
        for (Integer key : epics.keySet()) {
            System.out.println(epics.get(key).toString());
             epics.get(key).showSubTasksIds();
        }
    }

    public void showAllTasks() {
       System.out.println(tasks.toString());
       System.out.println(epics.toString());
       System.out.println(subtasks.toString());
    }

    public void updateEpicStatus(){
        for (Integer i : epics.keySet()) {
            Epic epic = epics.get(i);
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
            else {
                System.out.println("updateEpicStatus goes wrong!");
            }

        }
    }


    public void getListEpicSubTasks(int epicId) {
        for (Integer key : subtasks.keySet()) {
            if (key.equals(epicId)) {
                System.out.println(subtasks.get(key));
            }
        }
    }

    public void removeAllTusk() {
        subtasks.clear();
        epics.clear();
        tasks.clear();
    }

    public void createTask(Task task) {
        Task newTask = new Task(task.getName(), task.getStatus(), task.getId(), task.getDescription());
        newTask.setId(generatorId);
        newTask.setStatus("NEW");
        tasks.put(generatorId, newTask);
        generatorId += 1;
    }

    public void createEpic(Epic epic) {
        Epic newEpic = new Epic(epic.getName(), epic.getStatus(), epic.getId(), epic.getDescription());
        newEpic.setId(generatorEcpicId);
        newEpic.setStatus("NEW");
        epics.put(generatorEcpicId, newEpic);
        generatorEcpicId += 1;
    }

    public void createSubTask(SubTask subTask, int epicId) {
        if (!epics.isEmpty()) {
            SubTask newSubTask = new SubTask(subTask.getName(), subTask.getStatus(), generatorSubTaskId, subTask.getDescription());
            newSubTask.setId(generatorSubTaskId);
            newSubTask.setStatus("NEW");
            newSubTask.setEpicId(epicId);
            subtasks.put(generatorSubTaskId, newSubTask);
            generatorSubTaskId += 1;
            Epic epic = epics.get(epicId);
            epic.addSubTaskId(newSubTask.getId());
        }
        updateEpicStatus();
    }

    public void removeTask(Task task) {
        tasks.remove(task.getId());
    }

    public void removeEpic(Epic epic) {
        epics.remove(epic.getId());
    }

    public void removeSubTask(SubTask subTask) {
        subtasks.remove(subTask.getId());
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

    public int getGeneratorId() {
        return generatorId;
    }

    public void setGeneratorId(int generatorId) {
        this.generatorId = generatorId;
    }

    public HashMap<Integer, SubTask> getSubtasks() {
        return subtasks;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }


}
