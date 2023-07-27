package ru.aromat.aromatTerapevt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aromat.aromatTerapevt.models.Client;
import ru.aromat.aromatTerapevt.models.Task;
import ru.aromat.aromatTerapevt.service.ClientService;
import ru.aromat.aromatTerapevt.service.TaskService;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final ClientService clientService;

    public TaskController(TaskService taskService, ClientService clientService) {
        this.taskService = taskService;
        this.clientService = clientService;
    }

@GetMapping("/tasks")
public String getTasks(Model model, Principal principal) {
    Long userId = taskService.getUserByPrincipal(principal).getId();
    Map<String, List<Task>> tasksByCategory = taskService.getAllTasksByUser(userId);
    model.addAttribute("tasksByCategory", tasksByCategory);
    return "task/allTasksByUser";
}





    @GetMapping("/tasks/create")
    public String createTask(Model model, Principal principal) {
        Long users = clientService.getUserByPrincipal(principal).getId();
        String userName = clientService.getUserByPrincipal(principal).getUsername();
        List<Client> clients = clientService.getClientsByUser(users);
        model.addAttribute("clients", clients);
        model.addAttribute("task", new Task());
        return "task/create-task";
    }
    @PostMapping("/tasks/create")
    public String createTask(Principal principal, @ModelAttribute Task task, @RequestParam Long clientId) {
        taskService.createTask(principal, task, clientId);
        return "redirect:/tasks";
    }


    @GetMapping("/tasks/{taskId}/update")
    public String updateTask(Model model, Principal principal, @PathVariable Long taskId) throws AccessDeniedException {
        Task task = taskService.getTaskById(taskId);
        if (!task.getUser().getEmail().equals(principal.getName())) {
            throw new AccessDeniedException("You do not have permission to update this task");
        }
        model.addAttribute("task", task);
        return "task/update-task";
    }

    @PostMapping("/tasks/{taskId}/update")
    public String updateTask(Principal principal, @ModelAttribute Task updatedTask, @PathVariable Long taskId) throws AccessDeniedException {
        taskService.updateTask(principal, updatedTask, taskId);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/{taskId}/delete")
    public String deleteTask(Principal principal, @PathVariable Long taskId) throws AccessDeniedException {
        taskService.deleteTask(principal, taskId);
        return "redirect:/tasks";
    }
}



