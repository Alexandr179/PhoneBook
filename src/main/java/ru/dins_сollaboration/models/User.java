package ru.dins_сollaboration.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;
import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ph_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firName;

    @Column(name = "email")// , unique = true
    private String email;

    @Column(name = "hash_password")
    @JsonIgnore
    private String hashPassword;

    @ElementCollection
    @CollectionTable(name = "token", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "value")
    @JsonIgnore
    private List<String> tokens;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhoneNote> phoneNotes;


    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
