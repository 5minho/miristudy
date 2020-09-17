package me.minho.miristiudy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@ToString(of = {"id", "name"})
@NoArgsConstructor
public class User {

    @Id
    @Getter
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
