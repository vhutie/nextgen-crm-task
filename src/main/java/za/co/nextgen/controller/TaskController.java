package za.co.nextgen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.nextgen.exception.ResourceNotFoundException;
import za.co.nextgen.model.Task;
import za.co.nextgen.service.TaskService;

@RestController
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService service;
	
	@RequestMapping(value = "/api/task/{taskId}", method = RequestMethod.GET, produces = "application/json")
	 private @ResponseBody
	    ResponseEntity<Object> getById(
	            @PathVariable(name = "taskId", required = false) Long id) {

	        LOGGER.info("obtaining task by id {} ", id);

	        try {
	            return new ResponseEntity<>(service.getByTaskId(id), HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        }
	}
	
	@RequestMapping(value = "/api/task", method = RequestMethod.GET, produces = "application/json")
	 private @ResponseBody
	    ResponseEntity<Object> getAll() {

	        LOGGER.info("obtaining all tasks ");

	        try {
	            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        }
	}
	
	
	@RequestMapping(value = "/api/task", method = RequestMethod.POST, produces = "application/json")
	 private @ResponseBody
	    ResponseEntity<Object> create(@RequestBody(required = true) Task task ) {

	        LOGGER.info("create task");

	        try {
	            return new ResponseEntity<>(service.create(task), HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        }
	}
	
	@RequestMapping(value = "/api/task/extended/by-project/{projectId}", method = RequestMethod.GET, produces = "application/json")
	 private @ResponseBody
	    ResponseEntity<Object> getByProjectId(
	            @PathVariable(name = "projectId", required = false) Long id) {

	        LOGGER.info("obtaining task by projectId {} ", id);

	        try {
	            return new ResponseEntity<>(service.getByProjectId(id), HttpStatus.OK);
	        } catch (ResourceNotFoundException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        }
	}
}
