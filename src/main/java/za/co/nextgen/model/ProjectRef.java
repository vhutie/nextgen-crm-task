package za.co.nextgen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_ref")
public class ProjectRef implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8181420046388297749L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tableId;
	
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String href;

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	
	
	
	
}
