package BTL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuanlySV {
	private ArrayList<Student> LStudent = new ArrayList<Student>();
	private Map<Integer,Integer> m =  new HashMap<Integer,Integer>();
	public void GetData(String path) throws IOException
	{
		IOFILE a =new IOFILE();
		LStudent = a.Stu_ReadFile(path);
		for(int i=0;i<LStudent.size();i++)
			m.put(LStudent.get(i).getID(),i);
	}
	///xoa theo ma thi sinh 
	public void Delete(int ID)
	{
		int z = m.get(ID);
		LStudent.remove(z);
		m.remove(ID);
		for(int i=0;i<LStudent.size();i++)
			m.put(LStudent.get(i).getID(),i);
	}
	public void Save(String Path)
	{
		IOFILE a = new IOFILE();
		a.Stu_WriteFile(LStudent,Path);
	}
	public void changeStudent(Student a,int index)
	{
		LStudent.set(index, a);
	}
	public int getIndex(Student i)
	{
		return LStudent.indexOf(i);
	}
	public Student getStudent(int i)
	{
		return LStudent.get(i);
	}
	public boolean check(int id)
	{
		if(m.containsKey(id))
			return true;
		return false;
	}
	public	void add(Student a)
	{
		LStudent.add(a);
		int i =  LStudent.indexOf(a);
		m.put(a.getID(),i);
	}
	public int size()
	{
		return LStudent.size();
	}
}
