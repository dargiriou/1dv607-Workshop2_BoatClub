package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Member;
import model.Registry;
import model.search.ISearchStrategy;
import model.search.SearchStrategy;
import view.IView;

public class SearchController {
	
	SearchStrategy ss;
	BoatClub bc;
	ISearchStrategy searchStratInterface;
	MemberController mc;
	ArrayList<Member> member;
	
	
	
	String name;
	String boatType;
	String ID;
	int month;
	int age;
	double boatLength;

	public SearchController()
	{
		ss = new SearchStrategy();
		searchStratInterface = null;
	}

	public void search(IView a_view,Registry the_registry) throws IOException
	{
		a_view.displayInitialSearchMenu();
		switch(a_view.readUserInput())
		{
			case"1":
			{
				ArrayList<Member> member = the_registry.searchforMembers(simpleSearch(a_view, the_registry));
				a_view.displaySearchResults(member);
				break;
			}
			/*case"2":
			{
				advancedSearch(a_view, the_registry);
				break;
			}*/
			case"2":
			{
				bc = new BoatClub();
				bc.boatClubStart(the_registry, a_view);
			}
		}
	}
	
	public ISearchStrategy simpleSearch(IView a_view,Registry the_registry)
	{
		a_view.displaySearchInstructions();
		switch(a_view.readUserInput())
		{
			case"1"://name
			{
				a_view.searchName();
				name = a_view.readUserInput();
				while(!correctName(name))
				{
					//make incorrect name method in the view
					name = a_view.readUserInput();
				}
				searchStratInterface = ss.getSearchByNameStrategy(name);
				break;
			}
			case"2"://age
			{
				a_view.SearchAttributeInstructions();
				switch(a_view.readUserInput())
				{
					case"1"://grater than
					{
						a_view.searchAge();
						age = a_view.readUserIntInput();
						while(!correctAge(age))
						{
							//make incorrect age method in the view
							age = a_view.readUserIntInput();
						}
						searchStratInterface = ss.getSearchGreaterThanAge(age);
						break;
					}
					case"2"://less than
					{
						a_view.searchAge();
						age = a_view.readUserIntInput();
						while(!correctAge(age))
						{
							//make incorrect age method in the view
							age = a_view.readUserIntInput();
						}
						searchStratInterface = ss.getSearchLessThanAge(age);
						break;
					}
					case"3"://equals
					{
						a_view.searchAge();
						age = a_view.readUserIntInput();
						while(!correctAge(age))
						{
							//make incorrect age method in the view
							age = a_view.readUserIntInput();
						}
						searchStratInterface = ss.getSearchByAge(age);
						break;
					}
					case"0"://RETURN
					{
						bc = new BoatClub();
						try {
							bc.boatClubStart(the_registry, a_view);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}			
				}
				break;
				
			}
			case"3"://boat type
			{
				a_view.boatTypeMessage();
				boatType = a_view.readUserInput();
				while(!mc.correctBoatTypeInput(a_view, boatType))
				{
					//make incorrect age method in the view
					boatType = a_view.readUserInput();
				}
				searchStratInterface = ss.getSearchByType(boatType);
				break;
			}
			case"4"://boat length
			{
				switch(a_view.readUserInput())
				{
					case"1"://grater than
					{
						a_view.boatLengthMessage();
						boatLength = Double.parseDouble(a_view.readUserInput());
						searchStratInterface = ss.getSearchByLengthGreaterThan(boatLength);
						break;
					}
					case"2"://less than
					{
						a_view.boatLengthMessage();
						boatLength = Double.parseDouble(a_view.readUserInput());
						searchStratInterface = ss.getSearchByLengthLessThan(boatLength);
						break;
					}
					case"3"://equals
					{
						a_view.boatLengthMessage();
						boatLength = Double.parseDouble(a_view.readUserInput());
						searchStratInterface = ss.getSearchByLengthEqualto(boatLength);
						break;
					}
					case"0"://RETURN
					{
						simpleSearch(a_view, the_registry);
						break;
					}			
				}
			}
			case"5"://id
			{
				a_view.enterID();
				ID = a_view.readUserInput();
				searchStratInterface = ss.getSearchByID(ID);
				break;
				
			}
			case"6"://month
			{
				a_view.enterMonth();
				month = a_view.readUserIntInput();
				while(!correctMonth(month))
				{
					month = a_view.readUserIntInput();
				}
				searchStratInterface = ss.getSearchByMonth(month);			
			}
			case"0"://return
			{
				try {
					bc.boatClubStart(the_registry, a_view);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return searchStratInterface;
		
	}
	/*
	public ISearchStrategy advancedSearch(IView a_view, Registry the_registry) throws IOException
	{
		a_view.displaySearchInstructions();
		switch(a_view.readUserInput())
		{
			case"1"://name
			{
				a_view.searchName();
				name = a_view.readUserInput();
				while(!correctName(name))
				{
					//make incorrect name method in the view
					name = a_view.readUserInput();
				}
					searchStratInterface = ss.getSearchByNameStrategy(name);
		
				advancedSearchAttributes(searchStratInterface, a_view, the_registry);
				break;
			}
			case"2"://age
			{
				a_view.SearchAttributeInstructions();
				switch(a_view.readUserInput())
				{
					case"1"://grater than
					{
						a_view.searchAge();
						age = a_view.readUserIntInput();
						while(!correctAge(age))
						{
							//make incorrect age method in the view
							age = a_view.readUserIntInput();
						}
						searchStratInterface = ss.getSearchGreaterThanAge(age);
						advancedSearchAttributes(searchStratInterface, a_view, the_registry);
						break;
					}
					case"2"://less than
					{
						a_view.searchAge();
						age = a_view.readUserIntInput();
						while(!correctAge(age))
						{
							//make incorrect age method in the view
							age = a_view.readUserIntInput();
						}
						searchStratInterface = ss.getSearchLessThanAge(age);
						advancedSearchAttributes(searchStratInterface, a_view, the_registry);
						break;
					}
					case"3"://equals
					{
						a_view.searchAge();
						age = a_view.readUserIntInput();
						while(!correctAge(age))
						{
							//make incorrect age method in the view
							age = a_view.readUserIntInput();
						}
						searchStratInterface = ss.getSearchByAge(age);
						advancedSearchAttributes(searchStratInterface, a_view, the_registry);
						break;
					}
					case"0"://RETURN
					{
						advancedSearch(a_view,the_registry);
					}			
				}
				break;
				
			}
			case"3"://boat type
			{
				a_view.boatTypeMessage();
				boatType = a_view.readUserInput();
				while(!mc.correctBoatTypeInput(a_view, boatType))
				{
					//make incorrect age method in the view
					boatType = a_view.readUserInput();
				}
				searchStratInterface = ss.getSearchByType(boatType);
				advancedSearchAttributes(searchStratInterface, a_view, the_registry);
				break;
			}
			case"4"://boat length
			{
				switch(a_view.readUserInput())
				{
					case"1"://grater than
					{
						a_view.boatLengthMessage();
						boatLength = Double.parseDouble(a_view.readUserInput());
						searchStratInterface = ss.getSearchByLengthGreaterThan(boatLength);
						advancedSearchAttributes(searchStratInterface, a_view, the_registry);
						break;
					}
					case"2"://less than
					{
						a_view.boatLengthMessage();
						boatLength = Double.parseDouble(a_view.readUserInput());
						searchStratInterface = ss.getSearchByLengthLessThan(boatLength);
						advancedSearchAttributes(searchStratInterface, a_view, the_registry);
						break;
					}
					case"3"://equals
					{
						a_view.boatLengthMessage();
						boatLength = Double.parseDouble(a_view.readUserInput());
						searchStratInterface = ss.getSearchByLengthEqualto(boatLength);
						advancedSearchAttributes(searchStratInterface, a_view, the_registry);
						break;
					}
					case"0"://RETURN
					{
						search(a_view, the_registry);
					}			
				}
			}
			case"5"://id
			{
				a_view.enterID();
				ID = a_view.readUserInput();
				searchStratInterface = ss.getSearchByID(ID);
				advancedSearchAttributes(searchStratInterface, a_view, the_registry);
				break;
				
			}
			case"6"://month
			{
				a_view.enterMonth();
				month = a_view.readUserIntInput();
				while(!correctMonth(month))
				{
					month = a_view.readUserIntInput();
				}
				searchStratInterface = ss.getSearchByMonth(month);
				advancedSearchAttributes(searchStratInterface, a_view, the_registry);
				}
			case"0"://return
			{
				bc = new BoatClub();
				bc.boatClubStart(the_registry, a_view);
			}
		}
		return searchStratInterface;
	}
		
	private ISearchStrategy advancedSearchAttributes(ISearchStrategy a_strategy, IView a_view, Registry the_registry) throws IOException
	{
		
		ISearchStrategy strat1 = null;
		ISearchStrategy strat2 = null;
		a_view.displaySearchInstructions();
		a_view.displayAdvancedSearchInstructions();
			switch(a_view.readUserInput())
			{
				case"1"://AND
				{
					strat1 = advancedSearch(a_view, the_registry);
					strat2 = advancedSearch(a_view, the_registry);
					break;
				}
				case"2"://OR
				{
					strat1 = advancedSearch(a_view, the_registry);
					strat2 = advancedSearch(a_view, the_registry);
					break;
				}
				case"3"://SEARCH
				{
					member = the_registry.searchforMembers(ss.getANDstrategy(strat1,strat2));
					a_view.displaySearchResults(member);
					break;
				}
				case"0"://RETURN
				{
					search(a_view, the_registry);
					break;
				}			
			}
			return a_strategy;
	}
	*/
	
	private boolean correctName(String name)
	{
		for(int i=0; i<name.length(); i++)
		{
			if(Character.isLetter(name.charAt(i)))
			{
				return true;
			}
		}
		return false;
	}
	private boolean correctAge(int age)
	{
		if(age > 0 && age < 120)
		{
			return true;
		}
		return false;
	}
	private boolean correctMonth(int month)
	{
		if(month > 0 && month < 13)
		{
			return true;
		}
		return false;
	}
	
}
