package com.yandex.practicum.todo;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, String status, int id, String description) {
        super(name, status, id, description);
    }
    public void addSubTaskId(int subTaskId) {
        this.subTaskIds.add(subTaskId);
    }
    public void showSubTasksIds(){
        for (int i : subTaskIds) {
            System.out.println(i);
        }
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }
}
