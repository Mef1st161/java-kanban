package com.yandex.practicum.todo;

public class Main {

    public static void main(String[] args) {
         TaskManager taskManager = new TaskManager();
         Task task = new Task("Купить велосипед", "IN_PROGRESS", 1, "VeloXxx");
         Task task2 = new Task("Купить машину", "NEW", 1, "Mazda cx 5");
         Task taskUpdate = new Task("Купить машину на авито", "NEW", 1, "Mazda cx 6");
         Epic epic = new Epic("Слетать в турцию", "NEW", 1, "Зимой");
         SubTask subTask = new SubTask("Купить билеты", "NEW", 1, "На авиасейлс");
         SubTask subTask2 = new SubTask("Приехать в аэропорт", "NEW", 1, "На такси");
         Epic epic2 = new Epic("Стать разработчиком", "NEW", 1, "Через 10 месяцев");
         SubTask subTask3 = new SubTask("Выучить java", "NEW", 1, "в ЯПрактикуме");
         SubTask subTaskUpdate = new SubTask("Выучить Java", "IN_PROGRESS", 2, "в ЯПрактикуме");
         taskManager.createTask(task);
         taskManager.createTask(task2);
         taskManager.createEpic(epic);
         taskManager.createEpic(epic2);
         taskManager.createSubTask(subTask, 1);
         taskManager.createSubTask(subTask2, 1);
         taskManager.createSubTask(subTask3, 2);
         taskManager.updateTask(taskUpdate);
         taskManager.updateSubtask(subTaskUpdate);
         taskManager.showAllTasks();
    }
}
