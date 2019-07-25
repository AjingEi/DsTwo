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
import service.JobseekerService;

public class JobseekerAction {
	private Jobseeker jobseeker;
	private JobseekerService jobseekerService = null;
	
	//����ͼƬ��ر���
	private File pic;
	private String picContentType;
	private String picFileName;

	
	private  List jobseekers = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Jobseeker getJobseeker() {
		return jobseeker;
	}
	public void setJobseeker(Jobseeker jobseeker) {
		this.jobseeker = jobseeker;
	}
	public JobseekerService getJobseekerService() {
		return jobseekerService;
	}
	public void setJobseekerService(JobseekerService jobseekerService) {
		this.jobseekerService = jobseekerService;
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
	public List getJobseekers() {
		return jobseekers;
	}
	public void setJobseekers(List jobseekers) {
		this.jobseekers = jobseekers;
	}
	
	
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Jsave() {
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
		String username=jobseeker.getUsername();
		jobseekers=jobseekerService.findByUsername(username);
			if (jobseekers!=null && !jobseekers.isEmpty()) {
				session.setAttribute("registerfail", "�û����Ѵ���");
				return "registerfail";
			}
		//���浽��
		jobseeker.setImg(picFileName);
		jobseeker.setType("�˲�");
		jobseekerService.save(jobseeker);
		return "success";
		}
	public String Jlogin() {
		//��ȡsession
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String username=jobseeker.getUsername();
		String password=jobseeker.getPassword();
		jobseekers=jobseekerService.findByUsername(username);
			
			if (jobseekers!=null && !jobseekers.isEmpty()) {
				jobseeker=(Jobseeker) jobseekers.get(0);
				
				 if(jobseeker.getPassword().equals(password))
				 {
					 session.setAttribute("logina",null);
					 session.setAttribute("admin", jobseeker);
					 session.setAttribute("admintype", jobseeker.getType());
					 session.setAttribute("adminname", jobseeker.getName());
					 session.setAttribute("adminid", jobseeker.getId());
					 session.setAttribute("Jloginfail", "");
					 return "loginsuccess";
				 }
				 else
				 {
					 jobseeker.setPassword("");
					 session.setAttribute("logina", 2);
					 session.setAttribute("Jloginfail", "�������");
					 return "loginfail";
				 }
					 
			} 
			else
			{
				session.setAttribute("logina", 2);
				session.setAttribute("Jloginfail", "�û���������");
				return "loginfail";
			}
	}
	//�������б�����Ϣ
	 public String JfindAll() {
		 jobseekers=jobseekerService.findALL();
		
		if (jobseekers!=null && !jobseekers.isEmpty()) {
			
			return "findallJsuccess";
			}
		return "findallJfail";
	}
	//ͨ��id����
	 public String Jfindbyid() {
		//��ȡsession
			HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
		 
		  String str=request.getParameter("a"); 
		  System.out.print(str);
	        int id=Integer.parseInt(str);  
	       jobseekers=jobseekerService.findById(id);
	       jobseeker=(Jobseeker) jobseekers.get(0);
	       if(jobseeker.getSex()==1)
	       {
	    	   session.setAttribute("sex", "��");
	       }
	       else{
	    	   session.setAttribute("sex", "Ů");
	       }
			return "ufsuccess";
	}
	//ģ������
	 public String JfindbyEverything() {
		String str=jobseeker.getSs();
	       jobseekers=jobseekerService.findByEverything(str);
	    if(jobseekers!=null)
	    {
	    	return "Jfindsuccess";
	    }
	    return "Jfindfail";
	}
	 //��ѡ��ɾ��
	 public String Jdelete() {
		 Jobseeker jobseeker1=new Jobseeker();
			 //����Struts2�����������õ�jspҳ�洫��ids
			String[] id = getCheckid().split(",");
			int x;
			//String[] ����ת����Integer ����
			for(int i=0;i<id.length;i++) {
				//ע��ȥ���ո�
				x = Integer.parseInt(id[i].trim());
				
				jobseeker1.setId(x);
				jobseekerService.delete(jobseeker1);
			}
			
			return "dsuccess";
	}
	//�޸ĺ���
		public String Jupdate() {
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
			jobseeker.setImg(picFileName);
			session.setAttribute("Jloginfail", "�޸ĳɹ��������µ�½");
			session.setAttribute("logina", 2);
			jobseeker.setType("�˲�");
			jobseekerService.update(jobseeker);
			jobseeker.setPassword("");
						return "usuccess";
						}
	    //��Ϊ�޸ĵ���Ҫ����id����
		public String  Jupdatefind() {
			//��ȡsession
			HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			
			
		        int id=(int)session.getAttribute("adminid"); 
		        jobseekers=jobseekerService.findById(id);
		        jobseeker=(Jobseeker) jobseekers.get(0);
				return "ufsuccess";
						}
}
