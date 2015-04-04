package com.sf.kits.easyui;

import java.util.List;

public class TreeNode {
	private String id;
	private String text;
	private List<Object> children;
	public TreeNode(String id, String text) {
		super();
		this.id = id;
		this.text = text;
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
	public List<Object> getChildren() {
		return children;
	}
	public void setChildren(List<Object> children) {
		this.children = children;
	}
}
