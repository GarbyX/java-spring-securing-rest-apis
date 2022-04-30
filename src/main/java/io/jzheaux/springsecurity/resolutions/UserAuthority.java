package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;


@Entity(name="authorities")
public class UserAuthority {

    @Id
    UUID id;

    @Column
    String authority;

    // ... getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // --------------------------------------

    @JoinColumn(name="username", referencedColumnName="username")
    @ManyToOne
    User user;

    // ...
    /* Add two constructors, first the default one that JPA expects,
    and then a convenience one that takes a user and an authority:
     */

    UserAuthority() {}

    public UserAuthority(User user, String authority) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.authority = authority;
    }

//    JPA expects you to manage the bi-directional relationship between User and UserAuthority, so, lastly, add a Set<UserAuthority> field as well as a grantAuthority method to User,



}
