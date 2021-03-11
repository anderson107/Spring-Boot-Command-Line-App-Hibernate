package com.alpha.entities.card;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.alpha.entities.customer.Customer;

@MappedSuperclass
public abstract class Card {

	// private fields
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "card_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "expire_date")
	private LocalDate expireDate;

	@Column(name = "card_code")
	private String cardCode;

	
	// constructors
	public Card() {

	}

	// getters and setters
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {

		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null");
		}
		this.customer = customer;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {

		if (!(cardNumber.length() == 16)) {
			throw new IllegalArgumentException("Card Number must be 16 digit number");
		}
		this.cardNumber = cardNumber;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {

		if (expireDate.isBefore(LocalDate.now().plusDays(1))) {
			throw new IllegalArgumentException("Invalid expire date, please enter a new one");
		}

		this.expireDate = expireDate;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {

		if (!(cardCode.length() == 3)) {
			throw new IllegalArgumentException("Card code must be 3 digit number");
		}
		this.cardCode = cardCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
