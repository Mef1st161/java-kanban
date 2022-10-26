package com.yandex.practicum.task;

public class SubTask extends Task {
   private int epicId;

   public SubTask(String name, String status, int id, int epicId, String description) {
      super(name, status, id, description);
   }

   public int getEpicId() {
      return epicId;
   }

   public void setEpicId(int epicId) {
      this.epicId = epicId;
   }
}
