package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;

    @Getter @Setter
    @Column(name = "username")
    private String username;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "group_id")
    private long groupId;

    public User() {}

    public User(long id, String username, String email, long groupId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return String.format("User { id=%d, username='%s', email='%s', group_id='%s'}",id, username, email, groupId);
    }
}
