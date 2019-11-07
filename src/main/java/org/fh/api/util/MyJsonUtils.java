/******************************************************************************
 * @File name   :      MyJsonUtils.java
 *
 * @Author      :      xiaobo.qin
 *
 * @Date        :      2016年6月2日 下午6:59:43
 *
 * @Copyright Notice: 
 * Copyright (c) 2016 Envision, Inc. All  Rights Reserved.
 * This software is published under the terms of the Envision Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 *
 *****************************************************************************/
package org.fh.api.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MyJsonUtils {
	// 对象转为JSON
	public static <T> String toJson(T t) {
		return (new Gson()).toJson(t); 
	}

	public static <T> List<T> jsonToList(String json) {
		return new Gson().fromJson(json, new TypeToken<List<T>>() {
		}.getType());
	}

	// String 转为对象
	public static <T> T fromJson(String json, Class<T> cls) {
		return (new Gson()).fromJson(json, cls);
	}
}
