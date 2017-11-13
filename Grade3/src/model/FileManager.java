package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Registry;
import view.IView;

public class FileManager {
	
	
	public void saveRegistry(Registry the_registry,IView a_view) throws IOException 
	{
		jaxbObjectToXML(the_registry,a_view);
		
	}

		public Registry loadRegistry()
		{
			Registry registryData = jaxbXMLToObject();
			return registryData;
		}
		
		private static void jaxbObjectToXML(Registry the_registry, IView a_view) throws IOException
		{
			File file = new File(System.getProperty("user.dir"), "RegistryData.xml");

	        try {
	        	if(the_registry.emptyMemberList())
	        	{
	        		a_view.emptyList();
	        	}
	        	else
	        	{
	        		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		            JAXBContext context = JAXBContext.newInstance(Registry.class);
		            Marshaller m = context.createMarshaller();
		            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		            m.marshal(the_registry, out);
	        	}

	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
	    }

	    private static Registry jaxbXMLToObject() 
	    {
	     File file = new File(System.getProperty("user.dir"), "RegistryData.xml");
	        try {
	            JAXBContext context = JAXBContext.newInstance(Registry.class);
	            Unmarshaller un = context.createUnmarshaller();
	            Registry reg = (Registry) un.unmarshal(file);
	            return reg;
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    /**
	     * 
	     * @return
	     */
	    public File getFile()
	    {	
		    File file = new File(System.getProperty("user.dir"), "RegistryData.xml");
			return file;
	    }
	}

