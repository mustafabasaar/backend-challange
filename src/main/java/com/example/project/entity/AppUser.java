package com.example.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "finalproject")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    /*
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_adress",schema ="finalproject",
     joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="adress_id"))
    private List<Adress> adresses;
*/
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_role",schema ="finalproject",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> authorities ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getauthorities(){
        return authorities;
    }
}
