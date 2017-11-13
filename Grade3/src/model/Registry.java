package model;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.search.ISearchStrategy;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Registry")
public class Registry 
{
	String m_id;
	@XmlElement(name = "memberList")
	private ArrayList<Member> memberList = new ArrayList<Member>();
	private ArrayList<String> IDList = new ArrayList<String>();

	
	public Registry()
	{

	}
	/**
	 * Adds a member to the member list and creates a random ID 
	 * @param name
	 * @param personalNumber
	 * @return
	 */
	public Member registerMember(String name, String personalNumber, String password)
	{
		
		String id = createID();
		while(!isDuplicateID(id))
		{
			id = createID();
		}
		Member a_member = new Member(id, name, personalNumber, password);
		this.memberList.add(a_member);
		return a_member;
	}
	/**
	 * Returns a specific member according to the ID string given
	 * @param id
	 * @return
	 */
	public Member lookForSpecificMember(String id)
	{

		Member a_member = new Member();
		try {
		a_member =  memberList.stream().filter(o -> o.getMembersID().equals(id)).findFirst().get();
		
		} 
		catch (Exception e) 
		{
			return null;
		}
		return a_member;
	}
	/**
	 * Removes a member from the member list
	 * @param a_member
	 */
	public void deleteMember(Member a_member)
	{
		this.memberList.remove(a_member);
	}
	/**
	 * Returns the member list
	 * @return
	 */
	public Iterable<Member> getMemberList()
	{
		return memberList;
	}
	
	public boolean emptyMemberList()
	{
		if(memberList.isEmpty())
			return true;
		return false;
	}
	
	public int memberListSize()
	{
		return memberList.size();
	}
	
	private Iterable<String> getIDList()
	{
		return IDList;
	}
	
	
	private String createID()
	{
		String randomId = UUID.randomUUID().toString(); //create a universally unique identifier of 128 bit value
		m_id = randomId.substring(0, Math.min(randomId.length(), 8)); // put the first 8 characters to the string "id"	
		IDList.add(m_id);
		return m_id;
	}
	
	private boolean isDuplicateID(String an_id)
	{
		for(String id : getIDList())
		{
			if(id.equals(an_id)){
				return true;
			}
		}
		return false;
		
	}
	
	public ArrayList<Member> searchforMembers(ISearchStrategy a_search)
	{
		ArrayList<Member> tempList = new ArrayList<Member>();
		
		for (Member m : this.memberList)
		{
			if (a_search.memberFound(m))
			{
	    		tempList.add(m);
	    	}
		}
		return tempList;
	}
}
