package com.zandu.readingList.repository;

import com.zandu.readingList.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReaderRepository extends JpaRepository<Reader, String> {
    Optional<Reader> findByUserName(String username);
}
