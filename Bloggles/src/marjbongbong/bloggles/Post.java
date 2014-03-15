package marjbongbong.bloggles;

public class Post {
	
	private int default_photo, post_photo;
	private String username, post_title, post_content, time_posted;
	
	public Post() {
	}
	
	public Post(int default_photo, String username, String post_title, String post_content, int post_photo, String time_posted)
	{
		this.default_photo = default_photo;
		this.username = username;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_photo = post_photo;
		this.time_posted = time_posted;
	}

	public void setDefaultPhoto(int default_photo)
	{
		this.default_photo = default_photo;
	}
	
	public int getDefaultPhoto()
	{
		return default_photo;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPostTitle(String post_title)
	{
		this.post_title = post_title;
	}
	
	public String getPostTitle()
	{
		return post_title;
	}
	
	public void setPostContent(String post_content)
	{
		this.post_content = post_content;
	}
	
	public String getPostContent()
	{
		return post_content;
	}
	
	public void setPostPhoto(int post_photo)
	{
		this.post_photo = post_photo;
	}
	
	public int getPostPhoto()
	{
		return post_photo;
	}
	
	public void setTimePosted(String time_posted)
	{
		this.time_posted = time_posted;
	}
	
	public String getTimePosted()
	{
		return time_posted;
	}
}