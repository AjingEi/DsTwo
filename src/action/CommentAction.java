package action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import po.Comment;
import po.Greenbuild;
import po.Question;
import service.CommentService;
import service.QuestionService;

public class CommentAction {
	private Comment comment;
	private CommentService commentService = null;
	private QuestionService questionService = null;
	
	private  List comments = new ArrayList();
	private  List questions = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public CommentService getCommentService() {
		return commentService;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	//������Ϣ�����get/set
	public List getComments() {
		return comments;
	}
	public void setComments(List comments) {
		this.comments = comments;
	}
	
	public List getQuestions() {
		return questions;
	}
	public void setQuestions(List questions) {
		this.questions = questions;
	}
	
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}	
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Cmsave() {
		 //��ȡϵͳ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String time = sdf.format(new java.util.Date());
		java.util.Date timeDate = null;
		try{
		timeDate = sdf.parse(time);
		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());
		
		
		
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("adminid");
			comment.setHumanid(id);
			comment.setHumantype((String)session.getAttribute("admintype"));
			comment.setQuestionid((int)session.getAttribute("quesid"));
		    comment.setHumanname((String)session.getAttribute("adminname"));
		//���浽��
		comment.setDate(dateTime);
		
		commentService.save(comment);
		return "success";
		}
	
	//�������б�����Ϣ
	 public String CmfindAll() {
		 comments=commentService.findALL();
		
		if (comments!=null && !comments.isEmpty()) {
			
			return "findallCmsuccess";
			}
		return "findallCmfail";
	}
	 //�������������
	 public String  Cfindbyqid() {
			
		 HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			String str=request.getParameter("a");  
	        int id=Integer.parseInt(str);  
	        
			 
			 comments=commentService.findById(id);
		        
			 Question question=new Question();
			 questions=questionService.findById(id);
		        question=(Question) questions.get(0);
		        session.setAttribute("quesid", question.getId());
		        session.setAttribute("title", question.getTitle());
				session.setAttribute("des", question.getDescribe());
				session.setAttribute("img", question.getImg());
				
				return "ufsuccess";
		}
	 //��ѡ��ɾ��
	 public String Cmdelete() {
		 HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			String str=request.getParameter("a");  
	        int id=Integer.parseInt(str);  
	        System.out.println(id);
				Comment comment1=new Comment();
				comment1.setId(id);
				commentService.delete(comment1);
			
			
			return "dsuccess";
	}
	//�޸ĺ���
		public String Cmupdate() {
			commentService.update(comment);
			return "usuccess";
			}
	//��Ϊ�޸ĵ���Ҫ����id����
	public String  Cmupdatefind() {
				
				HttpServletRequest request = ServletActionContext.getRequest();
				  String str=request.getParameter("a");  
			        int id=Integer.parseInt(str);  
			        
			        comments=commentService.findById(id);
			        comment=(Comment) comments.get(0);
					return "ufsuccess";
			}
}
