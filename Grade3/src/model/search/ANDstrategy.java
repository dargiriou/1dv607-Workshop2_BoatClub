package model.search;

import model.Member;

public class ANDstrategy implements ISearchStrategy
{
	ISearchStrategy firstStrategy;
	ISearchStrategy secondStrategy;

	public ANDstrategy(ISearchStrategy a, ISearchStrategy b)
	{
		firstStrategy = a;
		secondStrategy = b;
	}


	@Override
	public boolean memberFound(Member a_member) 
	{
		return  firstStrategy.memberFound(a_member) && secondStrategy.memberFound(a_member);
	}
}
