package org.imanmobile.sms.core.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "groups")
public class Group implements Serializable {
    private static final long serialVersionUID = 2683431443495299217L;
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private Date creationdate;
    private String user_id;
    private List<Recipient> recipients = new ArrayList<>();


    public Group() {
    }

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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + ", description="
                + description + ", creationdate=" + creationdate + ", owner = " + user_id + "]";
    }


}
