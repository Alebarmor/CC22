package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends AbstractEntity{
	
	//Serialisation indentifier --------------------------------------------------------------- 
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes --------------------------------------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String defaultCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	protected String strongSpamTerms;
	
	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	protected double strongSpamThreshold;
	
	protected String weakSpamTerms;
	
	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	protected double weakSpamThreshold;

}
