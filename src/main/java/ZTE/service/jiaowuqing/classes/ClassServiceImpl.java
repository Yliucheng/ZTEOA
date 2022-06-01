package ZTE.service.jiaowuqing.classes;

import ZTE.dao.jiaowuqing.classes.ClassDao;
import ZTE.dao.jiaowuqing.classes.ClassDaoImpl;
import ZTE.entity.jiaowuqing.Classes;

import java.util.List;

public class ClassServiceImpl implements ClassService {
    ClassDao classDao = new ClassDaoImpl();

    @Override
    public List<Classes> getClassList() {
        return classDao.getClassList();
    }

    @Override
    public List<Classes> getClassListByMajorId(Integer majorId) {
        return classDao.getClassListByMajorId(majorId);
    }



}
