package com.ls.spt.basic.entity;

/**
 * 调用短信发送接口返回结果
 * @author HF-121093-01
 *
 */
public class SmsResult {
	public int 	Status;
	public String	Data;
	public String	Message;
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
}
