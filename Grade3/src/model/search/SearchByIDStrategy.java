package model.search;

import model.Member;

public class SearchByIDStrategy implements ISearchStrategy
{
	private String m_id;
	public SearchByIDStrategy(String ID)
	{
		m_id = ID;
	}

	@Override
	public boolean memberFound(Member a_member)
	{
		return a_member.getMembersID().equals(m_id);
	}

}
