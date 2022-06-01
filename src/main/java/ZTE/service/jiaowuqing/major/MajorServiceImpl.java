package ZTE.service.jiaowuqing.major;

import ZTE.dao.jiaowuqing.major.MajorDao;
import ZTE.dao.jiaowuqing.major.MajorDaoImpl;
import ZTE.entity.jiaowuqing.Major;

import java.util.List;

public class MajorServiceImpl implements MajorService{
    MajorDao dao = new MajorDaoImpl();
    @Override
    public List<Major> getMajorList() {
        return dao.getMajorList();
    }
}
