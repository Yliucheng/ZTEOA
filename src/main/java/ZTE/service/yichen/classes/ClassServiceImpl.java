package ZTE.service.yichen.classes;

import ZTE.dao.yichen.classes.ClassDao;
import ZTE.dao.yichen.classes.ClassDaoImpl;
import ZTE.entity.yichen.Classes;

import java.util.ArrayList;
import java.util.List;

public class ClassServiceImpl implements ClassService {
    ClassDao classDao = new ClassDaoImpl();
    //根据查询条件获取所有的班级信息
    @Override
    public ArrayList<Classes> getClassList(Integer majorIds, Integer currentPageNo, Integer pageSize) {
        return classDao.getClassList(majorIds,currentPageNo,pageSize);
    }
    //获取班级信息的总数
    @Override
    public int getClassListCount(Integer majorIds) {
        return classDao.getClassListCount(majorIds);
    }

    @Override
    public List<Classes> getClassList() {
        return classDao.getClassList();
    }
    //获取查询条件下拉框内容
    @Override
    public ArrayList<Classes> getStudyTypeAll() {
        return classDao.getStudyTypeAll();
    }
    //判断添加班级是否成功
    @Override
    public boolean addClass(String classname, int studytype) {
        return classDao.addClass(classname,studytype);
    }
    //根据id获取班级信息放置到修改页面
    @Override
    public Classes getClassList(int id) {
        return classDao.getClassList(id);
    }
    //修改班级信息
    @Override
    public boolean ModifyClass(Classes classes) {
        return classDao.ModifyClass(classes);
    }
    //判断该班级是否还有学生
    @Override
    public boolean getClassStduentCount(int classid) {
        return classDao.getClassStduentCount(classid);
    }
    //根据班级ID删除班级信息
    @Override
    public boolean DeleteClass(int classid) {
        return classDao.DeleteClass(classid);
    }
    //班级验证，判断输入内容是否已存在
    @Override
    public boolean PanDuanClassName(String classname) {
        return classDao.PanDuanClassName(classname);
    }

    @Override
    public ArrayList<Classes> getClassList2(Integer majorId) {
        return classDao.getClassList2(majorId);
    }
}
