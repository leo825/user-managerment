package student.service;


import student.model.Classroom;
import sys.basic.model.Pager;

public interface IClassroomService {
    public void add(Classroom classroom, int pid);

    public void update(Classroom classroom);

    public void delete(int id);

    public Classroom load(int id);

    public Pager<Classroom> find(Integer pid);
}
