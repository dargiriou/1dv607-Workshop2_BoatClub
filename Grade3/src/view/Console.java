package view;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Boat;
import model.BoatType;
import model.Member;
import model.Registry;

public class Console implements IView
{	
	Scanner sc = new Scanner(System.in);
	/***********************************************************************
	 *                         DISPLAYING THE MENUS                        *
	 ***********************************************************************/
	
	@Override
	public void displayWelcomeMessage()
	{
		System.out.println("\n\n");
		System.out.println("|--------------------------------------------|");
        System.out.println("|       WELCOME TO THE MEMBER REGISTRY       |");
		System.out.println("|--------------------------------------------|\n");
     }
	
	@Override	
	public void displayFirstInstructions()
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) LOG IN                                 |");
		System.out.println("| 2.) SEARCH                                 |");
    	System.out.println("| 3.) DISPLAY ALL MEMBERS IN A COMPACT LIST  |");
    	System.out.println("| 4.) DISPLAY ALL MEMBERS IN A VERBOSE LIST  |");
    	System.out.println("| 5.) EXIT                                   |");
    	System.out.println("|--------------------------------------------|\n");
	}
	
	@Override
	public void displayInitialSearchMenu()
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) SIMPLE SEARCH                          |");
		/*System.out.println("| 2.) COMPLEX SEARCH                         |");*/
		System.out.println("| 0.) RETURN                                 |");
    	System.out.println("|--------------------------------------------|\n");
	}
	@Override
	public void displayAdvancedSearchInstructions()
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) AND                                    |");
		System.out.println("| 2.) OR                                     |");
		System.out.println("| 3.) INITIATE SEARCH                        |");
		System.out.println("| 0.) RETURN                                 |");
    	System.out.println("|--------------------------------------------|\n");
	}
	
	@Override
	public void SearchAttributeInstructions()
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) GREATER THAN                           |");
		System.out.println("| 2.) LESS THAN                              |");
		System.out.println("| 3.) EQUALS                                 |");		
		System.out.println("| 0.) RETURN                                 |");
    	System.out.println("|--------------------------------------------|\n");
	}

	@Override
	public void displaySearchInstructions() {
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) NAME                                   |");
		System.out.println("| 2.) AGE                                    |");
		System.out.println("| 3.) BOAT TYPE                              |");
		System.out.println("| 4.) BOAT LENGTH                            |");
		System.out.println("| 5.) ID                                     |");
		System.out.println("| 6.) MONTH                                  |");
    	System.out.println("| 0.) RETURN                                 |");
    	System.out.println("|--------------------------------------------|\n");	
	}
	
	@Override	
	public void displayAdminInstructions()
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) REGISTER A MEMBER                      |");
		System.out.println("| 2.) UPDATE MEMBER INFORMATION              |");
		System.out.println("| 3.) DELETE A MEMBER                        |");
    	System.out.println("| 4.) DISPLAY ALL MEMBERS IN A COMPACT LIST  |");
    	System.out.println("| 5.) DISPLAY ALL MEMBERS IN A VERBOSE LIST  |");
    	System.out.println("| 6.) LOG OUT                                |");
    	System.out.println("| 7.) SAVE & EXIT                            |");
    	System.out.println("|--------------------------------------------|\n");
	}
	
	@Override
	public void displayUpdateMemberInstructions()
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) UPDATE NAME                            |");
    	System.out.println("| 2.) UPDATE PERSONAL NUMBER                 |");
    	System.out.println("| 3.) REGISTER A BOAT                        |");
    	System.out.println("| 4.) UPDATE A BOAT                          |");
    	System.out.println("| 5.) DELETE A BOAT                          |");
    	System.out.println("| 6.) LOG OUT                                |");
    	System.out.println("|--------------------------------------------|\n");
		
	}
	
	@Override
	public void displayUpdateBoatInstructions() 
	{
		System.out.println("|--------------------------------------------|");
		System.out.println("|            SELECT ONE OPTION               |");
		System.out.println("|--------------------------------------------|");
		System.out.println("| 1.) UPDATE BOAT TYPE                       |");
    	System.out.println("| 2.) UPDATE BOAT LENGTH                     |");
    	System.out.println("| 3.) RETURN                                 |");
    	System.out.println("|--------------------------------------------|\n");
	}

	/***********************************************************************
	 *                         READING INPUT                               *
	 ***********************************************************************/
	@Override
	public String readUserInput()
	{
		return sc.next();	
	}

	@Override
	public int readUserIntInput()
	{
		int integerInput = 0;
		try
		{
		  System.out.println("PLEASE INPUT AN INTEGER");
		  integerInput = sc.nextInt();
		}
		catch(InputMismatchException exception)
		{

		  System.err.println("THIS IS NOT AN INTEGER");
		}
		return integerInput;
	}


	
	@Override
	public BoatType readBoatType(String input)
	{
		BoatType type = null;
		type = BoatType.valueOf(input.toUpperCase());
		return type;
	}
	/***********************************************************************
	 *                         MENU CHOICES                                *
	 ***********************************************************************/
	@Override
	public void searchMemberMessage()
	{
		System.out.println("ENTER THE ID OF THE MEMBER YOU WANT TO MODIFY OR PRESS 'r' TO RETURN: ");	
	}
	
	@Override
	public void searchMemberToDeleteMessage()
	{
		System.out.println("ENTER THE ID OF THE MEMBER YOU WANT TO DELETE OR PRESS 'r' TO RETURN:\n ");
	}
	@Override
	public void registerBoatMessage()
	{
		System.out.println("REGISTER A BOAT");
		System.out.println("AVAILABLE TYPES: SAILBOAT, MOTORSAILER, KAYAK, CANOE, OTHER"); 
	}
	@Override
	public void displayDeleteBoatsMessage()
	{
		System.out.println("SELECT A BOAT TO DELETE");
	}
	@Override
	public void provideName()
	{
		System.out.print("PROVIDE A NAME: ");
	}
	@Override
	public void provideSurname() 
	{
		System.out.print("PROVIDE A SURNAME: ");
	}
	@Override
	public void providePersonalNumber() 
	{
		System.out.print("PROVIDE THE PERSONAL NUMBER\n (YYYYMMDDXXX) or (YYMMDDXXX): ");
		
	}
	@Override
	public void providePassword() 
	{
		System.out.print("PROVIDE A PASSWORD: ");
		
	}
	@Override
	public void deleteMemberMessage()
	{
		System.out.print("ARE YOU SURE YOU WANT TO DELETE THIS MEMBER? (YES / NO)");
	}

	@Override
	public void boatTypeMessage()
	{
		System.out.print("ENTER BOAT TYPE: ");
		
	}

	@Override
	public void boatLengthMessage()
	{
		System.out.print("ENTER BOAT LENGTH: ");	
	}

	@Override
	public void chooseBoatToUpdateMessage() 
	{
		System.out.print("SELECT THE NUMBER OF THE BOAT YOU WANT TO UPDATE: ");
	}
	@Override
	public void chooseBoatToDeleteMessage()
	{
		System.out.print("SELECT THE NUMBER OF THE BOAT YOU WANT TO DELETE: ");	
	}
	@Override
	public void hasBoatMessage()
	{
		System.out.println("DO YOU WANT TO REGISTER A BOAT (YES/NO)?\n ");
	}
	@Override
	public void authenticateMessage() 
	{
		System.out.print("ENTER ID AND PASSWORD TO LOG IN: \n");	
	}

	@Override
	public void enterID() 
	{
		System.out.print("ENTER ID: ");			
	}
	@Override
	public void enterMonth()
	{
		System.out.print("ENTER THE NUMERICAL VALUE OF A MONTH: ");
	}

	@Override
	public void enterPassword() 
	{
		System.out.print("ENTER PASSWORD: ");			
	}
	@Override
	public void chooseMemberToUpdateMessage()
	{
		System.out.print("ENTER THE ID OF THE MEMBER YOU WANT TO UPDATE: ");	
	}

	@Override
	public void searchName()
	{
		System.out.print("ENTER A NAME OR A CHARACTER SEQUENCE WITHIN A NAME: ");	
	}
	
	@Override
	public void searchAge()
	{
		System.out.print("ENTER AN AGE: ");	
	}
	@Override
	public void exitMessage()
	{
		System.out.println("REGISTRY SAVED");
		System.out.println("||<< GOODBYE >>||");
	}
	/***********************************************************************
	 *                         SUCCESS MESSAGES                            *
	 ***********************************************************************/
	@Override
	public void registerBoatSuccessMessage()
	{
		System.out.println("BOAT REGISTERED!\n");
	}
	@Override
	public void deleteBoatSuccessMessage()
	{
		System.out.println("BOAT DELETED\n");
	}
	@Override
	public void updateBoatSuccessMessage()
	{
		System.out.println("BOAT UPDATED\n");
	}
	@Override
	public void deleteMemberSuccessMessage()
	{
		System.out.println("MEMBER DELETED\n");
	}
	@Override
	public void registerMemberSuccessMessage()
	{
		System.out.println("MEMBER REGISTERED\n");
	}
	@Override
	public void updateNameSuccessMessage() 
	{
		System.out.println("NAME UPDATED\n");	
	}
	@Override
	public void updatePersonalNumberSuccessMessage() 
	{
		System.out.println("PERSONAL NUMBER UPDATED\n");		
	}

	/***********************************************************************
	 *                         ERROR MESSAGES                              *
	 ***********************************************************************/
	@Override
	public void invalidChoice()
	{
		System.err.println("INVALID CHOICE");
	}
	@Override
	public void invalidName()
	{
		System.err.print(" SHOULD CONTAIN ONLY LETTERS.\n TRY AGAIN...");
	}
	@Override
	public void invalidPersonalNumber()
	{
		System.err.print("INVALID PERSONAL NUMBER. \n TRY AGAIN OR PRESS 'r' TO RETURN...");
	}
	@Override
	public void invalidBoatType()
	{
    System.err.println("INVALID BOAT TYPE.\n TRY AGAIN...");
	System.err.println("AVAILABLE TYPES: SAILBOAT, MOTORSAILER, KAYAK, CANOE, OTHER");
	}
	@Override
	public void invalidBoatLength()
	{
		System.err.println("INVALID BOAT LENGTH\n TRY AGAIN...");
	}
	@Override
	public void invalidBoatNumber()
	{
		System.err.println("INVALID BOAT NUMBER \n TRY AGAIN...\n");
	}

	@Override
	public void emptyList()
	{
		System.err.println("EMPTY LIST\n");
	}
	@Override
	public void IDNotFoundMessage()
	{
		System.err.print("MEMBER NOT FOUND\n TRY AGAIN OR PRESS 'r' TO RETURN: \n");
	}
	
	@Override
	public void memberHasNoBoats() 
	{
		System.err.println("THIS MEMBER HAS NO REGISTERED BOATS");
	}

	@Override
	public void invalidLogIn()
	{
		System.err.println("\nINVALID LOG IN! INCORRECT USERNAME OR PASSWORD!\nTRY AGAIN OR ENTER r TO RETURN: ");		
	}
	
	
	/***********************************************************************
	 *                        DISPLAYING INFORMATION                       *
	 ***********************************************************************/
	@Override
	public void displayMemberInfo(Member a_member)
	{
		System.out.println("   ___________________________________________________________________________________________");
		System.out.println("   |        NAME        | PERSONAL NUMBER |   ID   | BOATS |             BOAT INFO            |");
		System.out.println("   -------------------------------------------------------------------------------------------|");

		System.out.println("   |                    |                 |        |       |                                  |");
		System.out.printf("   |%-20s|%-17s|%-8s|%-7s|%-34s \n",a_member.getMembersFullName(), a_member.getMembersPersonalNumber()
					,a_member.getMembersID(),a_member.getNumberOfBoats(), 
					printBoatList(a_member));
		System.out.println("   |____________________|_________________|________|_______|__________________________________|\n");
	}	

	@Override
	public void displayVerboseList(Registry the_registry) 
	{
		int i = 1;
		System.out.println("	VERBOSE LIST\n");
		System.out.println("   ___________________________________________________________________________________________");
		System.out.println("   |        NAME        | PERSONAL NUMBER |   ID   | BOATS |             BOAT INFO            |");
		System.out.println("   -------------------------------------------------------------------------------------------|");
		for(Member m: the_registry.getMemberList())
		{ 
			System.out.println("   |                    |                 |        |       |                                  |");
			System.out.printf("%-3d|%-20s|%-17s|%-8s|%-7s|%-34s \n",i + 1,m.getMembersFullName(), m.getMembersPersonalNumber()
					,m.getMembersID(),m.getNumberOfBoats(), 
					printBoatList(m));
			System.out.println("   |____________________|_________________|________|_______|__________________________________|\n");
			i++;
		}
	}
	@Override
	public void displayCompactList(Registry the_registry) 
	{
		int i = 1;
		System.out.println("	COMPACT LIST\n");
		System.out.println("   ______________________________________");
		System.out.println("   |        NAME        |   ID   | BOATS |");
		System.out.println("   --------------------------------------|");
		for(Member m: the_registry.getMemberList())
		{
			System.out.println("   |                    |        |       |");
			System.out.printf("%-3d|%-20s|%-8s|%-7s|\n", i, m.getMembersFullName(),m.getMembersID(),m.getNumberOfBoats());
			System.out.println("   |____________________|________|_______|\n");
			i++;
		}
	}
	@Override	
	public String printBoatList(Member a_member) 
	{
		int i = 1;
		StringBuilder sb = new StringBuilder();
		for(Boat b : a_member.getBoatList())
		{  
	    	   sb.append(i + "."+"TYPE: " + b.getType() + " " + "LENGTH: " + b.getLength() + " M \n\t\t\t\t\t\t\t    ");
	    	   i++;
		}
		return sb.toString();
	}

	@Override
	public void displaySearchResults(ArrayList<Member> searchResultsList) 
	{
		if(searchResultsList.isEmpty())
		{
			System.err.println("NO RESULTS FOUND");
		}
		else
		{
			int i = 1;
			System.out.println("	SEARCH RESULTS\n");
			System.out.println("   ___________________________________________________________________________________________");
			System.out.println("   |        NAME        | PERSONAL NUMBER |   ID   | BOATS |             BOAT INFO            |");
			System.out.println("   -------------------------------------------------------------------------------------------|");
			for(Member m: searchResultsList)
			{ 
				System.out.println("   |                    |                 |        |       |                                  |");
				System.out.printf("%-3d|%-20s|%-17s|%-8s|%-7s|%-34s \n",i + 1,m.getMembersFullName(), m.getMembersPersonalNumber()
						,m.getMembersID(),m.getNumberOfBoats(), 
						printBoatList(m));
				System.out.println("   |____________________|_________________|________|_______|__________________________________|\n");
				i++;
			}
		}
	}
}

	

