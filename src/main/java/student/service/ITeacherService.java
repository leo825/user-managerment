package student.service;


import student.dto.TeacherDto;
import student.model.Teacher;
import sys.basic.model.Pager;

public interface ITeacherService {
    public void add(Teacher teacher);

    public void update(Teacher teacher);

    public Teacher load(int id);

    public Pager<TeacherDto> find(Integer pid);
}
