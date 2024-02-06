package com.evandro.cards.core;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class MoneyTextWatcher implements TextWatcher {

  private final WeakReference<EditText> editTextWeakReference;
  private final Locale locale = new Locale("pt", "BR");

  public MoneyTextWatcher(EditText editText) { this.editTextWeakReference = new WeakReference<>(editText); }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {}

  @Override
  public void afterTextChanged(Editable e) {
    EditText editText = editTextWeakReference.get();

    if (editText == null || e.toString().isEmpty()) {
      return;
    }

    editText.removeTextChangedListener(this);
    String value = e.toString().replaceAll("[^0-9-]", "");

    if (value.isEmpty()) {
      editText.setText("");
      editText.setSelection(0);
      editText.addTextChangedListener(this);
      return;
    }

    if (value.equals("--")) {
      value = Util.removeLastChar(value);
    }

    if (value.length() > 1 && value.endsWith("-") && !value.startsWith("-")) {
      value = Util.removeLastChar(value);
      value = "-" + value;
    }

    if (value.length() > 1 && value.endsWith("-")) {
      value = Util.removeLastChar(value);
      value = Util.removeStart(value, 1);
    }

    String formatted = value;
    if (!value.equals("-")) {
      BigDecimal parsed = parseToBigDecimal(value, locale);
      formatted = NumberFormat.getCurrencyInstance(locale).format(parsed);
    }

    if (formatted.length() > 1 && formatted.startsWith("-")) {
      formatted = formatted.charAt(0) + formatted.substring(4);
    }

    if (formatted.length() > 1 && !formatted.startsWith("-")) {
      formatted = formatted.substring(3);
    }

    editText.setText(formatted);
    editText.setSelection(formatted.length());
    editText.addTextChangedListener(this);
  }

  private BigDecimal parseToBigDecimal(String value, Locale locale) {
    String replaceable = String.format("[%s,.\\s]", Objects.requireNonNull(NumberFormat.getCurrencyInstance(locale).getCurrency()).getSymbol());
    String cleanString = value.replaceAll(replaceable, "");

    boolean isNegative = false;
    if (cleanString.startsWith("-")) {
      isNegative = true;
      cleanString = cleanString.substring(1);
    }

    BigDecimal parsed = new BigDecimal(cleanString).setScale(2, RoundingMode.FLOOR)
        .divide(new BigDecimal(100), RoundingMode.FLOOR);

    return isNegative ? parsed.negate() : parsed;
  }

}