package com.split.manager.vo;
import java.io.Serializable;
import java.util.ArrayList;


public class RestResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Float version=1.0F;
	private Integer code;
	private String messageid;
	private String message;
	private Object data;
	private Object metadata;
	private Object error;
	
	public RestResponse() {}
	
	public RestResponse(Integer code,String messageId,String message) {
		this.code = code;
		this.message = message;
		this.messageid = messageId;
		this.error = new ArrayList<EmptyObject>(0);
		this.metadata = new EmptyObject();
	}
	
	public Float getVersion() {
		return version;
	}

	public void setVersion(Float version) {
		this.version = version;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}
}