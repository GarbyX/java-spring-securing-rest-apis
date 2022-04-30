package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity(name="users")
public class User implements Serializable {

    @Id
    UUID id;

    @Column
    String username;

    @Column
    String password;

    @Column
    boolean enabled = true;

    // ... getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*
    Finally, add two constructors, the default one that JPA expects, and a convenience constructor that takes a username and a password:
     */
        User() {}

        public User(String username, String password) {
            this.id = UUID.randomUUID();
            this.username = username;
            this.password = password;
        }

    //    JPA expects you to manage the bi-directional relationship between User and UserAuthority, so, lastly, add a Set<UserAuthority> field as well as a grantAuthority method to User,

    // ...

    @OneToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    Collection<UserAuthority> userAuthorities = new ArrayList<>();

    // ...

    public Collection<UserAuthority> getUserAuthorities() {
        return Collections.unmodifiableCollection(this.userAuthorities);
    }

    public void grantAuthority(String authority) {
        UserAuthority userAuthority = new UserAuthority(this, authority);
        this.userAuthorities.add(userAuthority);
    }

//    public void grantAuthority(String s) {
//    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
        this.userAuthorities = user.userAuthorities;
    }

}
