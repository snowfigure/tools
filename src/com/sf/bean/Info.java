package com.sf.bean;

public class Info {
	private String title;
	private String topurl;
	private String suburl;
	private String keyid;
	public Info(String title, String topurl ,String suburl,String keyid) {
		super();
		this.title = title;
		this.topurl = topurl;
		this.suburl = suburl;
		this.setKeyid( keyid );
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTopurl() {
		return topurl;
	}
	public void setTopurl(String topurl) {
		this.topurl = topurl;
	}
    public String getSuburl()
    {
        return suburl;
    }
    public void setSuburl( String suburl )
    {
        this.suburl = suburl;
    }
    public String getKeyid()
    {
        return keyid;
    }
    public void setKeyid( String keyid )
    {
        this.keyid = keyid;
    }
}
