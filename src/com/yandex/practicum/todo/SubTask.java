package com.yandex.practicum.todo;

public class SubTask extends Epic{
   private int epicId;

   public SubTask(String name, String status, int id, String description) {
      super(name, status, id, description);
   }

   public int getEpicId() {
      return epicId;
   }

   public void setEpicId(int epicId) {
      this.epicId = epicId;
   }
}
