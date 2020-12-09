package com.uit.ooad.scienceresearch.entity;

import com.uit.ooad.scienceresearch.constant.ERole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/6/2020
 */
@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role extends BaseEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private ERole code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return code == role.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Role{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
