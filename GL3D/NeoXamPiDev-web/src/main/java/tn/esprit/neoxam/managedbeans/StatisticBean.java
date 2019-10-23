package tn.esprit.neoxam.managedbeans;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.google.gson.Gson;

import tn.esprit.entities.Produit;
import tn.esprit.neo.services.ProduitService;

@ManagedBean
@SessionScoped
public class StatisticBean {
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	ProduitService produitService ;
    Produit p = new Produit();
     

    	public String Statistic(){
    		
    		List<Object> list =produitService.ChartProject();
    		
    		String[] tab = new String[10];
    		 
    	
    		//return "[{name: 'Chrome', y: 61.41, sliced: true, selected: true}, { name: 'Internet Explorer', y: 11.84}, { name: 'Firefox', y: 10.85 }, {name: 'Edge',y: 4.67}, {name: 'Safari',y: 4.18}, { name: 'Other', y: 7.05}]";
    		//return gson.toJson(list);
    		
    		return new Gson().toJson(list);
    	}

	
	
	

	
	
	
	
}
