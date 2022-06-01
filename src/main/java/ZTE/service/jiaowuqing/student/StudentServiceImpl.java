package ZTE.service.jiaowuqing.student;

import ZTE.dao.jiaowuqing.student.StudentDao;
import ZTE.dao.jiaowuqing.student.StudentDaoImpl;
import ZTE.entity.jiaowuqing.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDao dao = new StudentDaoImpl();
    @Override
    public boolean addStudentRegister(Student student) {
        return dao.addStudentRegister(student);
    }

    @Override
    public List<Student> getStudentListByPage(Integer zhuanye, Integer banji, String name, int currentpage, int pageSize) {
        return dao.getStudentListByPage(zhuanye,banji,name,currentpage,pageSize);
    }

    @Override
    public boolean deleteStudentById(String id) {
        return dao.deleteStudentById(id);
    }

    @Override
    public Student getStudentById(String id) {
        return dao.getStudentById(id);
    }

    @Override
    public boolean updateStudentById(Student student) {
        return dao.updateStudentById(student);
    }

    @Override
    public int getStudentTotalCount(Integer zhuanye, Integer banji, String name) {
        return dao.getStudentTotalCount(zhuanye,banji,name);
    }

    @Override
    public boolean addStudent(Student student) {
        return dao.addStudent(student);
    }

    @Override
    public List<String> getStudentEducationList() {
        return dao.getStudentEducationList();
    }

    @Override
    public List<String> getLoginCodeList() {
        return dao.getLoginCodeList();
    }

    @Override
    public List<Student> getStudentList(Integer major, Integer classId, String name) {
        return dao.getStudentList(major,classId,name);
    }
}
