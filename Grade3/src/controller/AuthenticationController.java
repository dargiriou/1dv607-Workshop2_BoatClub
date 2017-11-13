package controller;

import view.IView;
import model.Authentication;
import model.Registry;

public class AuthenticationController 
{
	private String m_id;

	public void login(IView a_view,Registry the_registry,Authentication m_auth)
	{
		
		a_view.authenticateMessage();
		a_view.enterID();
		m_id = a_view.readUserInput();
		while(true)
		{
			a_view.enterPassword();
			String m_password = a_view.readUserInput();
			if(m_auth.AuthenticateAdmin(m_id, m_password) == true || m_auth.AuthenticateMember(m_id, m_password, the_registry) == true)
			{
				break;
			}
			else
			{
				a_view.invalidLogIn();
				a_view.enterID();
				m_id = a_view.readUserInput();
				if(m_id.contentEquals("r"))
					break;
				a_view.enterPassword();
				m_password = a_view.readUserInput();
			}
		}

	}
	
	public String getID()
	{
		return m_id;
	}
}
