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
import po.Employment;
import po.Hr;
import service.HrService;

public class HrAction {
	private Hr hr;
	private HrService hrService = null;
	
	//����ͼƬ��ر���
	private File pic;
	private String picContentType;
	private String picFileName;
	
	private  List hrs = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Hr getHr() {
		return hr;
	}
	public void setHr(Hr hr) {
		this.hr = hr;
	}
	public HrService getHrService() {
		return hrService;
	}
	public void setHrService(HrService hrService) {
		this.hrService = hrService;
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
	public List getHrs() {
		return hrs;
	}
	public void setHrs(List hrs) {
		this.hrs = hrs;
	}
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Hsave() {
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
		String username=hr.getUsername();
		hrs=hrService.findByUsername(username);
			if (hrs!=null && !hrs.isEmpty()) {
				session.setAttribute("registerfail", "�û����Ѵ���");
				return "registerfail";
			}
			else {
		//���浽��
		hr.setImg(picFileName);
		hr.setType("hr");
		hrService.save(hr);
		return "success";}
		}
	
	//�������б�����Ϣ
	 public String HfindAll() {
		hrs=hrService.findALL();
		
		if (hrs!=null && !hrs.isEmpty()) {
			
			return "findallHsuccess";
			}
		return "findallHfail";
	}
	//��½
		public String Hlogin() {
			//��ȡsession
			HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
			HttpSession session=request.getSession();
			
			String username=hr.getUsername();
			String password=hr.getPassword();
			hrs=hrService.findByUsername(username);
				
				if (hrs!=null && !hrs.isEmpty()) {
					 hr=(Hr) hrs.get(0);
					
					 if(hr.getPassword().equals(password))
					 {
						 session.setAttribute("logina", null);
						 session.setAttribute("admin", hr);
						 session.setAttribute("admintype", hr.getType());
						 session.setAttribute("adminname", hr.getName());
						 session.setAttribute("hrid", hr.getId());
						 session.setAttribute("adminid", hr.getId());
						 session.setAttribute("Hloginfail", "");
						 return "loginsuccess";
					 }
					 else
					 {
						 hr.setPassword("");
						 session.setAttribute("logina", 3);
						 session.setAttribute("Hloginfail", "�������");
						 return "loginfail";
					 }
						 
				} 
				else
				{
					
					session.setAttribute("logina", 3);
					session.setAttribute("Hloginfail", "�û���������");
					return "loginfail";
				}
		}
	 //��ѡ��ɾ��
	 public String Hdelete() {
			Hr hr1=new Hr();
			 //����Struts2�����������õ�jspҳ�洫��ids
			String[] id = getCheckid().split(",");
			int x;
			//String[] ����ת����Integer ����
			for(int i=0;i<id.length;i++) {
				//ע��ȥ���ո�
				x = Integer.parseInt(id[i].trim());
				System.out.println(x);
				hr1.setId(x);
				hrService.delete(hr1);
			}
			
			return "dsuccess";
	}
	//�޸ĺ���
	public String Hupdate() {
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
		hr.setImg(picFileName);
		hr.setType("hr");
			hrService.update(hr);
			session.setAttribute("Hloginfail", "�޸ĳɹ��������µ�½");
			session.setAttribute("logina", 3);
			hr.setPassword("");
					return "usuccess";
					}
    //��Ϊ�޸ĵ���Ҫ����id����
	public String  Hupdatefind() {
		//��ȡsession
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		
	        int id=(int)session.getAttribute("adminid");  
	        hrs=hrService.findById(id);
	        hr=(Hr) hrs.get(0);
			return "ufsuccess";
					}

}
