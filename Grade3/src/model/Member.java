package model;

import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Member")
@XmlType(propOrder = {"m_id", "m_fullName", "m_personalNumber", "boatList", "m_password"})
@XmlAccessorType(XmlAccessType.FIELD)

public class Member {

	@XmlElement(name = "m_id")
	private String m_id;
	@XmlElement(name = "fullname")
	private String m_fullName;
	@XmlElement(name = "personalNumber")
	private String m_personalNumber;
	@XmlElement(name = "BoatList")
	private ArrayList<Boat> boatList;
	@XmlElement(name = "Password")
	private String m_password;
	/**
	 * Constructors
	 * @param id
	 * @param name
	 * @param personalNumber
	 */
	public Member(String id, String name, String personalNumber, String password) 
	{
		m_id = id;
		m_fullName = name;
		m_personalNumber = personalNumber;
		m_password = password;
		boatList = new ArrayList<Boat>();
	}
	public Member() 
	{
		// TODO Auto-generated constructor stub
	}
	
	/***********************************************************************
	 *                         SETTERS                                     *
	 ***********************************************************************/
	public void setMembersPassword(String password)
	{
		m_password = password;
	}
	public void setMembersName(String name)
	{
		try {
			m_fullName = name;
			if(!validateName(name))
			{
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}
	
	public String setMembersID(String id)
	{
		return m_id = id;
	}
	
	public void setMembersPersonalNumber(String personalNumber)
	{
		try {
			m_personalNumber = personalNumber;
			if(!validPersonalNumber(personalNumber))
			{
				throw new InputMismatchException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setBoatList(ArrayList<Boat> boatList) 
	{
		this.boatList = boatList;
	}

	
	/***********************************************************************
	 *                         GETTERS                                     *
	 ***********************************************************************/
	public String getMembersPassword()
	{
		return m_password;
	}
	
	public String getMembersPersonalNumber() 
	{
		return m_personalNumber;
	}

	public String getMembersID() 
	{
		return m_id;
	}
	
	public String getMembersFullName() 
	{
		return m_fullName;
	}
	
	public int getNumberOfBoats() 
	{
		return boatList.size();
	}

	public Iterable<Boat>getBoatList() 
	{
		return boatList;
	}
	
	public int getMembersAge()
	{
		String yearOfBirth = m_personalNumber.substring(0, Math.min(m_personalNumber.length(), 4));
		int date = Integer.parseInt(yearOfBirth);
		return 2017 - date;
	}
	
	public int getMembersMonthOfBirth()
	{
		String monthOfBirth = m_personalNumber.substring(4, Math.min(m_personalNumber.length(), 6));
		int month = Integer.parseInt(monthOfBirth);
		return month;
	}
	
	public boolean emptyBoatList()
	{
		if(boatList.isEmpty())
			return true;
		return false;
	}
	
	public int incrementBoatListSize()
	{ 
		return boatList.size() + 1;
	}
	
	/**
	 * Add boat to the list
	 * @param type
	 * @param length
	 * @param id
	 */
	public void addBoat(BoatType type, Double length, int id)
	{
		boatList.add(new Boat(type, length, id));
	}
	/**
	 * Removes a boat from the list
	 * @param boat
	 */
	public void removeBoat(Boat boat)
	{
		boatList.remove(boat);
	}
	/**
	 * Update boat information
	 * @param boat
	 * @param boatType
	 * @param boatLength
	 */
	public void updateBoatInfo(Boat boat, BoatType boatType, double boatLength)
	{
		boat.setBoatType(boatType);
		boat.setBoatLength(boatLength);
	}
	
	private boolean validateName(String name)
	{
		if(name.matches("^[\\p{L} .'-]+$"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validPersonalNumber(String pNum)
	{
		if(pNum.matches("[0-9]+"))
		{
			return true;
		}
		return false;
	}
}
