package com.mts.tz.task.repository;

import com.mts.tz.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    Task findByGuid(String guid);

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.status = ?1, t.dateTime = ?2 where t.guid = ?3")
    void setUserInfoById(String status, LocalDateTime dateTime, String guid);

}
