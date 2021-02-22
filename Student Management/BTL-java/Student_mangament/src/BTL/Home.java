package BTL;

public class Home {
	private String Home_name;
	private int Home_ID;
	Home()
	{
		this.Home_name = null;
	}
	Home(String a)
	{
		this.Home_name = a;
		Home_ID ++;
	}
	Home(int id,String name)
	{
		this.Home_name = name;
		this.Home_ID = id;
	}
	public String getHome_name() {
		return Home_name;
	}
	public void setHome_name(String home_name) {
		Home_name = home_name;
	}
	public int getHome_ID() {
		return Home_ID;
	}
	public  void setHome_ID(int a) {
		Home_ID = a;
	}
	public String toString() {
		return Home_name +" ; "+ Home_ID;
	}
}
