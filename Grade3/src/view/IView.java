package view;

import java.util.ArrayList;

import model.BoatType;
import model.Member;
import model.Registry;

public interface IView 
{
	/**
	 * console messages and information
	 */
	//MENU choices
	public void displayWelcomeMessage();
	public void displayFirstInstructions();
	public void displayAdminInstructions();
	public void displaySearchInstructions();
	public void displayAdvancedSearchInstructions();
	public void SearchAttributeInstructions();
	public void displayInitialSearchMenu();
	public void displayUpdateMemberInstructions();
	public void displayUpdateBoatInstructions();
	public void displayVerboseList(Registry the_registry);
	public void displayCompactList(Registry the_registry);
	public void displaySearchResults(ArrayList <Member> searchResultsList);
	
	//Ask for input
	public void provideName();
	public void provideSurname();
	public void providePersonalNumber();
	public void providePassword();
	public void chooseBoatToUpdateMessage();
	public void searchMemberToDeleteMessage();
	public void searchMemberMessage();
	public void boatTypeMessage();
	public void boatLengthMessage();
	public void chooseBoatToDeleteMessage();
	public void registerBoatMessage();
	public void displayDeleteBoatsMessage();
	public void deleteMemberMessage();
	public void authenticateMessage();
	public void enterID();
	public void enterMonth();
	public void enterPassword();
	public void searchName();
	public void searchAge();
	public void chooseMemberToUpdateMessage();
	
	//Success messages
	public void registerMemberSuccessMessage();
	public void updateBoatSuccessMessage();
	public void deleteMemberSuccessMessage();
	public void deleteBoatSuccessMessage();
	public void registerBoatSuccessMessage();
	public void updateNameSuccessMessage();
	public void updatePersonalNumberSuccessMessage();
	public void exitMessage();

	//Display other info
	public String printBoatList(Member a_member);
	public void displayMemberInfo(Member a_member);
	
	//read input
	public String readUserInput();
	public int readUserIntInput();
	public BoatType readBoatType(String input);
	
	//error messages
	public void invalidChoice();
	public void invalidName();
	public void invalidBoatLength();
	public void invalidPersonalNumber();
	public void invalidBoatType();
	public void invalidBoatNumber();
	public void invalidLogIn();
	public void emptyList();
	public void IDNotFoundMessage();
	public void hasBoatMessage();
	public void memberHasNoBoats();

}
