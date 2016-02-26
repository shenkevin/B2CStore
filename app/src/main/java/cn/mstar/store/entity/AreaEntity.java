package cn.mstar.store.entity;

import java.io.Serializable;

/**
 * 区域 属性
 * @author wenjudn
 *
 */
public class AreaEntity implements Serializable{

	private String id;//id
	private String parentId;
	private String name;
	public AreaEntity(String id,String parentId,String name){
		this.id=id;
		this.parentId=parentId;
		this.name=name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
