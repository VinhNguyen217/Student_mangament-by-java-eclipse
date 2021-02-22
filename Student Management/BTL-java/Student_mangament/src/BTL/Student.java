package BTL;

import java.io.IOException;

public class Student {
	private int ID;				// ma thi sinh
	private String name;		//ho va ten
	private Home H_ID;			//que quan	
	private	String Dbirth; 		//ngay thang nam sinh DD-MM-YYYY
	private Boolean Gender;
	private float Math;
	private float Physics;
	private float Chemistry;
	public static QL_Home qlhome =  new QL_Home();
	Student() throws IOException
	{
		ID = 0;
		Math = 0;
		Physics = 0;
		Chemistry = 0;
		H_ID = new Home();
		Gender = null;
		qlhome.GetData("quequan.txt");
	}
	public int getID() {	
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHID()
	{
		return H_ID.getHome_ID();
	}
	public String getHname() {
		return H_ID.getHome_name();
	}
	public void SetHOME(Home c)
	{
		this.H_ID = c;
	}
	public void setHname(int  id) throws IOException {		//CHU Y 
		H_ID.setHome_ID(id);
		String name = qlhome.Checkid(id);
		H_ID.setHome_name(name);
	}
	public String getDbirth() {
		return Dbirth;
	}
	public void setDbirth(String dbirth) {
		Dbirth = dbirth;
	}
	public String getGender() {
		if(Gender == true)
			return "Nam";
		else return "Nu";
	}
	public void setGender(Boolean Gender) {
		this.Gender = Gender;
	}
	public float getMath() {
		return Math;
	}
	public void setMath(float math) {
		checkvalidMark(math);
		Math = math;
	}
	public float getPhysics() {
		
		return Physics;
	}
	public void setPhysics(float physics) {
		checkvalidMark(physics);
		Physics = physics;
	}
	public float getChemistry() {
		return Chemistry;
	}
	public void setChemistry(float chemistry) {
		checkvalidMark(chemistry);
		Chemistry = chemistry;
	}
	@Override
	public String toString() {
		return ID +" ; " + name+" ; " + H_ID.getHome_name() + " ; "+Dbirth+" ; "+ getGender() +" ; "+Math+"; "+Physics+" ; "+Chemistry;
	}
	public String Save()
	{
		return ID +" ; " + name+" ; " + H_ID.getHome_ID() + " ; "+Dbirth+" ; "+ Gender +" ; "+Math+"; "+Physics+" ; "+Chemistry;
	}
	public boolean checkvalidMark(float a)
	{
		if(a >10||a<0)
			return false;
		else return true;
	}
	public boolean checkque(String b)
	{
		b.toUpperCase();
		if(qlhome.CheckQue(b) == 1)
			return true;
		return false;
	}
	public boolean checkbirth(String b)
	{
		 return b.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$");
	}
	
}