package model;

public class Authentication {

	private final String adminID = "admin";
	private final String adminPassword = "admin";
	private boolean loggedInAdmin = false;
	private boolean loggedInMember = false;
	
	public boolean AuthenticateMember(String ID, String password, Registry the_registry)
	{
		
		for(Member m: the_registry.getMemberList())
		{
			if(m.getMembersID().equals(ID) && m.getMembersPassword().equals(password))
			{
				return loggedInMember = true;
			}
		}
		return loggedInMember = false;
	}
	
	public boolean AuthenticateAdmin(String adminID, String adminPassword)
	{
		if(adminID.equals(this.adminID) && adminPassword.equals(this.adminPassword))
		{
			return loggedInAdmin = true;
		}
		return loggedInAdmin = false;
	}
	
	public boolean isLoggedinAdmin()
	{
		return loggedInAdmin;
	}
	public boolean isLoggedinMember()
	{
		return loggedInMember;
	}
}
