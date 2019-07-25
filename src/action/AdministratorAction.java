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

import po.Administrator;
import service.AdministratorService;

public class AdministratorAction {
	private Administrator administrator;
	private AdministratorService administratorService = null;
	
	//����ͼƬ��ر���
	private File pic;
	private String picContentType;
	private String picFileName;
	
	private  List administrators = new ArrayList();
	
	//��ѡ��
	private String checkid;
	 
	//get/set����
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	public AdministratorService getAdministratorService() {
		return administratorService;
	}
	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
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
	public List getAdministrators() {
		return administrators;
	}
	public void setAdministrators(List administrators) {
		this.administrators = administrators;
	}
	
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}	
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Asave() {
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
		
		String username=administrator.getUsername();
		administrators=administratorService.findByUsername(username);
			
			if (administrators!=null && !administrators.isEmpty()) {
				session.setAttribute("registerfail", "�û����Ѵ���");
				return "registerfail";
			}
		
		//���浽��
		administrator.setImg(picFileName);
		administrator.setType("����Ա");
		
		administratorService.save(administrator);
		return "success";
		}
	//��½
	public String Alogin() {
		//��ȡsession
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String username=administrator.getUsername();
		String password=administrator.getPassword();
		administrators=administratorService.findByUsername(username);
			
			if (administrators!=null && !administrators.isEmpty()) {
				 administrator=(Administrator) administrators.get(0);
				
				 if(administrator.getPassword().equals(password))
				 {
					 session.setAttribute("logina", null);
					 session.setAttribute("admin", administrator);
					 session.setAttribute("admintype", administrator.getType());
					 session.setAttribute("adminname", administrator.getUsername());
					 session.setAttribute("adminid", administrator.getId());
					 session.setAttribute("Aloginfail", "");
					 return "loginsuccess";
				 }
				 else
				 {
					 administrator.setPassword("");
					 session.setAttribute("logina", 1);
					 session.setAttribute("Aloginfail", "�������");
					 return "loginfail";
				 }
					 
			} 
			else
			{
				session.setAttribute("logina", 1);
				session.setAttribute("Aloginfail", "�û���������");
				return "loginfail";
			}
	}
	//�������б�����Ϣ
	 public String AfindAll() {
		 administrators=administratorService.findALL();
		
		if (administrators!=null && !administrators.isEmpty()) {
			
			return "findallAsuccess";
			}
		return "findallAfail";
	}
	 
	 //��ѡ��ɾ��
	 public String Adelete() {
		 Administrator administrator1=new Administrator();
			 //����Struts2�����������õ�jspҳ�洫��ids
			String[] id = getCheckid().split(",");
			int x;
			//String[] ����ת����Integer ����
			for(int i=0;i<id.length;i++) {
				//ע��ȥ���ո�
				x = Integer.parseInt(id[i].trim());
				
				administrator1.setId(x);
				administratorService.delete(administrator1);
			}
			
			return "dsuccess";
	}
	//�޸ĺ���
		public String Aupdate() {
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
			administrator.setType("����Ա");
			administrator.setImg(picFileName);
			session.setAttribute("Aloginfail", "�޸ĳɹ��������µ�½");
			session.setAttribute("logina", 1);
			administratorService.update(administrator);
			administrator.setPassword("");
			return "usuccess";
			}
	//��Ϊ�޸ĵ���Ҫ����id����
	public String  Aupdatefind() {
		//��ȡsession
				HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
				HttpSession session=request.getSession();
				
				
			        int id=(int)session.getAttribute("adminid");  
			        
			        administrators=administratorService.findById(id);
			        administrator=(Administrator) administrators.get(0);
					return "ufsuccess";
			}
}
