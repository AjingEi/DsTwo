package service;

import java.util.List;

import dao.JobseekerDao;
import po.Jobseeker;

public class JobseekerService {
private JobseekerDao jobseekerDao = null;
	
	public JobseekerService(){
	}
	
	public void setJobseekerDao(JobseekerDao jobseekerDao) {
		this.jobseekerDao = jobseekerDao;
	}
	
	//����
	public void save(Jobseeker jobseeker) {
		jobseekerDao.save(jobseeker);
	}
	//ɾ��
	public void delete(Jobseeker jobseeker) {
		jobseekerDao.delete(jobseeker);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Jobseeker";
			List jobseekers = jobseekerDao.findByHql(hql);
		    return jobseekers;
		}
	//ͨ���û�������
			public List findByUsername(String username)
			{
				String hql="from Jobseeker as jobseeker where username='"+username+ "'";
				List jobseekers = jobseekerDao.findByHql(hql);
			    return jobseekers;
			}
	
	//�޸�
	public void update(Jobseeker jobseeker) {
		jobseekerDao.update(jobseeker);
		}
	
	public List findById(int id)
	{
		String hql = "from Jobseeker as jobseeker where id='"+id+ "'";
		List jobseekers= jobseekerDao.findByHql(hql);
	    return jobseekers;
	}
	public List findByEverything(String str)
	{
		String hql = "from Jobseeker as jobseeker where name LIKE'%"+str+ "%'or school LIKE'%"+str+ "%'or worktype LIKE'%"+str+ "%'or education LIKE'%"+str+"%'";
		List jobseekers = jobseekerDao.findByHql(hql);
	    return jobseekers;
	}
}
