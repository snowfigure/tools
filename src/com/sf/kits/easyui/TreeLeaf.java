package com.sf.kits.easyui;


public class TreeLeaf {
	private String id;
	private String text;
	private String url;
	public TreeLeaf(String id, String text, String url) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
