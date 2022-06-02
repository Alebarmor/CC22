package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.Status;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity{
	
	//Serialisation indentifier --------------------------------------------------------------- 
	
	protected static final long serialVersionUID = 1L;

	//Attributes --------------------------------------------------------------------------------
	
	@NotNull
	protected Status status;
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique = true)
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String legalStuff;
	
	@Valid
	@NotNull
	protected Money budget;
	
	@NotNull
	protected Date startPeriod;
	
	@NotNull
	protected Date endPeriod;
	
	@URL
	protected String link;
	
	protected boolean notPublished;
	
	@NotNull
	protected Date creationDate;
	
	//Relationships -----------------------------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	Patron patron; 
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	Inventor inventor;
	
}
