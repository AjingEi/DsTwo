package service;

import java.util.List;

import dao.AdministratorDao;
import po.Administrator;

public class AdministratorService {
private AdministratorDao administratorDao = null;
	
	public AdministratorService(){
	}
	
	public void setAdministratorDao(AdministratorDao administratorDao) {
		this.administratorDao = administratorDao;
	}
	
	//����
	public void save(Administrator administrator) {
		administratorDao.save(administrator);
	}
	//ɾ��
	public void delete(Administrator administrator) {
		administratorDao.delete(administrator);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Administrator";
			List administrators = administratorDao.findByHql(hql);
		    return administrators;
		}
	//ͨ���û�������
	public List findByUsername(String username)
	{
		String hql="from Administrator as administrator where username='"+username+ "'";
		List administrators = administratorDao.findByHql(hql);
	    return administrators;
	}
	
	//�޸�
	public void update(Administrator administrator) {
		administratorDao.update(administrator);
		}
	
	public List findById(int id)
	{
		String hql = "from Administrator as administrator where id='"+id+ "'";
		List administrators = administratorDao.findByHql(hql);
	    return administrators;
	}
}
