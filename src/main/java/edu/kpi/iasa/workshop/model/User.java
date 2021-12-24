package edu.kpi.iasa.workshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User {
    public static final User USER = new User("anonymous");

    private Long id;
    private String username;
    private String password;
    private String email;
    private int uploads;
    private int downloads;
    private int score;
    private LocalDate registration_date;
    private Collection<Role> roles = new HashSet<>();


    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 4,
            max = 127,
            message = "validation.text.error.from.four.to.hundred.twenty.seven")
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 6,
            max = 127,
            message = "validation.text.error.from.six.to.hundred.twenty.seven")
    @Column(name = "Hashed_pass")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 10,
            max = 50,
            message = "validation.text.error.from.ten.to.fifty")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "validation.text.email.error.sample")
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Uploads")
    public int getUploads() {
        return uploads;
    }

    public void setUploads(int uploads) {
        this.uploads = uploads;
        this.score = this.uploads - this.downloads;
    }

    @Column(name = "Downloads")
    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
        this.score = this.uploads - this.downloads;
    }

    @Column(name = "Score")
    public int getScore(){return score;}

    public void setScore(int score) {
        this.score = this.uploads - this.downloads;
    }


    @Column(name = "Registration_date")
    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate dateCreated) {
        this.registration_date = dateCreated;
    }

    @OneToMany
    @JoinTable(
            name = "users_has_roles",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "role_id",
                            referencedColumnName = "id")})
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(getRoles());
        return authorities;
    }

    public boolean hasRole(String role) {
        for (Role r : getRoles()) {
            if (r.getCode().equals(role)) return true;
        }
        return false;
    }

    public String toString() {
        return username;
    }

}
