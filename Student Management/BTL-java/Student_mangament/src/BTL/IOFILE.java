package BTL;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
public class IOFILE {
	private  String regex = "(?<StuID>^\\d*)"	//ID student
			+ 	"\\s*[,;]\\s*"				//dau phay
			+ 	"(?<StuName>\\w+\\s*\\w*\\s*\\w*\\s*\\w*\\s*\\w*)"	//Student Name
			+ 	"\\s*[,;]\\s*"
			+ 	"(?<StuHome>[a-zA-z0-9]*)"			//Student Home
			+ 	"\\s*[,;]\\s*"
			+ 	"(?<StuBirth>[0-3][0-9][/](((0)[0-9])|((1)[0-2]))[/]\\d{4})"	//Student Date Of birth
			+ 	"\\s*[,;]\\s*"
			+ 	"(?<StuGender>true|false)"			//Student Gender 
			+	"\\s*[,;]\\s*" 
			+	"(?<Math>\\d[.]*\\d*)" 				//Math Mark
			+	"\\s*[,;]\\s*" 
			+	"(?<Physics>\\d[.]*\\d*)"			//Physics Mark
			+	"\\s*[,;]\\s*" 
			+	"(?<Chemistry>\\d[.]*\\d*)";			//Chemical Mark
	private String regex_Home = "(?<Homename>\\w+\\s*\\w*)\\s*[;]\\s*(?<HomeID>\\d+)";
//	private String path_out_Home;
	//Ham Nhap du lieu Student tu File tra ve 1 Array List
	public ArrayList<Student> Stu_ReadFile(String path) throws IOException	
	{
		ArrayList<Student> list = new ArrayList<Student>();
		try (Scanner rfile = new Scanner(new File(path))) {
			while(rfile.hasNext())
			{
				Student b = new Student();
				String temp = rfile.nextLine();
				Pattern patern = Pattern.compile(regex);
				Matcher x = patern.matcher(temp);	
				if(x.matches())
				{
					b.setID(Integer.parseInt(x.group("StuID")));
					b.setName(x.group("StuName"));	
					b.setHname(Integer.parseInt(x.group("StuHome")));
					b.setDbirth(x.group("StuBirth"));
					b.setGender(Boolean.parseBoolean(x.group("StuGender")));
					b.setMath(Float.parseFloat(x.group("Math")));
					b.setPhysics(Float.parseFloat(x.group("Physics")));	
					b.setChemistry(Float.parseFloat(x.group("Chemistry")));
					list.add(b);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error Read loi  :" + e.toString());
		}
		return list;	

	}
	
	//Ham Ghi du lieu Student ra File
	public void Stu_WriteFile(ArrayList<Student> lStudent,String path)
	{
		try(PrintWriter pw = new PrintWriter(new File(path)))
		{
			for(Student x:lStudent)
			{
				pw.write(x.Save() +  "\n" );
			}
		}
		catch(Exception e)
		{
			System.out.println("Write loi! ");
		}
	}
	
	//Ham Nhap du lieu Tinh thanh truyen vao duong dan va tra ve arraylist Home
	public ArrayList<Home>	Home_ReadFile(String path) throws IOException
	{
		ArrayList<Home> list = new ArrayList<Home>();
		int i=0;
		try (Scanner rfile = new Scanner(new File(path))) {
			while(rfile.hasNext())
			{
				Home b = new Home();
				i++;
				String temp = rfile.nextLine();
				Pattern patern = Pattern.compile(regex_Home);
				Matcher x = patern.matcher(temp);
				if(!x.find())	System.out.println("Khong doc duoc mot so tinh thanh vi tri " + i );
				if(x.matches())
				{
					b.setHome_name(x.group("Homename").toUpperCase());
					b.setHome_ID(Integer.parseInt(x.group("HomeID")));	//cai nay static nen truy cap luon tu class
					list.add(b);			
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error Read loi  :" + e.toString());
		}
		return list;
	}
	//Ham xuat du lieu tinh Thanh truyen vao arraylist la dc
	public void Home_WriteFile(ArrayList<Home> a,String path_out_Home)
	{
		try(PrintWriter pw = new PrintWriter(new File(path_out_Home)))
		{
			for(Home x:a)
				pw.println(x.toString().toUpperCase());		
		}
		catch(Exception e)
		{
			System.out.println("Write loi! ");
		}
	}
}
