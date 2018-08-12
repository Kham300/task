package com.mts.tz.task.controller;

import com.mts.tz.task.model.Task;
import com.mts.tz.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mts.tz.task.util.Util.isGuid;

@RestController
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<Task> getTaskByGuid(@PathVariable("id") String guid) {

        if (guid == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!isGuid(guid)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);        }

        Task task = service.getByGUID(guid);

        if (task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping(value = "/task")
    public ResponseEntity<String> saveTask() {

        Task result = service.saveTask();

        if (result != null){
            service.doTask(result);
            return new ResponseEntity<>(result.getGuid(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
