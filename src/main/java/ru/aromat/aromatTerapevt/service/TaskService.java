package ru.aromat.aromatTerapevt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aromat.aromatTerapevt.models.Client;
import ru.aromat.aromatTerapevt.models.Task;
import ru.aromat.aromatTerapevt.models.User;
import ru.aromat.aromatTerapevt.repo.TaskRepository;
import ru.aromat.aromatTerapevt.repo.UserRepo;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ClientService clientService;
    private final UserRepo userRepository;

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksForClient(Long clientId) {
        return taskRepository.findByClientId(clientId);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Principal principal, Task task, Long clientId) {
        Client client = clientService.getClientById(clientId);
        task.setClient(client);
        task.setUser(getUserByPrincipal(principal));
        return taskRepository.save(task);
    }

    public Map<String, List<Task>> getAllTasksByUser(Long userId) {
        Map<String, List<Task>> taskMap = new HashMap<>();

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate afterTomorrow = today.plusDays(2);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfToday = LocalDateTime.of(today, LocalTime.MIN);
        LocalDateTime endOfToday = LocalDateTime.of(today, LocalTime.MAX);
        LocalDateTime startOfTomorrow = LocalDateTime.of(tomorrow, LocalTime.MIN);
        LocalDateTime startOfAfterTomorrow = LocalDateTime.of(afterTomorrow, LocalTime.MIN);

        List<Task> pastTasks = taskRepository.findByUserIdAndDeadlineBeforeOrderByDeadline(userId, now);
        List<Task> todayTasks = taskRepository.findByUserIdAndDeadlineBetweenOrderByDeadline(userId, startOfToday, endOfToday);
        List<Task> tomorrowTasks = taskRepository.findByUserIdAndDeadlineBetweenOrderByDeadline(userId, startOfTomorrow, startOfAfterTomorrow);
        List<Task> futureTasks = taskRepository.findByUserIdAndDeadlineAfterOrderByDeadline(userId, startOfAfterTomorrow);

        List<Task> upcomingTasks = new ArrayList<>();
        upcomingTasks.addAll(todayTasks);
        upcomingTasks.addAll(tomorrowTasks);
        upcomingTasks.addAll(futureTasks);

        taskMap.put("pastTasks", pastTasks);
        taskMap.put("todayTasks", todayTasks);
        taskMap.put("tomorrowTasks", tomorrowTasks);
        taskMap.put("futureTasks", futureTasks);
        taskMap.put("upcomingTasks", upcomingTasks);

        return taskMap;
    }


    public Task updateTask(Principal principal, Task updatedTask, Long taskId) throws AccessDeniedException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id " + taskId));
        if (!task.getUser().getEmail().equals(principal.getName())) {
            throw new AccessDeniedException("You do not have permission to update this task");
        }
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        task.setDeadline(updatedTask.getDeadline());
        return taskRepository.save(task);
    }

    public void deleteTask(Principal principal, Long taskId) throws AccessDeniedException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id " + taskId));
        if (!task.getUser().getEmail().equals(principal.getName())) {
            throw new AccessDeniedException("You do not have permission to delete this task");
        }
        taskRepository.deleteById(taskId);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return null;
        }
        return userRepository.findByEmail(principal.getName());
    }
    public List<Task> getTasksByUser(Long id) {
        List<Task> tasks = new ArrayList<>();

        taskRepository.findByUserId_Id(id)
                .forEach(tasks::add);

        return tasks;
    }

}




