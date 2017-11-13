package model;


import java.util.InputMismatchException;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement	
@XmlType(propOrder = {"m_boatType", "m_boatLength", "m_id"})
@XmlAccessorType(XmlAccessType.FIELD)
public final class Boat {

	@XmlElement(name = "type")
	private BoatType m_boatType;
	@XmlElement(name = "length")
	private double m_boatLength;
	@XmlElement(name = "boatID")
	private int m_id = 0;
	/**
	 * Constructors
	 * @param boatType
	 * @param boatLength
	 * @param id
	 */
	public Boat(BoatType boatType, double boatLength, int id)
	{
		m_id = id;
		m_boatType = boatType;
		m_boatLength = boatLength;
	}
	
	public Boat() 
	{

	}
	/***********************************************************************
	 *                         GETTERS                                     *
	 ***********************************************************************/
	public String getBoatId()
	{
		return String.valueOf(m_id);
	}

	public BoatType getType()
	{
		return m_boatType;
	}
	
	public double getLength()
	{
		return m_boatLength;
	}
	/***********************************************************************
	 *                         SETTERS                                     *
	 ***********************************************************************/
	public void setBoatType(BoatType boatType)
	{
		
		try {
			m_boatType = boatType;
			if(!validBoatType(boatType))
			{
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}
	public void setBoatID(String ID)
	{
		m_id = Integer.valueOf(ID);
	}
	
	public void setBoatLength(double boatLength)
	{
		try {
			m_boatLength = boatLength;
			if(!validBoatLength(boatLength))
			{
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}
	
	private boolean validBoatLength(double length)
	{
		if(length > 0)
		{
			return true;
		}
		return false;
	}
	
	private boolean validBoatType(BoatType a_type)
	{
		 for (BoatType type : BoatType.values()) 
		 {
		      if (type == a_type) 
		      {
		           return true;
		        }
		    }
		    return false;
	}
	

}