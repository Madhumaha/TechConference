package ApponitmentScheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PersonalScheduler {
	
	public boolean addApponitment()
	{
		//Appointment a1=new Appointment();
		Scanner s=new Scanner(System.in);
		Date d1=null;
		 Connection con=null;
		int n=0;
		System.out.println("Enter the name, Appointment date in mm/dd/yyyy format and enter the time");
		//a1.setName(s.nextLine());
		
		
			// d1=sd.parse(d);
			//a1.setDate(sd.format(d));
		
		
		//a1.setTime(s.next());
		//return a1;
		 
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test1");
			if(con!=null)
			{
		PreparedStatement ps;
		ps = con.prepareStatement("insert into appointment values(?,?,?)");
			ps.setString(1,s.nextLine());
			SimpleDateFormat sd=new SimpleDateFormat("mm/dd/yyyy");
			String d=s.next();
			ps.setString(2,sd.format(d));
			ps.setString(3,s.next());
			n=ps.executeUpdate();
			if(n==1)
				return true;
			}
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Appointment> allAppointmentsByUser(String name)
	{
		Connection con=null;
		Appointment a=new Appointment();
		ArrayList<Appointment> al=new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test1");
			if(con!=null)
			{
			PreparedStatement ps=con.prepareStatement("select * from appointment where name='"+name+"'");
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				a.setName(rs.getString("name"));
				a.setDate(rs.getString("appdate"));
				a.setTime(rs.getString("apptime"));
				al.add(a);
			}
			return al;
			
			}
		}
		catch(Exception e)
		{
			return null;
		}
		return null;
	}
	
public static void main(String[] a)
{
	
	PersonalScheduler p=new PersonalScheduler();
	System.out.println("Menu \n 1.Add Appointment \n 2.List Appointment by name \n 3.Exit");
	System.out.println("Enter choicce");
	int ch;
	Scanner s=new Scanner(System.in);
	ch=s.nextInt();
	switch(ch)
	{
	case 1:if(p.addApponitment())
		System.out.println("Apponitment added");
	else
		System.out.println("Something wrong");
	break;
	case 2:System.out.println("Enter name to get apponitments");
	Scanner s1=new Scanner(System.in);
	String name=s.nextLine();
		List<Appointment> al= p.allAppointmentsByUser(name);
		System.out.println("your appointments are");
		for(Appointment a2:al)
		{
			System.out.println(a2.getName());
			System.out.println(a2.getDate());
			System.out.println(a2.getTime());
		}
		break;
	case 3:
		System.out.println("invalid and you exiting");
		System.exit(0);
		break;
	default:System.out.println("invalid and you exiting");
		System.exit(0);
		break;
		
	}
}
}
