package ZTE.service.student;

import ZTE.dao.student.StudentDao;
import ZTE.dao.student.StudentDaoImpl;
import ZTE.entity.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService{

    StudentDao dao = new StudentDaoImpl();
    @Override
    public boolean addStudentRegister(Student student) {
        return dao.addStudentRegister(student);
    }

    @Override
    public List<Student> getStudentListWithoutResult(String className,int examType,String examDate, int pageSize, int currentPage) {
        return dao.getStudentListWithoutResult(className,examType,examDate,pageSize,currentPage);
    }

    @Override
    public int getStudentCountByClassName(String className,int examType,String examDate) {
        return dao.getStudentCountByClassName(className,examType,examDate);
    }

    @Override
    public int getStudentCountByClassName(String className, String attDate) {
        return dao.getStudentCountByClassName(className,attDate);
    }

    @Override
    public List<Student> getStudentListWithoutAtt(String className, String attDate, int pageSize, int currentPage) {
        return dao.getStudentListWithoutAtt(className,attDate,pageSize,currentPage);
    }
}
