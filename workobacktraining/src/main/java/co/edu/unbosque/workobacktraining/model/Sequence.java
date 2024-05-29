package co.edu.unbosque.workobacktraining.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequences")
public class Sequence {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private Long value;
	
	public Sequence() {
		// TODO Auto-generated constructor stub
	}

	public Sequence(String name, Long value) {
		this.name = name;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Sequence [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
