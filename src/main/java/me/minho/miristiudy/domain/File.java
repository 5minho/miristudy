package me.minho.miristiudy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "file")
@NoArgsConstructor
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column
    private String key;

    @Column
    private Long size;

    public File(String key, long size) {
        this.key = key;
        this.size = size;
    }
}
