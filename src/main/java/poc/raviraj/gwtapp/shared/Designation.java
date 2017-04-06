package poc.raviraj.gwtapp.shared;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("designation")
public class Designation extends MasterType{

	private static final long serialVersionUID = 1L;
	
	public Designation(){
		//required for generating proxy.. proxy is generated by the gwt compiler
	}
}
