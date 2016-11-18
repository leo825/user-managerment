package student.dao;


import student.model.Classroom;
import sys.basic.idao.IBaseDao;
import sys.basic.model.Pager;

public interface IClassroomDao extends IBaseDao<Classroom> {
	
	public Pager<Classroom> find(Integer pid);
	
	public int getMaxOrder(Integer pid);
	
}
