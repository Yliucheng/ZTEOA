package ZTE.service.jiaowuqing.student;

import ZTE.entity.jiaowuqing.Student;

import java.util.List;

public interface StudentService {
    /**
     * 增加注册学生对象
     * @param student 学生对象
     * @return 返回增加状态
     */
    boolean addStudentRegister(Student student);

    /**
     * 根据专业id、班级id、姓名条件查询所有符合的记录数
     * @param zhuanye 专业id
     * @param banji 班级id
     * @param name 姓名
     * @return 返回查询的记录数
     */
    int getStudentTotalCount(Integer zhuanye, Integer banji, String name);

    /**
     * 通过专业id、班级id、姓名条件分页查询所有符合的学生对象集合
     * @param zhuanye 专业id
     * @param banji 班级id
     * @param name 姓名
     * @param currentpage 当前页号
     * @param pageSize 页面尺寸(单页面显示的记录数)
     * @return 返回分页显示的学生对象列表
     */
    List<Student> getStudentListByPage(Integer zhuanye, Integer banji, String name, int currentpage, int pageSize);

    /**
     * 根据学生id删除学生
     * @param id 学生id
     * @return 返回删除结果
     */
    boolean deleteStudentById(String id);

    /**
     * 通过学生id返回学生信息
     * @param id 学生id
     * @return 返回学生对象
     */
    Student getStudentById(String id);

    /**
     * 根据学生id更新学生信息
     * @param student 修改学生信息封装的学生对象
     * @return 返回更新结果
     */
    boolean updateStudentById(Student student);

    /**
     * 增加学生
     * @param student 增加学生信息封装的学生对象
     * @return 返回增加学生的结果
     */
    boolean addStudent(Student student);

    /**
     * 去重查询ztestudent表中的学历信息
     * @return String 返回学历信息
     */
    List<String> getStudentEducationList();

    /**
     * 获取所有登录账户
     * @return 返回所有账户列表
     */
    List<String> getLoginCodeList();

    List<Student> getStudentList(Integer major, Integer classId, String name);
}
