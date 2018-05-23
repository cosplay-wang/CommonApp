package com.cosplay.wang.commonapp.bean;

import java.util.List;

/**
 * Author:zhiwei.wang on 2018/5/21.
 * Email :cosplay.wang@gmail.com|760560322@qq.com
 * Description:
 */

public class NewsItem {


	/**
	 * type : doc
	 * thumbnail : http://d.ifengimg.com/w230_h152_q100/y1.ifengimg.com/ifengiclient/ipic/20160223/157_157_0eaddb36f545715822fe1972458daa45_w640_h533.jpg
	 * title : 证明霓虹人都是猫奴的10件事
	 * showType : 0
	 * updateTime : 2018/01/03 14:27:07
	 * id : http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054761309&channelKey=&channelid=SYLB10&category=
	 * documentId : cmpp_034470054761309
	 * staticId : cmpp_034470054761309
	 * style : {"backreason":["不感兴趣","旧闻、看过了","内容质量差"],"view":"titleimg"}
	 * commentsUrl : http://news.ifeng.com/a/20180103/54761309_0.shtml
	 * comments : 2
	 * commentsall : 6
	 * link : {"type":"doc","url":"http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054761309&channelKey=&channelid=SYLB10&category=","weburl":"http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=cmpp_034470054761309"}
	 * reftype : ai|||
	 * simId : clusterId_27106519
	 * intro : 证明霓虹人都是猫奴的10件事
	 */

	public String type;
	public String thumbnail;
	public String title;
	public String showType;
	public String updateTime;
	public String id;
	public String documentId;
	public String staticId;
	public StyleBean style;
	public String commentsUrl;
	public String comments;
	public String commentsall;
	public LinkBean link;
	public String reftype;
	public String simId;
	public String intro;

	public static class StyleBean {
		/**
		 * backreason : ["不感兴趣","旧闻、看过了","内容质量差"]
		 * view : titleimg
		 */

		public String view;
		public List<String> backreason;
	}

	public static class LinkBean {
		/**
		 * type : doc
		 * url : http://api.3g.ifeng.com/ipadtestdoc?aid=cmpp_034470054761309&channelKey=&channelid=SYLB10&category=
		 * weburl : http://m.ifeng.com/sharenews.f?ch=qd_sdk_dl1&aid=cmpp_034470054761309
		 */

		public String type;
		public String url;
		public String weburl;
	}
}
