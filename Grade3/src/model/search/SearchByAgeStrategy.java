package model.search;

import model.Member;

public class SearchByAgeStrategy implements ISearchStrategy{
	
	private int age;
	public SearchByAgeStrategy(int an_age)
	{
		age = an_age;
	}

	@Override
	public boolean memberFound(Member a_member) {
		return a_member.getMembersAge() == age;
	}
}
