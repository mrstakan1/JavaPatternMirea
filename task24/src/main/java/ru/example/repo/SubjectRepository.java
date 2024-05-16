package ru.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Iterable<Subject> findAllByName(String name);
}
