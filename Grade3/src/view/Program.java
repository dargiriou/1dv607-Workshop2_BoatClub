package view;


import java.io.IOException;

import controller.AuthenticationController;
import controller.BoatClub;
import controller.MemberController;
import model.Authentication;
import model.FileManager;
import model.Registry;
import view.Console;
import view.IView;

public class Program
{
	public static void main(String[] args) throws IOException 
	{
	  FileManager fm = new FileManager();
           BoatClub c = new BoatClub();
           Registry r = new Registry();
           AuthenticationController ac = new AuthenticationController();
           MemberController mc = new MemberController();
           Authentication m_auth = new Authentication();
           if(!fm.getFile().exists())
           {
        	   try 
        	   {
        		   fm.getFile().createNewFile();
        	   } catch (IOException e) 
        	   	{
        		   e.printStackTrace();
        	   	}
           }
           else if (fm.getFile().length() == 0)
           {
               IView a_view = new Console();
               c.boatClubStart(r, a_view);
           }
           else
           {
        	   r = fm.loadRegistry();
               IView a_view = new Console();
                c.boatClubStart(r, a_view);
           }         
	}
}
