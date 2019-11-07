/******************************************************************************
 * @File name   :      HttpEcho.java
 *
 * @Author      :      xiaobo.qin
 *
 * @Date        :      2016年6月2日 下午6:58:22
 *
 * @Copyright Notice: 
 * Copyright (c) 2016 Envision, Inc. All  Rights Reserved.
 * This software is published under the terms of the Envision Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 *****************************************************************************/
package org.fh.api.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class HttpEcho {

	public static <T> String success() {
		return success("");
	}

	public static <T> String success(T data) {
		return new Echo<T>(data).toString();
	}

	public static <T> String success(String name, T data) {
		Map<String, T> map = new HashMap<String, T>();
		map.put(name, data);
		return new Echo<Map<String, T>>(map).toString();
	}

	// 是否需要包装起来，也就是增加状态，若为false，则直接把对象转成json
	public static <T> String success(T data, boolean wrappen) {
		return wrappen ? success(data) : MyJsonUtils.toJson(data);
	}

	public static <T> String fail(T data, int error, String msg) {
		return new Echo<T>(data, error, msg).toString();
	}

}

enum Status {
	Success, Failed;
}

// 返回的响应包;
class Echo<T> {
	private T data;
	private int error = 0;

	private String msg = "";

	public Echo(T _data) {
		data = _data;
		error = 0;
	}

	public Echo(T _data, int _error) {
		data = _data;
		error = _error;
	}

	public Echo(T _data, int _error, String _msg) {
		data = _data;
		error = _error;
		msg = _msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
