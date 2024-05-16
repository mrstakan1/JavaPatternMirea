package ru.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
