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

import po.Hr;
import po.Question;
import po.Student;
import service.StudentService;

public class StudentAction {
	private Student student;
	private StudentService studentService = null;
	
	//����ͼƬ��ر���
	private File pic;
	private String picContentType;
	private String picFileName;
	
	private  List students = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
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
	public List getStudents() {
		return students;
	}
	public void setStudents(List students) {
		this.students = students;
	}
	
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Stsave() {
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//����ͼƬ��·��ת��
		try {
		String realPath=ServletActionContext.getServletContext().getRealPath("/images");
		
		System.out.println(realPath);
		File file=new File(realPath,picFileName);
		FileUtils.copyFile(pic, file);
		}catch(IOException e) {
			ActionContext.getContext().put("message", "ͼƬ�ϴ�");
		}
		//�ж��Ƿ��Ѵ���
		String username=student.getUsername();
		students=studentService.findByUsername(username);
			if (students!=null && !students.isEmpty()) {
				session.setAttribute("registerfail", "�û����Ѵ���");
				return "registerfail";
			}
		//���浽��
		student.setImg(picFileName);
		student.setType("ѧ��");
		
		studentService.save(student);
		return "success";
		}
	public String Stlogin() {
		//��ȡsession
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String username=student.getUsername();
		String password=student.getPassword();
		students=studentService.findByUsername(username);
			
			if (students!=null && !students.isEmpty()) {
				student=(Student) students.get(0);
				
				 if(student.getPassword().equals(password))
				 {
					 session.setAttribute("logina", null);
					 session.setAttribute("admin", student);
					 session.setAttribute("admintype", student.getType());
					 session.setAttribute("adminname", student.getName());
					 session.setAttribute("adminid", student.getId());
					 session.setAttribute("Sloginfail", "");
					 return "loginsuccess";
				 }
				 else
				 {
					 student.setPassword("");
					 session.setAttribute("logina", 4);
					 session.setAttribute("Sloginfail", "�������");
					 return "loginfail";
				 }
					 
			} 
			else
			{
				session.setAttribute("logina", 4);
				session.setAttribute("Sloginfail", "�û���������");
				return "loginfail";
			}
	}
	//�������б�����Ϣ
	 public String StfindAll() {
		 students=studentService.findALL();
		
		if (students!=null && !students.isEmpty()) {
			
			return "findallSsuccess";
			}
		return "findallSfail";
	}
	 
	 //��ѡ��ɾ��
	 public String Stdelete() {
		 Student student1=new Student();
			 //����Struts2�����������õ�jspҳ�洫��ids
			String[] id = getCheckid().split(",");
			int x;
			//String[] ����ת����Integer ����
			for(int i=0;i<id.length;i++) {
				//ע��ȥ���ո�
				x = Integer.parseInt(id[i].trim());
				System.out.println(x);
				student1.setId(x);
				studentService.delete(student1);
			}
			
			return "dsuccess";
	}
	//�޸ĺ���
		public String Stupdate() {
			HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			try {
				String realPath=ServletActionContext.getServletContext().getRealPath("/images");
				
				
				System.out.println(realPath);
				File file=new File(realPath,picFileName);
				FileUtils.copyFile(pic, file);
				}catch(IOException e) {
					ActionContext.getContext().put("message", "ͼƬ�ϴ�");
				}
			student.setType("ѧ��");
			student.setImg(picFileName);
			session.setAttribute("Sloginfail", "�޸ĳɹ��������µ�½");
			session.setAttribute("logina", 4);
			studentService.update(student);
			student.setPassword("");
								return "usuccess";
								}
		//��Ϊ�޸ĵ���Ҫ����id����
		public String  Stupdatefind() {
			//��ȡsession
			HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			
			
		        int id=(int)session.getAttribute("adminid"); 
				        
				        students=studentService.findById(id);
				        student=(Student) students.get(0);
						return "ufsuccess";
								}

}
