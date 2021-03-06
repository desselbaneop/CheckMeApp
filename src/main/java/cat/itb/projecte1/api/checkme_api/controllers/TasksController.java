package cat.itb.projecte1.api.checkme_api.controllers;

import cat.itb.projecte1.api.checkme_api.model.entities.Task;
import cat.itb.projecte1.api.checkme_api.model.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TasksController {
    private final TasksService tasksService;

    @GetMapping("/todoitems")
    public List<Task> listTasks(){
        return tasksService.listTasks();
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity<?> getTask(@PathVariable String id){
        Task res = tasksService.getTask(id);
        if (res == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(res);
        }
    }

    @PostMapping("/todoitems")
    public ResponseEntity<?> addTask(@RequestBody Task task){
        Task res = tasksService.addTask(task);
        return new ResponseEntity<Task>(res, HttpStatus.CREATED);
    }

    @PutMapping("/todoitems/{id}")
    public ResponseEntity<?> modifyTask(@RequestBody Task task){
        Task res = tasksService.modifyTask(task);
        if (res == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(res);
        }
    }

    @DeleteMapping("todoitems/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable String id){
        Task res = tasksService.deleteTask(id);
        return new ResponseEntity<Task>(res, HttpStatus.NO_CONTENT);
    }
}
