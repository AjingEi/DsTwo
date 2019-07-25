package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import po.Jobseeker;

public class JobseekerDao extends BaseHibernateDao{
	public JobseekerDao(){
		//	System.out.println("create CustomerDao.");
			}
		
		//����
		public void save(Jobseeker jobseeker) {
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				session.save(jobseeker);
				tran.commit();
			} catch (RuntimeException re) {
				if(tran != null) tran.rollback();
				throw re;
			} finally {
				session.close();
			}
		}
		//�޸�
		public void update(Jobseeker jobseeker) {
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				session.update(jobseeker);
				tran.commit();
			} catch (RuntimeException re) {
				if(tran != null) tran.rollback();
				throw re;
			} finally {
				session.close();
			}
		}
		//ɾ��
		public void delete(Jobseeker jobseeker) {
			Transaction tran = null;
			Session session = null;
			try {
				session = getSession();
				tran = session.beginTransaction();
				session.delete(jobseeker);
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
