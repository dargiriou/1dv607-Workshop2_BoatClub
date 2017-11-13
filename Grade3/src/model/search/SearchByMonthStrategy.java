package model.search;

import model.Member;

public class SearchByMonthStrategy implements ISearchStrategy
{
	private int m_month;
	public SearchByMonthStrategy(int a_month)
	{
		m_month = a_month;
	}

	@Override
	public boolean memberFound(Member a_member)
	{
		return a_member.getMembersMonthOfBirth() == m_month;
	}

}
