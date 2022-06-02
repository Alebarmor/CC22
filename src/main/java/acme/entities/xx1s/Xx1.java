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
	@Pattern(regexp = "^\\d{2}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01]):[0-9]{4}$", message = "inventor.xx1.form.error.code-regex")
	/*
	Este code es la fechade creación (yy-mm-dd) y ":0000" cuatro numeros
	Se ha puesto esto, porque la fecha ira si o si en el pattern, y es obvio que
	tendra que ir tambien algun tipo de secuencia numerica por si se quieren
	crear mas XX1 en un mismo dia. Si se pide que los años se pongan con
	4 digitos, cambiad el d{2} del principio por un d{4}. Si se tiene que cambiar
	el orden de la fecha o de lo que sea, basta con mover los campos y
	cambiarlos de sitio (vienen separados por un guion, excepto los 4
	numero del final, que vienen separados por un ':'). En el caso de que
	en vez de numeros (lo que esta al final) tengais que poner letras,
	mirad el code de los patronage, que son tres letras (creo que en
	mayuscula), un guion, tres numero, y, opcionalmente, un guion y
	una letra (tambien en mayuscula). No solo puede que la secuencia
	numerica sean 4 numeros, sino que puede que sea como patronage: una
	secuencia que se incremente automaticamente con cada xx1 que salga.
	En ese caso, mirar el caso de patronage report, sobretodo el create service,
	donde se asigna automaticamente una secuencia, pero adaptandola a lo nuestro.
	p.d. no pongo tildes que se buguea xd
	*/
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
