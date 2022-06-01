package ZTE.service.yichen.classes;

import ZTE.entity.yichen.Classes;

import java.util.ArrayList;
import java.util.List;

public interface ClassService {
    //根据查询条件获取所有的班级信息
    ArrayList<Classes> getClassList(Integer majorIds, Integer currentPageNo, Integer pageSize);
    //获取班级信息的总数
    int getClassListCount(Integer majorIds);
    List<Classes> getClassList();
    //获取查询条件下拉框内容
    ArrayList<Classes> getStudyTypeAll();
    //判断添加班级是否成功
    boolean addClass(String classname, int studytype);
    //根据id获取班级信息放置到修改页面
    Classes getClassList(int id);
    //修改班级信息
    boolean ModifyClass(Classes classes);
    //判断该班级是否还有学生
    boolean getClassStduentCount(int classid);
    //根据班级ID删除班级信息
    boolean DeleteClass(int classid);
    //班级验证，判断输入内容是否已存在
    boolean PanDuanClassName(String classname);
    //导出的班级信息
    ArrayList<Classes> getClassList2(Integer majorId);
}
