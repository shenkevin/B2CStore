package cn.mstar.store.entity;

import java.io.Serializable;

/**
 * 储存首页轮播图片的实体类
 * @author wenjundu 2015-7-8
 *
 */
public class BannerItemData implements Serializable{
	private int index;
	private String title;
	private String imageUrl;
	private String url;
	private int categoryId;
	private String keyword;
	private int proId;
	private int actionKey;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getActionKey() {
		return actionKey;
	}
	public void setActionKey(int actionKey) {
		this.actionKey = actionKey;
	}

}
