package cn.mstar.store.entity;

import java.io.Serializable;
import java.util.Arrays;

import cn.mstar.store.entity.OrderDetailsEntity;

/**
 * 产品的属性---图片 名称 价格
 * @author duwenjun 2015-7-13
 *
 *
 */
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	//产品id
	private int proId;
	//类别id
	private String categoryId;
	private String sales_way;
	//产品图片地址
	private String ImageUrl;
	//名称
	private String name;
	//价格
	private Double price;
	private int proSpecialID;

	private String descURL;
	private String kindName;
	private String brandName;
	private String area;
	private boolean isHaveProSpecificationPrice;
	private String proNum;//货号
	private String weight;
	private String hit;

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	private String attributes;
	private String[] pics;//展示图

	private int stock;

	private String className;
	// is favorite
	private boolean isHaveProFavorite;
	private String parentClassName;

	public Product(OrderDetailsEntity.OrderItems instance) {
		this.price = instance.price;
		this.name = instance.title;
		this.proId = instance.proId;
		this.proSpecialID = instance.proSpecialId;
		this.ImageUrl = instance.pic;
	}

	public Product() {
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Product{" +
				"proId=" + proId +
				", categoryId='" + categoryId + '\'' +
				", sales_way='" + sales_way + '\'' +
				", ImageUrl='" + ImageUrl + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", proSpecialID=" + proSpecialID +
	//			", descURL='" + descURL + '\'' +
				", kindName='" + kindName + '\'' +
				", brandName='" + brandName + '\'' +
				", area='" + area + '\'' +
				", isHaveProSpecificationPrice=" + isHaveProSpecificationPrice +
				", proNum='" + proNum + '\'' +
				", weight='" + weight + '\'' +
				", hit='" + hit + '\'' +
				", pics=" + Arrays.toString(pics) +
				", stock=" + stock +
				", className='" + className + '\'' +
				", isHaveProFavorite=" + isHaveProFavorite +
				", parentClassName='" + parentClassName + '\'' +
				'}';
	}

	public boolean isHaveProFavorite() {
		return isHaveProFavorite;
	}
	public void setHaveProFavorite(boolean isHaveProFavorite) {
		this.isHaveProFavorite = isHaveProFavorite;
	}
	public String getDescURL() {
		return descURL;
	}
	public void setDescURL(String descURL) {
		this.descURL = descURL;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public boolean isHaveProSpecificationPrice() {
		return isHaveProSpecificationPrice;
	}
	public void setHaveProSpecificationPrice(boolean isHaveProSpecificationPrice) {
		this.isHaveProSpecificationPrice = isHaveProSpecificationPrice;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String[] getPics() {
		return pics;
	}
	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public int getProSpecialID() {
		return proSpecialID;
	}
	public void setProSpecialID(int proSpecialID) {
		this.proSpecialID = proSpecialID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSales_way() {
		return sales_way;
	}
	public void setSales_way(String sales_way) {
		this.sales_way = sales_way;
	}

	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
