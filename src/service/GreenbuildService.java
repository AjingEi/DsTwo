package service;

import java.util.List;

import dao.GreenbuildDao;
import po.Greenbuild;

public class GreenbuildService {
private GreenbuildDao greenbuildDao = null;
	
	public GreenbuildService(){
	}
	
	public void setGreenbuildDao(GreenbuildDao greenbuildDao) {
		this.greenbuildDao = greenbuildDao;
	}
	
	//����
	public void save(Greenbuild greenbuild) {
		greenbuildDao.save(greenbuild);
	}
	//ɾ��
	public void delete(Greenbuild greenbuild) {
		greenbuildDao.delete(greenbuild);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Greenbuild";
			List greenbuilds = greenbuildDao.findByHql(hql);
		    return greenbuilds;
		}
	
	
	//�޸�
	public void update(Greenbuild greenbuild) {
		greenbuildDao.update(greenbuild);
		}
	
	public List findById(int id)
	{
		String hql = "from Greenbuild as greenbuilding where id='"+id+ "'";
		List greenbuilds = greenbuildDao.findByHql(hql);
	    return greenbuilds;
	}
	public List findByEverything(String str)
	{
		String hql = "from Greenbuild as greenbuilding where materialname LIKE'%"+str+ "%'or title LIKE'%"+str+ "%'or essay LIKE'%"+str+ "%'";
		List greenbuilds = greenbuildDao.findByHql(hql);
	    return greenbuilds;
	}
}
