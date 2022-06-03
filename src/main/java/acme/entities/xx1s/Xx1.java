package acme.entities.xx1s;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.items.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Xx1 extends AbstractEntity{
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes  
	
	@NotBlank
	@Column(unique = true) //XXP
	@Pattern(regexp = "^\\d{2}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01]):[0-9]{4}$", message = "The code must be the current date XXP (yy-mm-dd) followed by a \":\" and four numbers")
	protected String code; //Code
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	protected Date xx2; // Creation Moment
	
	@NotBlank
	@Length(min = 1, max=100)
	protected String xx3; // Title
	
	@NotBlank
	@Length(min = 1, max=255)
	protected String xx4; // Description
	
	@NotNull
	protected Date xx51; //Start of the period
	
	@NotNull
	protected Date xx52; // End of the period (at least one month ahead and one week long)
	
	@Valid
	@NotNull
	protected Money xx6; // Budget positive
	
	@URL
	protected String xx7; // Optional Link
	
	@OneToOne(optional = true)
    @Valid
    @NotNull
    protected Item item; // Sera component o tool
 
}
