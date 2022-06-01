package ZTE.service.classes;

import ZTE.dao.classes.ClassDao;
import ZTE.dao.classes.ClassDaoImpl;
import ZTE.entity.Classes;
import ZTE.entity.Major;

import java.util.ArrayList;
import java.util.List;

public class ClassServiceImpl implements ClassService{
    ClassDao classDao = new ClassDaoImpl();
    @Override
    public ArrayList<Classes> getClassList(Integer majorIds, Integer currentPageNo, Integer pageSize) {
        return classDao.getClassList(majorIds,currentPageNo,pageSize);
    }

    @Override
    public int getClassListCount(Integer majorIds) {
        return classDao.getClassListCount(majorIds);
    }

    @Override
    public List<Classes> getClassList(Integer majorType) {
        return classDao.getClassList(majorType);
    }

    @Override
    public ArrayList<Classes> getStudyTypeAll() {
        return classDao.getStudyTypeAll();
    }

    @Override
    public boolean addClass(String classname, int studytype) {
        return classDao.addClass(classname,studytype);
    }

    @Override
    public List<Major> getMajorList() {
        return classDao.getMajorList();
    }
}
