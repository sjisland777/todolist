package com.su.controller;

import com.su.model.Task;
import com.su.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private TaskRepo taskRepo;

    @RequestMapping("/")
    String index() {
        return "index";
    }


    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Task> list = (List<Task>) taskRepo.findAll();
        model.addAttribute("tasks", list);
        return "tasks";
    }


    @RequestMapping("task/new")
    public String newTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addtask";
    }


    @RequestMapping(value = "task", method = RequestMethod.POST)
    public String save(Task task) {
        taskRepo.save(task);
        return "redirect:/tasks";
    }


    @RequestMapping("task/delete/{id}")
    public String delete(@PathVariable Long id){
        taskRepo.delete(id);
        return "redirect:/tasks";
    }

}
