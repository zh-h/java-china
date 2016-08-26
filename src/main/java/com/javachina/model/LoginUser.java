package com.javachina.model;

public class LoginUser {

	private Integer uid;
	private String user_name;
	private String nick_name;
	private String pass_word;
	private String jobs;
	private String avatar;
	private int role_id;
	private int status;
	private long topics;
	private long comments;
	private long notices;
	// 我收藏的帖子数
	private long my_topics;
	// 我收藏的节点数
	private long my_nodes;
	// 我关注的用户数
	private long following;
	
	public LoginUser() {
		// TODO Auto-generated constructor stub
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTopics() {
		return topics;
	}

	public void setTopics(long topics) {
		this.topics = topics;
	}

	public long getComments() {
		return comments;
	}

	public void setComments(long comments) {
		this.comments = comments;
	}

	public long getNotices() {
		return notices;
	}

	public void setNotices(long notices) {
		this.notices = notices;
	}
	public long getFollowing() {
		return following;
	}

	public void setFollowing(long following) {
		this.following = following;
	}

	public long getMy_topics() {
		return my_topics;
	}

	public void setMy_topics(long my_topics) {
		this.my_topics = my_topics;
	}

	public long getMy_nodes() {
		return my_nodes;
	}

	public void setMy_nodes(long my_nodes) {
		this.my_nodes = my_nodes;
	}
	
}
