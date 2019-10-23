package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.entities.CategoryT;
@ManagedBean
@ApplicationScoped
public class TestEnum implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4881798023822559123L;
	

	public CategoryT[] getRoles() 
	{
		return CategoryT.values(); 
	}

	public TestEnum() {} 

	
	
}
