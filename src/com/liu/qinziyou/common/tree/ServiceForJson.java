package com.liu.qinziyou.common.tree;

public class ServiceForJson {
	// key(增值服务名)
	private String text;
	// value(增值服务参数)
	private String value;
	//scode(增值服务代码)
	private String scode;
	

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
