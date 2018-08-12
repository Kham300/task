package com.mts.tz.task.service;

import com.mts.tz.task.model.Task;

public interface TaskService {

    Task getByGUID(String guid);

    Task saveTask();

    void doTask(Task task);

}
