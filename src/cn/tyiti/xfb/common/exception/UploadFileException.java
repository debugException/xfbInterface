/*
 * Copyright 2014 [程控物流投资有限公司], Inc. All rights reserved.
 * Website: http://www.56vl.com
 */
package cn.tyiti.xfb.common.exception;

import java.io.Serializable;

/**
 * 
 * 文件上传失败异常
 * 
 * @version 1.0 2015年3月31日
 * @author Black
 */
public class UploadFileException extends RuntimeException implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 3041169995696432789L;
	
	public UploadFileException(){
		
	}
	
	public UploadFileException(Throwable cause){
		super(cause);
	}
	
	public UploadFileException(String message){
		super(message);
	}

}
