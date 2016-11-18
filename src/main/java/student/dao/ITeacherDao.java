package student.dao;


import student.dto.TeacherDto;
import student.model.Teacher;
import sys.basic.idao.IBaseDao;
import sys.basic.model.Pager;

public interface ITeacherDao extends IBaseDao<Teacher> {
	
	public Pager<TeacherDto> find(Integer pid);
}
