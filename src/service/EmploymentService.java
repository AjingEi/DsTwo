package service;

import java.util.List;

import dao.EmploymentDao;
import po.Employment;

public class EmploymentService {
private EmploymentDao employmentDao = null;
	
	public EmploymentService(){
	}
	
	public void setEmploymentDao(EmploymentDao employmentDao) {
		this.employmentDao = employmentDao;
	}
	
	//����
	public void save(Employment employment) {
		employmentDao.save(employment);
	}
	//ɾ��
	public void delete(Employment employment) {
		employmentDao.delete(employment);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Employment";
			List employments = employmentDao.findByHql(hql);
		    return employments;
		}
	
	
	//�޸�
	public void update(Employment employment) {
		employmentDao.update(employment);
		}
	
	public List findById(int id)
	{
		String hql = "from Employment as employment where id='"+id+ "'";
		List employments = employmentDao.findByHql(hql);
	    return employments;
	}
	public List findByEverything(String str)
	{
		String hql = "from Employment as employment where education LIKE'%"+str+ "%'or workduty LIKE'%"+str+ "%'or companyname LIKE'%"+str+ "%'or workname LIKE'%"+str+ "%'";
		List employments = employmentDao.findByHql(hql);
	    return employments;
	}
}
