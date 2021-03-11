package com.alpha.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.entities.card.Card;

@Transactional
@Repository("cardDao")
public class CardDaoImpl implements CardDao {

	// fields
	private SessionFactory sessionFactory;
	private static Logger logger = LoggerFactory.getLogger(CardDaoImpl.class);
	
	//constructors
	public CardDaoImpl() {
		
	}

	@Resource(name = "sessionFactory")
	@Override
	public void setSessionFactory(SessionFactory factory) {
		this.sessionFactory = factory;
		
	}
	
	//CRUD methods
	//returns all cards in the DB
	@Override
	public List<Card> findAll() {
		
		
//		String query = "select c.customer_id, c.first_name, c.last_name, c.email,"
//				+ " c.address, s.card_id, s.customer_id, s.card_number, s.card_code,"
//				+ " s.expire_date from customer c left join card s on c.customer_id = s.customer_id";
		return sessionFactory.getCurrentSession().createQuery("from Card").list();
	}

	// it returns card by id
	@Override
	public Card findCard(int id) {
		
		String query = "select c.customer_id, c.first_name, c.last_name, c.email,"
				+ " c.address, s.card_id, s.customer_id, s.card_number, s.card_code,"
				+ " s.expire_date from customer c left join card s on c.customer_id = s.customer_id where s.card_id =" + id;
		return null;
	}

	// it add a card to the db
	@Override
	public void insert(Card card) {
		String query = "insert into card(customer_id, card_number, card_code, expire_date) values (?, ?, ?, ?)";

	}

	// it updates card code and expire date
	@Override
	public void update(Card card) {
		String query = "update card SET card_number = ?, card_code =?, expire_date=? where card_id=?";
		
	}

	// it deletes a card by id
	@Override
	public void delete(int id) {
		String query = "delete from card where card_id=?";
		
	}



	


}
