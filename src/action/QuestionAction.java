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

import po.Greenbuild;
import po.Hr;
import po.Jobseeker;
import po.Question;
import po.Student;
import service.QuestionService;

public class QuestionAction {
	private Question question;
	private QuestionService questionService = null;
	
	//����ͼƬ��ر���
	private File pic;
	private String picContentType;
	private String picFileName;
	
	private  List questions = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	//�ϴ�ͼƬ���get/set
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	
	//������Ϣ�����get/set
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
	public String Qsave() {
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
		
		//����ͼƬ��·��ת��
		try {
		String realPath=ServletActionContext.getServletContext().getRealPath("/images");
		
		System.out.println(realPath);
		File file=new File(realPath,picFileName);
		FileUtils.copyFile(pic, file);
		}catch(IOException e) {
			ActionContext.getContext().put("message", "ͼƬ�ϴ�");
		}
		
		
		
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("adminid");
			question.setHumenid(id);
			question.setHumentype((String)session.getAttribute("admintype"));
		
		
		
		
		
		//���浽��
		
		question.setImg(picFileName);
		question.setDate(dateTime);
		
		questionService.save(question);
		return "success";
		}
	
	//�������б�����Ϣ
	 public String QfindAll() {
		 questions=questionService.findALL();
		
		if (questions!=null && !questions.isEmpty()) {
			
			return "findallQsuccess";
			}
		return "findallQfail";
	}
	//ͨ��id����
	 public void Qfindbyid(int id) {
		 HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
	       System.out.println(id); 
	       questions=questionService.findById(id);
	       question=(Question) questions.get(0);
	       System.out.println(question.getTitle());
	       session.setAttribute("title", question.getTitle());
			session.setAttribute("des", question.getDescribe());
			session.setAttribute("img", question.getImg());
	}
	 //��ѡ��ɾ��
	 public String Qdelete() {
		 Question question1=new Question();
			 //����Struts2�����������õ�jspҳ�洫��ids
			String[] id = getCheckid().split(",");
			int x;
			//String[] ����ת����Integer ����
			for(int i=0;i<id.length;i++) {
				//ע��ȥ���ո�
				x = Integer.parseInt(id[i].trim());
				System.out.println(x);
				question1.setId(x);
				questionService.delete(question1);
			}
			
			return "dsuccess";
	 }
	//ģ������
	 public String QfindbyEverything() {
		String str=question.getSs();
	       questions=questionService.findByEverything(str);
	    if(questions!=null)
	    {
	    	return "Qfindsuccess";
	    }
	    return "Qfindfail";
	}
	//�޸ĺ���
	public String Qupdate() {
				questionService.update(question);
							return "usuccess";
							}
	//��Ϊ�޸ĵ���Ҫ����id����
	public void  Qupdatefind(int id) {
		 HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
				//  String str=request.getParameter("a");  
			       // int id=Integer.parseInt(str);  
			        
			        questions=questionService.findById(id);
			        question=(Question) questions.get(0);
			        session.setAttribute("title", question.getTitle());
					session.setAttribute("des", question.getDescribe());
					
							}
}
