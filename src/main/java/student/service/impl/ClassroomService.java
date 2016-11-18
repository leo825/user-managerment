package student.service.impl;

import javax.inject.Inject;


import student.dao.IClassroomDao;
import student.model.Classroom;
import student.service.IClassroomService;
import sys.basic.model.Pager;
import sys.basic.model.SysException;
import sys.org.dao.IOrgDao;
import sys.org.dao.IOrgTypeDao;
import sys.org.model.Org;
import sys.org.model.OrgType;
import org.springframework.stereotype.Service;

@Service("classroomService")
public class ClassroomService implements IClassroomService {
    @Inject
    private IClassroomDao classroomDao;
    @Inject
    private IOrgDao orgDao;
    @Inject
    private IOrgTypeDao orgTypeDao;

    @Override
    public void add(Classroom classroom, int pid) {
        //先添加org之后再添加classroom
        Org org = new Org();
        OrgType ot = orgTypeDao.loadBySn(Classroom.ZZLX);
        if (ot == null) throw new SysException("管理类型的SN不正确!");
        org.setTypeId(ot.getId());
        org.setTypeName(ot.getName());
        Org p = orgDao.load(pid);
        if (p == null) throw new SysException("组织机构类型的父类不正确");
        org.setName(classroom.getName());
        org.setManagerType(Classroom.MANAGER_TYPE);
        int maxOrder = classroomDao.getMaxOrder(pid);
        org.setOrderNum(maxOrder + 1);
        org.setParent(p);
        orgDao.add(org);
        //添加classroom
        classroom.setOrderNum(maxOrder + 1);
        classroom.setPid(p.getId());
        classroom.setPname(p.getName());
        classroom.setOrg(org);
        classroomDao.add(classroom);
    }

    @Override
    public void update(Classroom classroom) {
        Org org = classroom.getOrg();
        org.setName(classroom.getName());
        org.setOrderNum(classroom.getOrderNum());
        orgDao.update(org);
        classroomDao.update(classroom);
    }

    @Override
    public void delete(int id) {
        Classroom cla = classroomDao.load(id);
        orgDao.delete(cla.getOrg().getId());
        classroomDao.delete(id);
    }

    @Override
    public Classroom load(int id) {
        return classroomDao.load(id);
    }

    @Override
    public Pager<Classroom> find(Integer pid) {
        return classroomDao.find(pid);
    }

}
