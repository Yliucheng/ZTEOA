package ZTE.dao.jiaowuqing.major;

import ZTE.entity.jiaowuqing.Major;

import java.util.List;

public interface MajorDao {
    /**
     * 查询ztemajor表所有专业对象列表
     * @return 返回所有专业对象列表
     */
    List<Major> getMajorList();
}
