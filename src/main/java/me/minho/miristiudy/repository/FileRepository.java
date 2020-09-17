package me.minho.miristiudy.repository;

import me.minho.miristiudy.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
