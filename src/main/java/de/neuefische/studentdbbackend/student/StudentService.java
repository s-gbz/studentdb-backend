package de.neuefische.studentdbbackend.student;

import de.neuefische.studentdbbackend.util.MatriculationNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final MatriculationNumberService matriculationNumberService;

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> StudentResponseDto.builder()
                        .matriculationNumber(student.getMatriculationNumber())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .age(student.getAge())
                        .course(student.getCourse())
                        .build())
                .toList();
    }

    public List<StudentResponseDto> getStudentsByFirstName(String firstName) {
        return studentRepository.findAllByFirstNameEqualsIgnoreCase(firstName)
                .stream()
                .map(student -> StudentResponseDto.builder()
                        .matriculationNumber(student.getMatriculationNumber())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .age(student.getAge())
                        .course(student.getCourse())
                        .build())
                .toList();
    }

    public List<StudentResponseDto> getStudentsByLastName(String lastName) {
        return studentRepository.findAllByLastNameEqualsIgnoreCase(lastName)
                .stream()
                .map(student -> StudentResponseDto.builder()
                        .matriculationNumber(student.getMatriculationNumber())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .age(student.getAge())
                        .course(student.getCourse())
                        .build())
                .toList();
    }

    public List<StudentResponseDto> getStudentsByCourse(String course) {
        return studentRepository.findAllByCourseEqualsIgnoreCase(course)
                .stream()
                .map(student -> StudentResponseDto.builder()
                        .matriculationNumber(student.getMatriculationNumber())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .age(student.getAge())
                        .course(student.getCourse())
                        .build())
                .toList();
    }

    public StudentResponseDto getStudentByFirstNameAndLastName(String firstName, String lastName) {
        Student student = studentRepository
                .findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new StudentNotFoundException("Student with first name " + firstName + " and last name " + lastName + " where not found"));

        return StudentResponseDto.builder()
                .matriculationNumber(student.getMatriculationNumber())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .course(student.getCourse())
                .build();
    }

    public StudentResponseDto getStudentByMatriculationNumber(String matriculationNumber) {
        Student student = studentRepository
                .findByMatriculationNumber(matriculationNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student with matriculation number " + matriculationNumber + " not found"));

        return StudentResponseDto.builder()
                .matriculationNumber(student.getMatriculationNumber())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .course(student.getCourse())
                .build();
    }

    public StudentResponseDto addStudent(NewStudentDto studentRequestDto) {
        Student student = Student.builder()
                .matriculationNumber(matriculationNumberService.generateMatriculationNumber())
                .firstName(studentRequestDto.getFirstName())
                .lastName(studentRequestDto.getLastName())
                .age(studentRequestDto.getAge())
                .course(studentRequestDto.getCourse())
                .build();

        Student savedStudent = studentRepository.save(student);

        return StudentResponseDto.builder()
                .matriculationNumber(savedStudent.getMatriculationNumber())
                .firstName(savedStudent.getFirstName())
                .lastName(savedStudent.getLastName())
                .age(savedStudent.getAge())
                .course(savedStudent.getCourse())
                .build();
    }

    public StudentResponseDto updateStudent(String matriculationNumber, NewStudentDto updateStudentDto) {
        Student student = studentRepository
                .findByMatriculationNumber(matriculationNumber)
                .orElseThrow(() -> new StudentNotFoundException("Student with matriculation number " + matriculationNumber + " not found"));

        student.setFirstName(updateStudentDto.getFirstName());
        student.setLastName(updateStudentDto.getLastName());
        student.setAge(updateStudentDto.getAge());
        student.setCourse(updateStudentDto.getCourse());

        Student savedStudent = studentRepository.save(student);

        return StudentResponseDto.builder()
                .matriculationNumber(savedStudent.getMatriculationNumber())
                .firstName(savedStudent.getFirstName())
                .lastName(savedStudent.getLastName())
                .age(savedStudent.getAge())
                .course(savedStudent.getCourse())
                .build();
    }

    public void deleteStudent(String matriculationNumber) {
        studentRepository.deleteByMatriculationNumber(matriculationNumber);
    }

}
