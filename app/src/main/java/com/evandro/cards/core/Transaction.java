package com.evandro.cards.core;

import java.util.Date;

public class Transaction {

  private int id;
  private String person;
  private String description;
  private String value;
  private String installment;
  private String holder;
  private String card;
  private Date date;

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getPerson() { return person; }
  public void setPerson(String person) { this.person = person; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public String getValue() { return value; }
  public void setValue(String value) { this.value = value; }

  public String getInstallment() { return installment; }
  public void setInstallment(String installment) { this.installment = installment; }

  public String getHolder() { return holder; }
  public void setHolder(String holder) { this.holder = holder; }

  public String getCard() { return card; }
  public void setCard(String card) { this.card = card; }

  public Date getDate() { return date; }
  public void setDate(Date date) { this.date = date; }

}