package com.rently.rentlyAPI.entity.User;

import com.rently.rentlyAPI.auth.entity.enums.Provider;
import com.rently.rentlyAPI.entity.AbstractEntity;
import com.rently.rentlyAPI.entity.S3File;
import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Annotation Guidance for JPA Entities and Lombok Usage
 *
 * @EqualsAndHashCode(callSuper = true)
 * - Generates equals() and hashCode() that consider superclass fields.
 * - Useful for including inherited fields in equality checks and hashcode calculations.
 * - Use cautiously with JPA entities; entity identity should primarily be defined by its ID.
 *
 * @Data
 * - Bundles @ToString, @EqualsAndHashCode, @Getter, @Setter, and @RequiredArgsConstructor.
 * - Simplifies coding by auto-generating boilerplate code.
 * - May not always be suitable for JPA entities due to potential performance issues and unintended loading of lazy relationships. Consider more specific annotations as needed.
 *
 * @SuperBuilder
 * - Enhances the builder pattern, supporting inherited attributes. Useful for object creation in complex hierarchies.
 * - Ensure to annotate subclasses with @SuperBuilder to handle inherited fields correctly.
 *
 * @NoArgsConstructor
 * - Generates a no-argument constructor.
 * - Required by JPA for all entities for instantiation via reflection.
 *
 * @AllArgsConstructor
 * - Generates a constructor with a parameter for each field.
 * - Might not be necessary for JPA entities and could interfere with entity usage, especially in relationships.
 *
 * @Entity
 * - Marks the class as a JPA entity for database storage.
 * - Essential for all classes intended to map to a database table.
 *
 * @Table(name = "user")
 * - Specifies the table in the database to which the entity is mapped.
 * - Use to specify a custom table name different from the class name. In inheritance, apply on concrete classes for specific table mapping.
 *
 * Recommended Approach:
 * - AbstractEntity: Keep Lombok annotations; @Table not needed.
 * - User and Other Abstract Classes: Use @Getter, @Setter over @Data to avoid potential issues with @EqualsAndHashCode and @ToString. @Entity required; @Table used for concrete class table naming. @SuperBuilder recommended for builder pattern.
 * - Concrete Classes: Include @Entity and @Table as needed for custom table names. Tailor Lombok annotations to class-specific needs, focusing on avoiding issues with entity relationships and performance.
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class User extends AbstractEntity implements UserDetails {
    
    @Column(unique = true)
    @NotBlank(message = "The email is required")
    private String email;
    
    @NotBlank(message = "The password is required")
    private String password;
    
    @NotBlank(message = "The first name is required")
    private String firstName;
    
    @NotBlank(message = "The last name is required")
    private String lastname;
    
    private String phoneNumber;
    
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'RENTLY'")
    private Provider provider;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'USER'")
    private Role role;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_picture_id", referencedColumnName = "id")
    private S3File profilePicture;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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
}
