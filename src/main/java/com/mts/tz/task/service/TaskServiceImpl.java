package com.mts.tz.task.service;

import com.mts.tz.task.model.Task;
import com.mts.tz.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String STATUS_CREATED = "created";
    private static final String STATUS_RUN = "running";
    private static final String STATUS_FINISH = "finished";

    @Autowired
    private TaskRepository repository;

    public String getRandomUUIDString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    @Override
    public Task getByGUID(String guid) {
        return repository.findByGuid(guid);
    }

    @Override
    public Task saveTask() {
        Task task = new Task();
        String guid = getRandomUUIDString();
        task.setGuid(guid);
        task.setStatus(STATUS_CREATED);
        task.setDateTime(LocalDateTime.now());
        repository.save(task);
        return task;
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void doTask(Task task) {
        repository.setUserInfoById(STATUS_RUN, LocalDateTime.now(), task.getGuid());
        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            repository.setUserInfoById(STATUS_FINISH, LocalDateTime.now(), task.getGuid());
        }
    }
}
