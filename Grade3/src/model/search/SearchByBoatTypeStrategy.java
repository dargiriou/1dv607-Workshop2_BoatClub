package model.search;

import model.Boat;
import model.BoatType;
import model.Member;

public class SearchByBoatTypeStrategy implements ISearchStrategy 
{

	private BoatType boatType;
	public SearchByBoatTypeStrategy(String type)
	{
		boatType = BoatType.valueOf(type.toUpperCase());
	}

	@Override
	public boolean memberFound(Member a_member) 
	{
		for(Boat b : a_member.getBoatList())
		{
			if(b.getType() == boatType)
			{
				return true;
			}
		}
		return false;
	}
}
