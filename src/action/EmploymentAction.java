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
import po.Employment;
import po.Greenbuild;
import service.EmploymentService;

public class EmploymentAction {
	private Employment employment;
	private EmploymentService employmentService = null;
	
	private  List employments = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Employment getEmployment() {
		return employment;
	}
	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	public EmploymentService getEmploymentService() {
		return employmentService;
	}
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	
	//������Ϣ�����get/set
	public List getEmployments() {
		return employments;
	}
	public void setEmployments(List employments) {
		this.employments = employments;
	}
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Esave() {
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
				int id=(int)session.getAttribute("hrid");
				employment.setHrid(id);
				employment.setUpdatedate(dateTime);
		employmentService.save(employment);
		return "success";
		}
	
	//�������б�����Ϣ
	 public String EfindAll() {
		employments=employmentService.findALL();
		
		if (employments!=null && !employments.isEmpty()) {
			
			return "findallEsuccess";
			}
		return "findallEfail";
	}
	 
	 //��ѡ��ɾ��
	 public String Edelete() {
		 Employment employment1=new Employment();
			 //����Struts2�����������õ�jspҳ�洫��ids
			String[] id = getCheckid().split(",");
			int x;
			//String[] ����ת����Integer ����
			for(int i=0;i<id.length;i++) {
				//ע��ȥ���ո�
				x = Integer.parseInt(id[i].trim());
				System.out.println(x);
				employment1.setId(x);
				employmentService.delete(employment1);
			}
			
			return "dsuccess";
	}
	//�޸ĺ���
	public String Eupdate() {
		employmentService.update(employment);
				return "usuccess";
				}
	//��Ϊ�޸ĵ���Ҫ����id����
    public String  Eupdatefind() {
					
					HttpServletRequest request = ServletActionContext.getRequest();
					  String str=request.getParameter("a");  
				        int id=Integer.parseInt(str);  
				        
				        employments=employmentService.findById(id);
				        employment=(Employment) employments.get(0);
						return "ufsuccess";
				}
  //ģ������
	 public String EfindbyEverything() {
		String str=employment.getSs();
	       employments=employmentService.findByEverything(str);
	    if(employments!=null)
	    {
	    	return "Efindsuccess";
	    }
	    return "Efindfail";
	}
  //ͨ��id����
	 public String Efindbyid() {
		 HttpServletRequest request = ServletActionContext.getRequest();
		  String str=request.getParameter("a"); 
		  System.out.print(str);
	        int id=Integer.parseInt(str);  
	       employments=employmentService.findById(id);
	       employment=(Employment) employments.get(0);
			return "ufsuccess";
	}
}
