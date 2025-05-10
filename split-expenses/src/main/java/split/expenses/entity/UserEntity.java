package split.expenses.entity;

import io.quarkus.security.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@User
@Table(name = "users", schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String password;

    private boolean enabled;

    private Date createdAt;

    @OneToMany(mappedBy = "createdBy")
    private List<GroupEntity> groupsCreated;

    @OneToMany(mappedBy = "user")
    private List<ParticipantEntity> participations;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<GroupEntity> getGroupsCreated() {
        return groupsCreated;
    }

    public void setGroupsCreated(GroupEntity group) {
        this.groupsCreated.add(group);
    }

    public List<ParticipantEntity> getParticipations() {
        return participations;
    }

    public void setParticipations(ParticipantEntity participation) {
        this.participations.add(participation);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
