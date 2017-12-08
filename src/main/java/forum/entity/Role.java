package forum.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "forum")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }

    public Role() {
    }
}
