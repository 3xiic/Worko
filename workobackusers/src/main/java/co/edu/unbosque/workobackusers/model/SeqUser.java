package co.edu.unbosque.workobackusers.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequser")
public class SeqUser {
	
	@Id
	private String id;
	private Long value;
	
	public SeqUser() {
		// TODO Auto-generated constructor stub
	}

	public SeqUser(Long value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SeqUser [id=" + id + ", value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}
