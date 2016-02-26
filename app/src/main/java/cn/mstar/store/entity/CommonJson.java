package cn.mstar.store.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class CommonJson<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private T data;
	private String response;
	private String error;
	private String message;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static CommonJson fromJson(String json, Class clazz) {
		Gson gson = new Gson();
		Type objectType = type(CommonJson.class, clazz);
		return gson.fromJson(json, objectType);
	}

	public String toJson(Class<T> clazz) {
		Gson gson = new Gson();
		Type objectType = type(CommonJson.class, clazz);
		return gson.toJson(this, objectType);
	}

	static ParameterizedType type(final Class raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}
}
