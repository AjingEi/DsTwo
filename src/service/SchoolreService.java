package service;

import java.util.List;

import dao.SchoolreDao;
import po.Schoolre;

public class SchoolreService {
private SchoolreDao schoolreDao = null;
	
	public SchoolreService(){
	}
	
	public void setSchoolreDao(SchoolreDao schoolreDao) {
		this.schoolreDao = schoolreDao;
	}
	
	//����
	public void save(Schoolre schoolre) {
		schoolreDao.save(schoolre);
	}
	//ɾ��
	public void delete(Schoolre schoolre) {
		schoolreDao.delete(schoolre);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Schoolre";
			List schoolres = schoolreDao.findByHql(hql);
		    return schoolres;
		}
	
	public List findById(int id)
	{
		String hql = "from Schoolre as schoolre where id='"+id+ "'";
		List schoolres = schoolreDao.findByHql(hql);
	    return schoolres;
	}
	//�޸�
	public void update(Schoolre schoolre) {
		schoolreDao.update(schoolre);
		}

	public List findByEverything(String str)
	{
		String hql = "from Schoolre as schoolre where companyname LIKE'%"+str+ "%'or title LIKE'%"+str+ "%'or essay LIKE'%"+str+ "%'";
		List schoolres = schoolreDao.findByHql(hql);
	    return schoolres;
	}
}
