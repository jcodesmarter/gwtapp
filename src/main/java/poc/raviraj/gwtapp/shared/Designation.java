package poc.raviraj.gwtapp.shared;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@DiscriminatorValue("designation")
public class Designation extends MasterType{

	private static final long serialVersionUID = 1L;
	
	public Designation(){
		//required for generating proxy.. proxy is generated by the gwt compiler
	}
	
	@Override
	@Min(value = 1, message="Designation is not specified")
	public Long getKeyId() {
		return super.getKeyId();
	}
}
