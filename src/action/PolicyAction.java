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
import po.Policy;
import service.PolicyService;

public class PolicyAction {
	private Policy policy;
	private PolicyService policyService = null;
	
	private  List policys = new ArrayList();
	
	private String checkid;//��ѡ��
	 
	//get/set����
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	
	//������Ϣ�����get/set
	public List getPolicys() {
		return policys;
	}
	public void setPolicys(List policys) {
		this.policys = policys;
	}
	
	//��ѡ������get/set
	public String getCheckid() {
		return checkid;
	}	
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	
	//����
	public String Psave() {
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
		
		//���浽��
		policy.setDate(dateTime);
		session.setAttribute("cgxs", "����ɹ����鿴���е�������Ϣ");
		session.setAttribute("ahref", "PfindallC");
	
		policyService.save(policy);
		return "success";
		}
	
	//�������б�����Ϣ
	 public String PfindAll() {
		 List policys1=new ArrayList();
		 policys1=policyService.findALL();
		
		if (policys1!=null && !policys1.isEmpty()) {
			for(int i=0;i<policys1.size();i++)
			{
				policy=(Policy)policys1.get(i);
				String ee=policy.getEssay();
				if(ee.length()>101)
				ee=ee.substring(0, 100);
				policy.setEssay(ee);
				policys.add(policy);
			}
			return "findallPsuccess";
			}
		return "findallPfail";
	}
	//ͨ��id����
	 public String Pfindbyid() {
		 HttpServletRequest request = ServletActionContext.getRequest();
		  String str=request.getParameter("a"); 
		 // System.out.print(str);
	        int id=Integer.parseInt(str);  
	       policys=policyService.findById(id);
	       policy=(Policy) policys.get(0);
			return "ufsuccess";
	}
	 
	 public String Pfindthree() {
		 List policys1 = new ArrayList();
		 policys1=policyService.findALL();
		if (policys1!=null && !policys1.isEmpty()) {
			
			int x=0;
			if(policys1.size()>5)
			{
				x=5;
				for(int i=0;i<x;i++)
				{	
					policy=(Policy)policys1.get(i);
					policys.add(policy);
				}
			}
			else
				policys=policys1;
			
			return "findallCsuccess";
			}
			
		return "findallCfail";
	}
	//ģ������
	 public String PfindbyEverything() {
		String str=policy.getSs();
	       policys=policyService.findByEverything(str);
	    if(policys!=null)
	    {
	    	return "Pfindsuccess";
	    }
	    return "Pfindfail";
	}
	 //��ѡ��ɾ��
	 public String Pdelete() {
		 HttpServletRequest request = ServletActionContext.getRequest();
		 HttpSession session=request.getSession();
		  String str=request.getParameter("a"); 
		  int id=Integer.parseInt(str);  
		 Policy policy1=new Policy();
			
				policy1.setId(id);
				policyService.delete(policy1);
				session.setAttribute("cgxs", "ɾ���ɹ����鿴���е�������Ϣ");
				session.setAttribute("ahref", "PfindallC");
			
			return "dsuccess";
	}
	//�޸ĺ���
		public String Pupdate() {
			policyService.update(policy);
			return "usuccess";
			}
}
