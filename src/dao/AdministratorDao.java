package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import po.Administrator;

public class AdministratorDao extends BaseHibernateDao{
	public AdministratorDao(){
		//	System.out.println("create CustomerDao.");
			}
		
		//����
		public void save(Administrator administrator) {
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				session.save(administrator);
				tran.commit();
			} catch (RuntimeException re) {
				if(tran != null) tran.rollback();
				throw re;
			} finally {
				session.close();
			}
		}
		//�޸�
		public void update(Administrator administrator) {
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				session.update(administrator);
				tran.commit();
			} catch (RuntimeException re) {
				if(tran != null) tran.rollback();
				throw re;
			} finally {
				session.close();
			}
		}
		//ɾ��
		public void delete(Administrator administrator) {
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				session.delete(administrator);
				tran.commit();
			} catch (RuntimeException re) {
				if(tran != null) tran.rollback();
				throw re;
			} finally {
				session.close();
			}
		}
		//ͨ��hql������
		public List findByHql(String hql)
		{
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				Query queryObject = session.createQuery(hql);
				return queryObject.list();
				} catch (RuntimeException re) {
					throw re;
					} finally{
						session.close();
						}
		}
}
