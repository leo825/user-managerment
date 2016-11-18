package sys.org.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import sys.basic.model.SysException;
import sys.org.dao.IPositionDao;
import sys.org.model.Position;
import sys.org.service.IPositionService;

@Service("positionService")
public class PositionService extends AbstractBaseService implements
        IPositionService {
    @Inject
    private IPositionDao positionDao;

    @Override
    public void add(Position pos) {
        if (positionDao.loadBySn(pos.getSn()) != null) throw new SysException("添加的岗位的sn已经存在");
        positionDao.add(pos);
    }

    @Override
    public void update(Position pos) {
        //if(positionDao.loadBySn(pos.getSn())!=null) throw new SysException("添加的岗位的sn已经存在");
        positionDao.update(pos);
    }

    @Override
    public void delete(int id) {
        positionDao.delete(id);
    }

    @Override
    public Position load(int id) {
        return positionDao.load(id);
    }

    @Override
    public List<Position> find() {
        return positionDao.find();
    }

    @Override
    public List<Position> listByOrg(int orgId) {
        return positionDao.listByOrg(orgId);
    }

}
