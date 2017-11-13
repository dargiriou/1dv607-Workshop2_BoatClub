package controller;

import java.io.IOException;

import model.Authentication;
import model.Boat;
import model.BoatType;
import model.Member;
import model.Registry;
import view.IView;

public class MemberController {

	private BoatClub bc;

	private boolean boatMenu = true;
	private boolean memberMenu = true;
	/**
	 * Registers a member
	 * @param a_view
	 * @param the_registry
	 */


		public void registerMember(IView a_view, Registry the_registry)
		{
			a_view.provideName();
			String name = a_view.readUserInput();
	    	while(!correctName(name,a_view))
	    		name = a_view.readUserInput();
	    		name = name.substring(0, 1).toUpperCase() + name.substring(1);//capitalize the 1st letter
	    		a_view.provideSurname();
	    		String surname = a_view.readUserInput();
	    		while(!correctName(surname,a_view))
	    			surname = a_view.readUserInput();
	    			surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);//capitalize the 1st letter			        	
	    			a_view.providePersonalNumber();
	    			String personalNumber = a_view.readUserInput();
	    			while(!correctPersonalNumber(personalNumber, a_view, the_registry))
	    			{
	    				personalNumber = a_view.readUserInput();
	        			if(personalNumber.contains("r"))
	        			{
	        				return;
	        			}
	    			}
	    			a_view.providePassword();
	    			String password = a_view.readUserInput();	    				
	    			Member a_member = new Member();
	    			a_member = the_registry.registerMember(name + " " + surname, personalNumber, password);
	    			a_view.hasBoatMessage();
	    			String answer = a_view.readUserInput();
	    				while(hasBoat(answer))
	    				{
	    					a_view.registerBoatMessage();
		        			a_view.boatTypeMessage();
		        			String boatType = a_view.readUserInput();
		        			while(!correctBoatTypeInput(a_view, boatType))
		        				boatType = a_view.readUserInput();
		        			a_view.boatLengthMessage();
		        			a_view.readBoatType(boatType);
		        			registerBoat(a_view, a_member, boatType);
		        			a_view.registerBoatSuccessMessage();
		        			a_view.displayCompactList(the_registry);
		        			a_view.hasBoatMessage();
		        			answer = a_view.readUserInput();
	    				}
	    				a_view.displayMemberInfo(a_member);
		}
		
		public void deleteMember(IView a_view, Registry the_registry)
		{
        	if(the_registry.emptyMemberList())
        	{
        		a_view.emptyList();
        		return;
        	}
        	a_view.searchMemberToDeleteMessage();
        	String id = a_view.readUserInput();
        	if(id.contains("r"))//return to main menu
        	{
        		return; //TODO CHACK IF RETURN WORKS THE SAME WAY WITH BREAK ( MAYBE set the boolean)
        	}
        	Member a_member = the_registry.lookForSpecificMember(id);
        	
        	while (a_member == null) 
    		 { 
    			a_view.IDNotFoundMessage();
        		id = a_view.readUserInput();
        		a_member = the_registry.lookForSpecificMember(id);
    		 }
    			a_view.displayMemberInfo(a_member);
    			a_member.getNumberOfBoats();
        			a_view.deleteMemberMessage();
	        		 String input = a_view.readUserInput();
	        			if(input.contains("y"))
	        			{
	        				the_registry.deleteMember(a_member);
	        				a_view.deleteMemberSuccessMessage();
	        			}
		}
		
		public void updateMember(IView a_view, Registry the_registry, AuthenticationController ac, Authentication m_auth)
		{
			String id = null;
			if(the_registry.emptyMemberList()) //if the member list is empty 
        	{
        		a_view.emptyList();
        		return;	
        	}
			if(m_auth.isLoggedinAdmin())
			{	a_view.displayVerboseList(the_registry);
				a_view.chooseMemberToUpdateMessage();
				id = a_view.readUserInput();
				while(!isCorrectID(id, the_registry))
				{
					a_view.IDNotFoundMessage();
					id = a_view.readUserInput();
					if(id.contains("r"))
					{
						return;
					}
				}
			}
			else if(m_auth.isLoggedinMember())
			{
				id = ac.getID();
			}
        	Member a_member = the_registry.lookForSpecificMember(id);
        	if(a_member != null)
        	{
	        a_view.displayMemberInfo(a_member);
	        a_member.getBoatList();
        	}
        		while(a_member == null)
        		{
        			a_view.IDNotFoundMessage();
        			id = a_view.readUserInput();
        			
        			if(id.contains("r"))
        			{
        				break;
        			}
        				a_member = the_registry.lookForSpecificMember(id);
        				if(a_member!=null)
        				{
					        a_view.displayMemberInfo(a_member);
					        a_member.getBoatList(); 
					        break;
        				}
        			}
        	while(memberMenu)
        	{
        		boatMenu = true;
	        	a_view.displayUpdateMemberInstructions();
        		switch(a_view.readUserInput())
		        {
        			case "1"://Update name
        			{
        				updateMembersName(a_view, a_member);
        				break;
        			}
        			case "2"://Update personal number
        			{
        				updateMembersPersonalNumber(a_view, a_member, the_registry);
        				break;
        			}
        			case "3"://Register a boat
        			{
			        	a_view.registerBoatMessage();
			        	String boatType = a_view.readUserInput();
	        			while(!correctBoatTypeInput(a_view, boatType))
	        				boatType = a_view.readUserInput();
	        			a_view.boatLengthMessage();
	        			registerBoat(a_view, a_member, boatType);
	        			a_view.registerBoatSuccessMessage();
        				break;
        			}
        			case "4"://Update boat
        			{	if(a_member.emptyBoatList())
        				{
        					a_view.memberHasNoBoats();
        					break;
        				}
        				
        				while(boatMenu)
        				{
        				a_view.chooseBoatToUpdateMessage();
        					
        					String input = a_view.readUserInput();
            
        					if(validBoatNumber(a_view, input, a_member))
        					{
        						Boat a_boat = (boatChosenByUser(a_view,a_member,input));
        						a_view.displayUpdateBoatInstructions();
        						

        						switch(a_view.readUserInput())
        						{//Boat Menu
        							case "1":
        							{
        								a_view.boatTypeMessage();
        								input = a_view.readUserInput();
        								while(true)
        								{//Update boat type
	        								if(correctBoatTypeInput(a_view, input))
	        								{
		        								BoatType type = a_view.readBoatType(input);
		        								try 
		        								{
		        									a_boat.setBoatType(type);
			        								a_view.updateBoatSuccessMessage();
			        								boatMenu = false;
			        								break;
												} 	
		        									catch (Exception e) 
		        									{
														a_view.invalidBoatType();
		        									}
		        							}
        									input = a_view.readUserInput();	
        								}
        								break;
        							}
        							case "2"://update boat length
        							{
        								a_view.boatLengthMessage();
        								input = a_view.readUserInput();
        								while(true)
        								{
	        								if(correctBoatLength(input));
	        								{
		        								try 
		        								{
		        									a_boat.setBoatLength(Double.parseDouble(input));
			        								a_view.updateBoatSuccessMessage();
			        								boatMenu = false;
			        								break;
												} 	
		        									catch (Exception e) 
		        									{
														a_view.invalidBoatLength();
		        									}
	        								}
        									input = a_view.readUserInput();
        								}
        								break;
        							}
        							case "3"://return to the previous menu
        							{
        								boatMenu = false;
        								break;
        							}
        						default:{a_view.invalidChoice();break;}
        						}	//switch end                          
        					}
        				else{continue;}
        				}
        				break;
        			}
        			case "5"://Delete a boat
        			{	
        				if(a_member.emptyBoatList())
        				{
        					a_view.memberHasNoBoats();
        					break;
        				}
        				else
        				{
        					a_view.displayMemberInfo(a_member);
        					a_view.chooseBoatToDeleteMessage();
        					String input = a_view.readUserInput();
        					while(true)
        					{
	        					if(validBoatNumber(a_view, input, a_member))
	        					{
		        					Boat a_boat = (boatChosenByUser(a_view,a_member,input));
		        					a_member.removeBoat(a_boat);
		        					a_view.deleteBoatSuccessMessage();
			        				break;
	        					}
	        					else
	        					{
	        						break;
	        					}
        					}
        				}
        				break;	
        			}
        			case "6":
        			{
        				memberMenu = false;
        			break;
        			}
        			case "7":
        			{
        				memberMenu = false;
        			try {
						bc.saveAndExit(a_view, the_registry);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			}
			        default:a_view.invalidChoice();
		        }	
        	}
		}
    	/**
    	 * Returns a boat chosen by the user
    	 * @param a_view
    	 * @param a_member
    	 * @param input
    	 * @return
    	 */
		
		private Boat boatChosenByUser(IView a_view, Member a_member, String input)
		{
			//TODO fix FIND boat return appropriate boat
			Boat a_boat = new Boat();
			for(Boat b : a_member.getBoatList())
			{
				if (b.getBoatId().equals(input))
				{
					a_boat = b;
				}
			}
			return a_boat;
		}
		/**
		 * Registers a boat with type and length given by the user
		 * @param a_view
		 * @param a_member
		 * @param type
		 */
		private void registerBoat(IView a_view, Member a_member,String type) 
		{
			switch(a_view.readBoatType(type))
	        {				
		        case SAILBOAT:
		        {
		        	String length = a_view.readUserInput();
		        	while(true)
		        	{
		        		if(correctBoatLength(length))
		        		{
		        			a_member.addBoat(BoatType.SAILBOAT, Double.parseDouble(length), a_member.incrementBoatListSize());
		        			break;
		        		}
		        		else{
		        			a_view.invalidBoatLength();
		        		}
		        		length = a_view.readUserInput();
		        	}
		        	break;
		        }	
		        case MOTORSAILER:
		        {
		        	String length = a_view.readUserInput();
		        	while(true)
		        	{
		        		if(correctBoatLength(length))
		        		{
		        			a_member.addBoat(BoatType.MOTORSAILER, Double.parseDouble(length), a_member.incrementBoatListSize());
		        			break;
		        		}
		        		else{
		        			a_view.invalidBoatLength();
		        		}
		        		length = a_view.readUserInput();
		        	}
		        	break;
		        }
		        case KAYAK:
		        {
		        	String length = a_view.readUserInput();
		        	while(true)
		        	{
		        		if(correctBoatLength(length))
		        		{
		        			a_member.addBoat(BoatType.KAYAK, Double.parseDouble(length), a_member.incrementBoatListSize());
		        			break;
		        		}
		        		else{
		        			a_view.invalidBoatLength();
		        		}
		        		length = a_view.readUserInput();
		        	}
		        	break;
		        }
		        case CANOE:
		        {
			        	String length = a_view.readUserInput();
			        	while(true)
			        	{
			        		if(correctBoatLength(length))
			        		{
			        			a_member.addBoat(BoatType.CANOE, Double.parseDouble(length), a_member.incrementBoatListSize());
			        			break;
			        		}
			        		else{
			        			a_view.invalidBoatLength();
			        		}
			        		length = a_view.readUserInput();
			        	}
			        	break;
			        }
		        case OTHER:
		        {
		        	String length = a_view.readUserInput();
		        	while(true)
		        	{
		        		if(correctBoatLength(length))
		        		{
		        			a_member.addBoat(BoatType.OTHER, Double.parseDouble(length), a_member.incrementBoatListSize());
		        			break;
		        		}
		        		else{
		        			a_view.invalidBoatLength();
		        		}
		        		length = a_view.readUserInput();
		        	}
		        	break;
		        }		
	        }		
		}
		/**
		 * Updates the members name
		 * @param a_view
		 * @param a_member
		 */
		private void updateMembersName(IView a_view, Member a_member)
		{
			a_view.provideName();
			String name = a_view.readUserInput();
	    	while(!correctName(name,a_view))
	    		name = a_view.readUserInput();
	    		name = name.substring(0, 1).toUpperCase() + name.substring(1);//capitalize the 1st letter
	    		a_view.provideSurname();
	    		String surname = a_view.readUserInput();
	    		while(!correctName(surname,a_view))
	    			surname = a_view.readUserInput();
	    			surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);//capitalize the 1st letter	
			a_member.setMembersName(name + " " + surname);
			a_view.updateNameSuccessMessage();
		}
		/**
		 * Update the members personal number
		 * @param a_view
		 * @param a_member
		 * @param the_registry
		 */
		private void updateMembersPersonalNumber(IView a_view, Member a_member, Registry the_registry)
		{
			
			a_view.providePersonalNumber();
			String personalNumber = a_view.readUserInput();
			while(!correctPersonalNumber(personalNumber, a_view, the_registry))
			{
				personalNumber = a_view.readUserInput();
    			if(personalNumber.contains("r"))
    			{
    				bc.memberMenu = false;
    				return;
    			}
			}
			a_member.setMembersPersonalNumber(personalNumber);
			a_view.updatePersonalNumberSuccessMessage();
		}
		
		/***********************************************************************
		 *       HELPER METHODS TO CHECK FOR CORRECT INPUT                     *
		 ***********************************************************************/
		/**
		 * Checks if the sting entered is made of letters
		 * @param name
		 * @param a_view
		 * @return
		 */
		private boolean correctName(String name, IView a_view)
		{
		    char[] characters = name.toCharArray();
		    for (char c : characters)
		    {
		        if(!Character.isLetter(c)) 
		        {
		        	a_view.invalidName();
		            return false;  
		        }
		    }
		    	return true;
		}	
		/**
		 * Checks if the sting entered is made of numbers and if it is the correct size for personal number.
		 * @param personalNumber
		 * @param a_view
		 * @return
		 */
		private boolean correctPersonalNumber(String personalNumber, IView a_view, Registry the_registry)
		{
			boolean temp = true;
			for(Member m : the_registry.getMemberList())
			{
				if(personalNumber.equals(m.getMembersPersonalNumber()))
				{
					temp = false;
				}
			}
				if(personalNumber.length() > 12 || personalNumber.length() < 10 || !personalNumber.chars().allMatch(x -> Character.isDigit(x))) 
				{
					temp = false;
				}
				 if(temp == false)
				 {
					 a_view.invalidPersonalNumber();
				 }
			return temp;
		}
		/**
		 * Checks if the string entered is an existing boat type
		 * @param a_view
		 * @param a_type
		 * @return
		 */
		public boolean correctBoatTypeInput(IView a_view, String a_type)
		{
			for( BoatType type : BoatType.values()) 
			{
			    if(type.name().equals(a_type.toUpperCase()))
			    {
			    	return true;
			    }
			}
			a_view.invalidBoatType();
			return false;
		}
		/**
		 * Checks for correct input for choosing a boat from the list
		 * @param a_view
		 * @param input
		 * @param a_member
		 * @return
		 */
		private boolean validBoatNumber(IView a_view,String input, Member a_member)
		{
				for (int i = 0; i < input.length(); i++) 
				{
					if (!Character.isDigit(input.charAt(i)))
					{
						a_view.invalidBoatNumber();
						return false;
					}
				}
					 if(Integer.valueOf(input) > a_member.getNumberOfBoats() || Integer.valueOf(input) < 1 )
					{
					    a_view.invalidBoatNumber();
					    return false;
					}
					else
					{
						return true;
					}			
		}
		/**
		 * Check if the input is a double
		 * @param str
		 * @return
		 */
		private boolean correctBoatLength(String str)  
		{  
		  try  
		  {  
		    Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;  
		}
		/**
		 * 
		 * @param answer
		 * @return
		 */

		private boolean hasBoat(String answer)
		{
			if(answer.contains("y"))
			{
				return true;
			}
			return false;
		}
		
		private boolean isCorrectID(String ID, Registry the_registry)
		{
			for(Member m: the_registry.getMemberList())
			{
				if(m.getMembersID().equals(ID))
				{
					return true;
				}
			}
			return false;
		}
}
