package za.co.nextgen.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import za.co.nextgen.exception.ResourceNotFoundException;
import za.co.nextgen.model.Task;
import za.co.nextgen.repo.TaskRepo;

@Service
public class TaskService {

	
	@Autowired
	private TaskRepo repo;
	
	@Value( "${environment_host}" )
	private String environmentHost;
	
	@Value( "${resource_not_found_exception}" )
	private String resourceNotFound;
	
	@Value( "${list_of_resources_not_found_exception}" )
	private String listOfResourcesNotFound;
	
	//POST
	public Task create(Task task) {
		Task savedTask = null;
		try {
			task.setCreateDate(new Date());
			savedTask = repo.saveAndFlush(task);
			//http://nextgen-crm-project/api/project/{ID} whatever the ID is 
			savedTask.setHref(String.format("%s%s%s", environmentHost,"/api/task/",savedTask.getId()));
			repo.save(savedTask);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return savedTask;
	}
	
	//GET ALL
	public List<Task> getAll() {
		List<Task> resources = repo.findAll();
		
		if(Objects.isNull(resources) || resources.isEmpty()){
			throw new ResourceNotFoundException(listOfResourcesNotFound);
		}
		
		return resources;
	}
	
	//GET BY PROJECT ID
	public Task getByTaskId(Long id) {
		//Find Resource or Throw ResourceNotFoundException
		Task resource = repo.
						findById(id).
						orElseThrow(()-> new ResourceNotFoundException(String.format(resourceNotFound, id)));
		return resource;
	}
	
	
	//GET ALL BY PROJECT ID
	public List<Task> getByProjectId(Long projectId) {
		List<Task> resources = repo.getTasksByProjectId(projectId);
		
		if(Objects.isNull(resources) || resources.isEmpty()){
			throw new ResourceNotFoundException(listOfResourcesNotFound);
		}
		
		return resources;
	}
	
	
	
	
}
