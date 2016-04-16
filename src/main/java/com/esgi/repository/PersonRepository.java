package com.esgi.repository;

import com.esgi.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class PersonRepository
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select a from Person a where name = :name_param")
    List<Person> findByName(@Param("name_param") String name);

    Page<Person> findAllBy(Pageable pageable);
}
