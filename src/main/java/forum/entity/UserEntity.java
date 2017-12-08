package forum.entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "forum")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 4, max = 16)
    @NotBlank
    @Column(name = "login", nullable = false, length = 20, unique = true)
    private String login;

    @Size(min = 4, max = 16)
    @NotBlank
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Transient
    private transient String confirmPassword;

    @Size(min = 4, max = 16)
    @NotBlank
    @Column(name = "first_name" , nullable = false, length = 64)
    private String name;

    @Size(min = 4, max = 16)
    @NotBlank
    @Column(name = "last_name" , nullable = false, length = 64)
    private String lastName;

    @Email
    @NotBlank
    @Column(name = "email" , nullable = false, length = 64, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MessageEntity> userMessages;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public UserEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public List<MessageEntity> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<MessageEntity> userMessages) {
        this.userMessages = userMessages;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
