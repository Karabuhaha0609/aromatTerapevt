package ru.aromat.aromatTerapevt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aromat.aromatTerapevt.models.Task;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByClientId(Long clientId);
    List<Task> findByUserId_Id(Long id);


    List<Task> findByUserIdAndDeadlineBeforeOrderByDeadline(Long userId, LocalDateTime now);

    List<Task> findByUserIdAndDeadlineBetweenOrderByDeadline(Long userId, LocalDateTime now, LocalDateTime tomorrow);

    List<Task> findByUserIdAndDeadlineAfterOrderByDeadline(Long userId, LocalDateTime afterTomorrow);
}

