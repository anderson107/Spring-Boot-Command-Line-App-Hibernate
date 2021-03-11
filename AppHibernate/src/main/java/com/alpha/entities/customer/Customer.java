package com.alpha.entities.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alpha.entities.card.AmericanExpress;
import com.alpha.entities.card.Card;
import com.alpha.entities.card.Mastercard;
import com.alpha.entities.card.Visa;

@SuppressWarnings("serial")
@Entity
@Table(name="customer")
@NamedQueries({
	@NamedQuery(name = "Customer_ById",
			query="from Customer where customer_id = :customer_id"),
})
public class Customer implements Serializable {

	// private fields
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CUSTOMER_ID")
	private int id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
	private String email;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AmericanExpress> card1 = new HashSet<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Mastercard> card2 = new HashSet<>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Visa> card3 = new HashSet<>();
	

	// constructors
	public Customer() {

	}

	public Customer(int id, String firstName, String lastName, String address, String email) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	// Argument must match only letters
	public void setFirstName(String firstName) {

		if (!firstName.matches("^[a-zA-Z]+$")) {
			throw new IllegalArgumentException("Please, enter only letters for first name");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	// Argument must match only letters
	public void setLastName(String lastName) {

		if (!lastName.matches("^[a-zA-Z]+$")) {
			throw new IllegalArgumentException("Please, enter only letters for last name");
		}

		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	// Argument cannot be null or empty
	public void setAddress(String address) {

		if (address == null || address.equals("")) {
			throw new IllegalArgumentException("Please, enter a valid address");
		}

		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	// Argument must be email format
	public void setEmail(String email) {

		if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
			throw new IllegalArgumentException("Please, enter a valid email address e.g email@email.com");
		}
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<AmericanExpress> getCard1() {
		return card1;
	}

	public void setCard1(Set<AmericanExpress> card1) {
		this.card1 = card1;
	}

	public Set<Mastercard> getCard2() {
		return card2;
	}

	public void setCard2(Set<Mastercard> card2) {
		this.card2 = card2;
	}

	public Set<Visa> getCard3() {
		return card3;
	}

	public void setCard3(Set<Visa> card3) {
		this.card3 = card3;
	}

	// to string method
	@Override
	public String toString() {
		return "NAME: " + firstName + "\nSURNAME: " + lastName + "\nADDRESS: " + address + " \nEMAIL: " + email;
	}

}
