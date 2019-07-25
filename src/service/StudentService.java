package service;

import java.util.List;

import dao.StudentDao;
import po.Student;

public class StudentService {
private StudentDao studentDao = null;
	
	public StudentService(){
	}
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	//����
	public void save(Student student) {
		studentDao.save(student);
	}
	//ɾ��
	public void delete(Student student) {
		studentDao.delete(student);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Student";
			List students = studentDao.findByHql(hql);
		    return students;
		}
	
	//ͨ���û�������
			public List findByUsername(String username)
			{
				String hql="from Student as student where username='"+username+ "'";
				List students = studentDao.findByHql(hql);
			    return students;
			}
	//�޸�
	public void update(Student student) {
		studentDao.update(student);
		}
	
	public List findById(int id)
	{
		String hql = "from Student as student where id='"+id+ "'";
		List students= studentDao.findByHql(hql);
	    return students;
	}
}
