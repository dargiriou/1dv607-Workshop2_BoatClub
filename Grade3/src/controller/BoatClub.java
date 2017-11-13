package controller;

import java.io.IOException;

import model.Authentication;
import model.FileManager;
import model.Registry;
import view.IView;
public class BoatClub 
{

	boolean mainMenu = true;
	boolean memberMenu = true;
	boolean boatMenu = true;
	boolean adminloggedIn = true;
	boolean isLoggedIn = true;
	AuthenticationController ac;
	Authentication m_auth;
	MemberController a_mc;
	SearchController sc;
	
	public BoatClub()
	{
		ac = new AuthenticationController();
		m_auth = new Authentication();
		a_mc = new MemberController();
		sc = new SearchController();
	}

	
	public void boatClubStart(Registry the_registry, IView a_view) throws IOException
	{
		a_view.displayWelcomeMessage();
		while(mainMenu)
			{
			a_view.displayFirstInstructions();
			switch(a_view.readUserInput())
			{
			case"1"://log in
			{	isLoggedIn = true;
				while(isLoggedIn)
				{
					ac.login(a_view, the_registry, m_auth);
					if(m_auth.isLoggedinAdmin())
					{
						adminloggedIn = true;
						while(adminloggedIn)
						{
							a_view.displayAdminInstructions();
							switch(a_view.readUserInput())
					        {
						        case "1"://Register member
						        {
						        	a_mc.registerMember(a_view, the_registry);			        			
						        	break;
						        }
						        case "2": //Update member
						        { 
						        	a_mc.updateMember(a_view, the_registry,ac, m_auth);
						        	break;
						        }
						        case "3":
						        {
						        	//Delete a member
						        	a_mc.deleteMember(a_view, the_registry);
							        break;	
						        }			
						        case "4"://show compact list
						        {
						        	if(the_registry.emptyMemberList())
						        	{
						        		a_view.emptyList();
						        		break;
						        	}
						        	else
						        	{
							        	a_view.displayCompactList(the_registry);
							        	break;
						        	}
						        }
						        case "5"://show verbose list
						        {
						        	if(the_registry.emptyMemberList())
						        	{
						        		a_view.emptyList();
						        		break;
						        	}
						        	else
						        	{
						        		a_view.displayVerboseList(the_registry);
						        		break;
						        	}
						        }
						        case"6"://log out
						        {
						        	adminloggedIn = false;
						        	isLoggedIn = false;
						        	break;
						        }
						        case "7"://exit
						        {
						        	saveAndExit(a_view, the_registry);
						        }
						        default:a_view.invalidChoice();
						    }
						}
					}
					else if(m_auth.isLoggedinMember())
					{
							a_mc.updateMember(a_view, the_registry, ac, m_auth);
				        	break;
					}
					break;
				}
				break;
			}
			case"2"://search
			{
				sc.search(a_view, the_registry);
				break;
			}
			case"3"://compact
			{
				a_view.displayCompactList(the_registry);
				break;
			}
			case"4"://verbose
			{
				a_view.displayVerboseList(the_registry);
				break;
			}
			
			case"5"://exit	
			{
				saveAndExit(a_view, the_registry);
			}
		}
	}
}//end of boatStart
	
	public void saveAndExit(IView a_view, Registry the_registry) throws IOException
	{
    	FileManager fm = new FileManager();
    	fm.saveRegistry(the_registry, a_view);// saves the registry data onto a file
    	a_view.exitMessage();
		mainMenu = false;
		System.exit(0);
	}


}