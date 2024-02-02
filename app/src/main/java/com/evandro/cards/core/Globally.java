package com.evandro.cards.core;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.evandro.cards.R;
import com.evandro.cards.dao.CardDAO;
import com.evandro.cards.dao.PersonDAO;

import java.util.ArrayList;
import java.util.List;

public class Globally {

  private static Globally instance = null;
  private static final String CARD = "card";
  private static final String PERSON = "person";

  private List<String> cards;
  private List<String> people;
  CardDAO cardDAO;
  PersonDAO personDAO;

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

    if (cards == null || cards.size() == 1) {
      getListFromDB(CARD, null, null);
    }

    if (people == null || people.size() == 1) {
      getListFromDB(PERSON, null, null);
    }
  }

  public void getListFromDB(String operation, Spinner spinner, Context context) {
    List<String> list = new ArrayList<>();
    list.add("");

    switch (operation) {
      case CARD:
        list.addAll(cardDAO.getAll());
        break;
      case PERSON:
        list.addAll(personDAO.getAll());
    }

    setList(operation, list);
    fillSpinner(operation, spinner, context);
  }

  public List<String> getList(String operation) {
    switch (operation) {
      case CARD:
        return this.getCards();
      case PERSON:
        return this.getPeople();
      default:
        return null;
    }
  }

  public void setList(String operation, List<String> list) {
    switch (operation) {
      case CARD:
        this.setCards(list);
        break;
      case PERSON:
        this.setPeople(list);
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
      case PERSON:
        list = getPeople();
    }

    ArrayAdapter<String> adapter = new ArrayAdapter<>(
      context,
      R.layout.item_spinner,
      R.id.txtSpinner,
      list
    );

    spinner.setAdapter(adapter);
  }

  public List<String> getCards() { return cards; }
  public void setCards(List<String> cards) { this.cards = cards; }

  public List<String> getPeople() { return people; }
  public void setPeople(List<String> people) { this.people = people; }

}