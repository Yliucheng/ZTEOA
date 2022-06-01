package ZTE.service.student;

import ZTE.entity.Student;

import java.util.List;

public interface StudentService {
    boolean addStudentRegister(Student student);

    List<Student> getStudentListWithoutResult(String className, int examType, String examDate, int pageSize, int currentPage);

    int getStudentCountByClassName(String className, int examType, String examDate);
    int getStudentCountByClassName(String className, String attDate);

    List<Student> getStudentListWithoutAtt(String className, String attDate, int pageSize, int currentPage);
}
