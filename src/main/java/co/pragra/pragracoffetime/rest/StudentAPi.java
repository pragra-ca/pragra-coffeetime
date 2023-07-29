package co.pragra.pragracoffetime.rest;

import co.pragra.pragracoffetime.entity.Course;
import co.pragra.pragracoffetime.entity.Student;
import co.pragra.pragracoffetime.repo.CourseRepo;
import co.pragra.pragracoffetime.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentAPi {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @PostMapping("/api/student")
    public Student create(@RequestBody Student student) {
        Course course = courseRepo.save(student.getCourse());
        student.setCourse(course);

        return studentRepo.save(student);
    }

    @PutMapping("/api/student")
    public Student update(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @DeleteMapping("/api/student/{id}")
    public void delete(@PathVariable long id) {
         studentRepo.deleteById(id);
    }
}
