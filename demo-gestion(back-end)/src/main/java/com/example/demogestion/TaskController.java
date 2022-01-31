package com.example.demogestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cli")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/addTasks")
    public void addAllTasks(@RequestBody List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return  taskRepository.findAll();
    }

    @GetMapping("/getTaskById/{id}")
    public Task getTaskById(@PathVariable String id){
        return taskRepository.findById(id).orElseGet(Task::new);
    }

}
