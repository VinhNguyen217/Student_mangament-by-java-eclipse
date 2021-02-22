package BTL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QL_Home {
	private ArrayList<Home> list = new ArrayList<Home>();
	private Map<Integer,String> MyMap = new HashMap<Integer,String>();
	public void GetData(String path) throws IOException
	{
		IOFILE a = new IOFILE();
		list = a.Home_ReadFile(path);
		for(Home v:list)
			MyMap.put(v.getHome_ID(),v.getHome_name().toUpperCase());
	}
	public String Checkid(int id)
	{
		if(MyMap.containsKey(id)) {
			return MyMap.get(id);	
		}
		return "Not Found!";
	}
	public int CheckQue(String value)
	{
		for(int key:MyMap.keySet())
		{
			if(MyMap.get(key).equals(value))
				return 1;
		}
		return 0;
	}
	public void add(Home a)
	{
		if(this.CheckQue(a.getHome_name().toUpperCase()) == 0)
		{
			IOFILE c = new IOFILE();
			int i =  list.get(list.size()-1).getHome_ID();
			i++;
			a.setHome_ID(i);
			list.add(a);
			c.Home_WriteFile(list, "quequan.txt");
			MyMap.put(a.getHome_ID(), a.getHome_name().toUpperCase());
		}
	}
	public int getID(String a)
	{
		for(int key:MyMap.keySet())
			if(MyMap.get(key).equals(a))
				return key;
		return 1;
	}
}
