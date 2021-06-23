package com.asf.desafioCrud.resources.exceptions;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long timeStamp;
	private Integer status;
	private String msg;

	
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/*
	 * public Long getTimeStamp() { return timeStamp; }
	 */
	
	public String getTimeStamp() {
		LocalDateTime d = millsToLocalDateTime(this.timeStamp);
		String time =  d.toString() ;
		return time;
	}
	
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	  public static LocalDateTime millsToLocalDateTime(long millis) {
	      Instant instant = Instant.ofEpochMilli(millis);
	      LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	      return date;
	  }
	
}
