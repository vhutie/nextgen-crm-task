package za.co.nextgen.test;

import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.nextgen.NextgenServiceApplication;
import za.co.nextgen.exception.ResourceNotFoundException;
import za.co.nextgen.model.ProjectRef;
import za.co.nextgen.model.Task;
import za.co.nextgen.service.TaskService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = NextgenServiceApplication.class)
public class ServiceTest {

	@Autowired
	private TaskService service;
	
	//Test 1 Fail Fetch All
	@Test
	@Order(1)
	public void test_fetch_all_fail() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.getAll();
		});
	}
	
	//Test 2 Fail Fecth By Id
	@Test
	@Order(2)
	public void test_fetch_by_task_id_fail() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.getByTaskId(1L);
		});
	}
	
	//Test 2 Fail Fecth By Id
		@Test
		@Order(3)
		public void test_fetch_by_project_id_fail() {
			
			Assertions.assertThrows(ResourceNotFoundException.class, () -> {
				service.getByProjectId(1L);
			});
		}
	
	//Test 3 Create Resource
	@Test
	@Order(4)
	public void test_create_resource_success() {
		Task task = service.create(createTask());
		
		Assert.assertEquals(Objects.nonNull(task), true);
	}
	
	//Test 4 Fetch All Success
	@Test
	@Order(5)
	public void test_fetch_all_success() {
		List<Task> tasks = service.getAll();
		
		Assert.assertEquals(Objects.nonNull(tasks), true);
		
		Assert.assertEquals(tasks.isEmpty(), false);
	}
	
	//Test 5 Fecth By Id Success
	@Test
	@Order(6)
	public void test_fetch_by_task_id_success() {
		Task task = service.getByTaskId(1L);
		
		Assert.assertEquals(Objects.nonNull(task), true);
		
		Assert.assertEquals(Objects.nonNull(task.getHref()), true);
	}
	
	//Test 4 Fetch All Success
		@Test
		@Order(7)
		public void test_fetch_all_tasks_by_project_id_success() {
			List<Task> tasks = service.getByProjectId(1L);
			
			Assert.assertEquals(Objects.nonNull(tasks), true);
			
			Assert.assertEquals(tasks.isEmpty(), false);
		}
	
	
	public Task createTask() {
		Task task = new Task();
		task.setName("Task From Test");
		task.setDescription("Task From Test, Spring-Boot Demo");
		
		ProjectRef projectRef = new ProjectRef();
		projectRef.setHref("http://nextgen-crm-project/api/project/1");
		projectRef.setId(1L);
		
		task.setProjectRef(projectRef);
		
		return task;
	}
}
