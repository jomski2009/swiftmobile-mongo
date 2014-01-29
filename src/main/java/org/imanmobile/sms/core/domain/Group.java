package org.imanmobile.sms.core.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity(value="groups")
public class Group implements Serializable {
	private static final long serialVersionUID = 2683431443495299217L;
	@Id
	private ObjectId id;
	private String name;
	private String description;
	private long creationdate;
	
	@Reference
	private User user;


    public Group(){}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(long creationdate) {
		this.creationdate = creationdate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description="
				+ description + ", creationdate=" + creationdate + ", owner="
				+ user + "]";
	}
	
	
}
