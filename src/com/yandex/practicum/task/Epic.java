package com.yandex.practicum.task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, String status, int id, String description) {
        super(name, status, id, description);
    }

    public void addSubTaskId(int subTaskId) {
        this.subTaskIds.add(subTaskId);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void removeSubtaskIdFromEpic(Integer id) {
        subTaskIds.remove(id);
    }

    public void clearSubtaskIds() {
        subTaskIds.clear();
    }
}
