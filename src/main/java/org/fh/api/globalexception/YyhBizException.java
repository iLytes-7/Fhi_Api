package org.fh.api.globalexception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fh.api.dialect.ErrorCode;

@SuppressWarnings("serial")
public class YyhBizException extends Exception{
	private List<String> params=new ArrayList<>();
	
	private ErrorCode ec;
	
	public YyhBizException(ErrorCode ec){
		this.ec = ec;
	}
	
	public YyhBizException(ErrorCode ec, String param){
		this(ec,Arrays.asList(param));
	}
	
	public YyhBizException(ErrorCode ec, List<String> params){
		this.ec = ec;
		this.params = params;
	}
	
	public YyhBizException(ErrorCode ec, Exception e){
		
	}

	public ErrorCode getEc() {
		return ec;
	}

	public List<String> getParams() {
		return params;
	}
	
	public boolean hasParams(){
		return !params.isEmpty();
	}
	
}
