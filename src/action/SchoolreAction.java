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
import po.Schoolre;
import service.SchoolreService;

public class SchoolreAction {
	private Schoolre schoolre;
	private SchoolreService schoolreService = null;
	
	//����ͼƬ��ر���
	private File pic;
	private String picContentType;
	private String picFileName;
	
	private  List schoolres = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Schoolre getSchoolre() {
		return  schoolre;
	}
	public void setSchoolre(Schoolre schoolre) {
		this.schoolre = schoolre;
	}
	public SchoolreService getSchoolreService() {
		return schoolreService;
	}
	public void setSchoolreService(SchoolreService schoolreService) {
		this.schoolreService = schoolreService;
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
	public List getSchoolres() {
		return schoolres;
	}
	public void setSchoolres(List schoolres) {
		this.schoolres = schoolres;
	}
	
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}		
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Ssave() {
		 HttpServletRequest request = ServletActionContext.getRequest();
		 HttpSession session=request.getSession();
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
		
		//���浽��
        schoolre.setImg(picFileName);
        schoolre.setDate(dateTime);
    	session.setAttribute("cgxs", "����ɹ����鿴���е�У����Ϣ");
		session.setAttribute("ahref", "SfindallC");
		
        schoolreService.save(schoolre);
		return "success";
		}
	
	//�������б�����Ϣ
	 public String SfindAll() {
		 schoolres=schoolreService.findALL();
		
		if (schoolres!=null && !schoolres.isEmpty()) {
			
			return "findallSsuccess";
			}
		return "findallSfail";
	}
	//ͨ��id����
	 public String Sfindbyid() {
		 HttpServletRequest request = ServletActionContext.getRequest();
		  String str=request.getParameter("a"); 
		  System.out.print(str);
	        int id=Integer.parseInt(str);  
	       schoolres=schoolreService.findById(id);
	       schoolre=(Schoolre) schoolres.get(0);
			return "ufsuccess";
	}
	//ģ������
	 public String SfindbyEverything() {
		String str=schoolre.getSs();
	       schoolres=schoolreService.findByEverything(str);
	    if(schoolres!=null)
	    {
	    	return "Sfindsuccess";
	    }
	    return "Sfindfail";
	}
	 //��ѡ��ɾ��
	 public String Sdelete() {
		 HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session=request.getSession();
		  String str=request.getParameter("a"); 
		//  System.out.print(str);
	        int id=Integer.parseInt(str);  
		 Schoolre schoolre1=new Schoolre();
			
		 schoolre1.setId(id);
		 schoolreService.delete(schoolre1);
			session.setAttribute("cgxs", "ɾ���ɹ����鿴���е�У����Ϣ");
			session.setAttribute("ahref", "SfindallC");
			
			return "dsuccess";
	}
	//�޸ĺ���
		public String Supdate() {
			 HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session=request.getSession();
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
			
			//��Ƭ
			try {
				String realPath=ServletActionContext.getServletContext().getRealPath("/images");
				
				
				System.out.println(realPath);
				File file=new File(realPath,picFileName);
				FileUtils.copyFile(pic, file);
				}catch(IOException e) {
					ActionContext.getContext().put("message", "ͼƬ�ϴ�");
				}
			schoolre.setDate(dateTime);
			schoolre.setImg(picFileName);
			schoolreService.update(schoolre);
			session.setAttribute("cgxs", "�޸ĳɹ����鿴���е�У����Ϣ");
			session.setAttribute("ahref", "SfindallC");
			return "usuccess";
			}
	//��Ϊ�޸ĵ���Ҫ����id����
		public String  Supdatefind() {
					
					HttpServletRequest request = ServletActionContext.getRequest();
					  String str=request.getParameter("a");  
				        int id=Integer.parseInt(str);  
				        //System.out.println(id);
				        schoolres=schoolreService.findById(id);
				        schoolre=(Schoolre) schoolres.get(0);
						return "ufsuccess";
				}
}
