package marjbongbong.bloggles;

public class User {
	
	private int image;
	private String username, name;
	
	public User() {		
	}
	
	public User(int image, String username, String name)
	{
		this.image = image;
		this.username = username;
		this.name = name;
	}

	public void setImage(int image)
	{
		this.image = image;
	}
	
	public int getImage()
	{
		return image;
	}

	public void setUsernmae(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}