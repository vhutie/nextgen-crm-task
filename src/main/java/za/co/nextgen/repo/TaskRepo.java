package za.co.nextgen.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import za.co.nextgen.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	List<Task> getTasksByProjectId(@Param("projectId")Long projectId);
}
