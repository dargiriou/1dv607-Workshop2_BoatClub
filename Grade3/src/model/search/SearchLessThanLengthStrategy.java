package model.search;

import model.Boat;
import model.Member;

public class SearchLessThanLengthStrategy implements ISearchStrategy{

	private double m_length;
	public SearchLessThanLengthStrategy(double length)
	{
		m_length = length;
	}

	@Override
	public boolean memberFound(Member a_member)
	{
		for(Boat b: a_member.getBoatList())
		{
			if(b.getLength() < m_length)
			{
				return true;
			}
		}
		return false;
	}
}
