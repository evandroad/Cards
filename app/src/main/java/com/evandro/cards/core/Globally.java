package com.evandro.cards.core;

import android.content.Context;
import android.widget.Spinner;

import com.evandro.cards.dao.CardDAO;
import com.evandro.cards.dao.DescriptionDAO;
import com.evandro.cards.dao.PersonDAO;

import java.util.ArrayList;
import java.util.List;

public class Globally {

  private static Globally instance = null;
  public static final String CARD = "card";
  public static final String CARD_HOLDER = "card_holder";
  public static final String PERSON = "person";
  public static final String DESCRIPTION = "description";

  private List<String> cards;
  private List<String> cards_holder;
  private List<String> people;
  private List<String> descriptions;
  CardDAO cardDAO;
  PersonDAO personDAO;
  DescriptionDAO descriptionDAO;

  private Globally() {}

  public static Globally getInstance() {
    if (instance == null) {
      instance = new Globally();
    }

    return instance;
  }

  public void init(Context context) {
    cardDAO = new CardDAO(context);
    personDAO = new PersonDAO(context);
    descriptionDAO = new DescriptionDAO(context);

    if (cards == null || cards.size() == 1) {
      getListFromDB(CARD, null, null);
    }

    if (cards_holder == null || cards_holder.size() == 1) {
      getListFromDB(CARD_HOLDER, null, null);
    }

    if (people == null || people.size() == 1) {
      getListFromDB(PERSON, null, null);
    }

    if (descriptions == null || descriptions.size() == 1) {
      getListFromDB(DESCRIPTION, null, null);
    }
  }

  public void getListFromDB(String operation, Spinner spinner, Context context) {
    List<String> list = new ArrayList<>();
    list.add("");

    switch (operation) {
      case CARD:
        list.addAll(cardDAO.getCards());
        break;
      case CARD_HOLDER:
        list.addAll(cardDAO.getAll());
        break;
      case PERSON:
        list.addAll(personDAO.getAll());
        break;
      case DESCRIPTION:
        list.addAll(descriptionDAO.getAll());
    }

    setList(operation, list);
    fillSpinner(operation, spinner, context);
  }

  public void setList(String operation, List<String> list) {
    switch (operation) {
      case CARD:
        this.setCards(list);
        break;
      case CARD_HOLDER:
        this.setCardsHolder(list);
        break;
      case PERSON:
        this.setPeople(list);
        break;
      case DESCRIPTION:
        this.setDescriptions(list);
    }
  }

  public void fillSpinner(String operation, Spinner spinner, Context context) {
    if (spinner == null) {
      return;
    }

    List<String> list = new ArrayList<>();

    switch (operation) {
      case CARD:
        list = getCards();
        break;
      case CARD_HOLDER:
        list = getCardsHolder();
        break;
      case PERSON:
        list = getPeople();
        break;
      case DESCRIPTION:
        list = getDescriptions();
    }

    Util.fillSpinner(context, list, spinner);
  }

  public List<String> getCards() { return cards; }
  public void setCards(List<String> cards) { this.cards = cards; }

  public List<String> getCardsHolder() { return cards_holder; }
  public void setCardsHolder(List<String> cards_holder) { this.cards_holder = cards_holder; }

  public List<String> getPeople() { return people; }
  public void setPeople(List<String> people) { this.people = people; }

  public List<String> getDescriptions() { return descriptions; }
  public void setDescriptions(List<String> descriptions) { this.descriptions = descriptions; }

}