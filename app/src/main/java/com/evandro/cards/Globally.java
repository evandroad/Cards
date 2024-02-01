package com.evandro.cards;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Globally {

  private static Globally instance = null;

  private List<String> cards;
  private List<String> people;
  CardDAO cardDAO;

  private Globally() {}

  public static Globally getInstance() {
    if (instance == null) {
      instance = new Globally();
    }

    return instance;
  }

  public void init(Context context) {
    cardDAO = new CardDAO(context);

    if (cards == null || cards.size() == 1) {
      getListFromDB("cards", null, null);
    }

    if (people == null || people.size() == 1) {
      getListFromDB("people", null, null);
    }
  }

  public void getListFromDB(String operation, Spinner spinner, Context context) {
    List<String> list = new ArrayList<>();
    list.add("");
    list.addAll(cardDAO.getAll());
    setList(operation, list);
    fillSpinner(operation, spinner, context);
  }

  public List<String> getList(String operation) {
    switch (operation) {
      case "cards":
        return this.getCards();
      case "person":
        return this.getPeople();
      default:
        return null;
    }
  }

  public void setList(String operation, List<String> list) {
    switch (operation) {
      case "cards":
        this.setCards(list);
        break;
      case "people":
        this.setPeople(list);
    }
  }

  public void fillSpinner(String operation, Spinner spinner, Context context) {
    if (spinner == null) {
      return;
    }

    List<String> list = new ArrayList<>();

    switch (operation) {
      case "category":
        list = getCards();
        break;
      case "person":
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
