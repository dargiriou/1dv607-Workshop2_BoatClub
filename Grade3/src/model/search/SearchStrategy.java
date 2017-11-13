package model.search;

public class SearchStrategy {

	public ISearchStrategy getSearchByNameStrategy(String a_name)
	{
		return new SearchByNameStrategy(a_name);		
	}
	
	public ISearchStrategy getSearchByAge(int age)
	{
		return new SearchByAgeStrategy(age);		
	}
	
	public ISearchStrategy getSearchGreaterThanAge(int age)
	{
		return new SearchGreaterThanAgeStrategy(age);		
	}
	
	public ISearchStrategy getSearchLessThanAge(int age)
	{
		return new SearchLessThanAgeStrategy(age);		
	}
	
	public ISearchStrategy getSearchByType(String BoatType)
	{
		return new SearchByBoatTypeStrategy(BoatType);		
	}
	
	public ISearchStrategy getSearchByLengthGreaterThan(double length)
	{
		return new SearchGreaterThanLengthStrategy(length);		
	}
	
	public ISearchStrategy getSearchByLengthLessThan(double length)
	{
		return new SearchLessThanLengthStrategy(length);		
	}
	
	public ISearchStrategy getSearchByID(String ID)
	{
		return new SearchByIDStrategy(ID);		
	}
	
	public ISearchStrategy getSearchByMonth(int month)
	{
		return new SearchByMonthStrategy(month);		
	}
	public ISearchStrategy getSearchByLengthEqualto(double length)
	{
		return new SearchByLengthStrategy(length);
	}
	public ISearchStrategy getANDstrategy(ISearchStrategy a, ISearchStrategy b)
	{
		return new ANDstrategy(a, b);
	}
	public ISearchStrategy getORstrategy(ISearchStrategy a, ISearchStrategy b)
	{
		return new ORstrategy(a, b);
	}
	
}
