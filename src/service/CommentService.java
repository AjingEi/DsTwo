package service;

import java.util.List;

import dao.CommentDao;
import po.Comment;

public class CommentService {
private CommentDao commentDao = null;
	
	public CommentService(){
	}
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	//����
	public void save(Comment comment) {
		commentDao.save(comment);
	}
	//ɾ��
	public void delete(Comment comment) {
		commentDao.delete(comment);
			}
		//�������б�����Ϣ
	public List findALL()
		{
			String hql = "from Comment";
			List comments = commentDao.findByHql(hql);
		    return comments;
		}
	
	
	//�޸�
	public void update(Comment comment) {
		commentDao.update(comment);
		}
	
	public List findById(int id)
	{
		String hql = "from Comment as comment where questionid='"+id+ "'";
		List comments = commentDao.findByHql(hql);
	    return comments;
	}
}
