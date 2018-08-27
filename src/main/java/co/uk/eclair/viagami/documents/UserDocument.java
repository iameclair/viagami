package co.uk.eclair.viagami.documents;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * Created by ${Eclair} on 8/19/2018.
 */

@Document(collection="users")
public class UserDocument {
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;
    @DBRef
    private Set<Role> roles;

    public UserDocument() {
    }

    public UserDocument(UserDocument userDocument) {
        this.setId(userDocument.getId());
        this.setName(userDocument.getName());
        this.setEmail(userDocument.getEmail());
        this.setPassword(userDocument.getPassword());
        this.setCreatedDate(userDocument.getCreatedDate());
        this.setRoles(userDocument.getRoles());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString(){
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}

