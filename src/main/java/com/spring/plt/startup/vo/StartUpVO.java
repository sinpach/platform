package com.spring.plt.startup.vo;

import org.springframework.stereotype.Component;

@Component("startUpVO")
public class StartUpVO {
	private String id;
	private String pwd;
	private String compName;
	private String compCeoName;
	private String compEmail;
	private String compBizNo;
	private String compBizType;
	private String compAddr;
	private String compDetailAddr;
	private String compTel;
	private String compStart;
	private String compDetail;
	private String compImg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompCeoName() {
		return compCeoName;
	}
	public void setCompCeoName(String compCeoName) {
		this.compCeoName = compCeoName;
	}
	public String getCompEmail() {
		return compEmail;
	}
	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}
	public String getCompBizNo() {
		return compBizNo;
	}
	public void setCompBizNo(String compBizNo) {
		this.compBizNo = compBizNo;
	}
	public String getCompBizType() {
		return compBizType;
	}
	public void setCompBizType(String compBizType) {
		this.compBizType = compBizType;
	}
	public String getCompAddr() {
		return compAddr;
	}
	public void setCompAddr(String compAddr) {
		this.compAddr = compAddr;
	}
	public String getCompTel() {
		return compTel;
	}
	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}
	public String getCompStart() {
		return compStart;
	}
	public void setCompStart(String compStart) {
		this.compStart = compStart;
	}
	public String getCompDetail() {
		return compDetail;
	}
	public void setCompDetail(String compDetail) {
		this.compDetail = compDetail;
	}
	public String getCompImg() {
		return compImg;
	}
	public void setCompImg(String compImg) {
		this.compImg = compImg;
	}
	
	public String toString() {
		String info = "";
		info += "\n"+id+"\n "
			        +pwd+"\n"
			        +compName+"\n"
			        +compCeoName+"\n"
			        +compEmail+"\n"
			        +compBizNo+"\n"
			        +compBizType+"\n"
			        +compAddr+"\n"
			        +compDetailAddr+"\n"
			        +compTel+"\n"
			        +compStart+"\n"
			        +compDetail+"\n"
			        +compImg;
					
		
		return info;
	}
	public String getCompDetailAddr() {
		return compDetailAddr;
	}
	public void setCompDetailAddr(String compDetailAddr) {
		this.compDetailAddr = compDetailAddr;
	}
	
}
