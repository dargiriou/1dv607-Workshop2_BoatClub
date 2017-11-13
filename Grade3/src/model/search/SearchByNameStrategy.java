package model.search;

import model.Member;

public class SearchByNameStrategy implements ISearchStrategy{

	private String name;
	public SearchByNameStrategy(String a_name)
	{
		name = a_name;
	}

	@Override
	public boolean memberFound(Member a_member) 
	{
		return a_member.getMembersFullName().toLowerCase().contains(name);
	}
}
