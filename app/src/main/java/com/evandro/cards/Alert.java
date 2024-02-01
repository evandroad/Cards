package com.evandro.cards;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

public class Alert {

  private static final int TITLE_SUCCESS = R.string.alertTitle_success;
  private static final int TITLE_ERROR = R.string.alertTitle_error;
  private static final int TITLE_INFO = R.string.alertTitle_info;

  private static final int ICON_SUCCESS = R.drawable.success;
  private static final int ICON_ERROR = R.drawable.error;
  private static final int ICON_INFO = R.drawable.info;

  public static AlertDialog.Builder baseAlert(Context context, int title, int icon, String msg) {
    AlertDialog.Builder _dialog = new AlertDialog.Builder(context);
    _dialog.setTitle(title);
    _dialog.setIcon(icon);
    _dialog.setMessage(msg);
    _dialog.setPositiveButton(R.string.btn_ok, (dialog, which) -> {});
    return _dialog;
  }

  public static AlertDialog.Builder alertSuccess(Context context, String msg) {
    return baseAlert(context, TITLE_SUCCESS, ICON_SUCCESS, msg);
  }

  public static AlertDialog.Builder alertInfo(Context context, String msg) {
    return baseAlert(context, TITLE_INFO, ICON_INFO, msg);
  }

  public static AlertDialog.Builder alertError(Context context, String msg) {
    return baseAlert(context, TITLE_ERROR, ICON_ERROR, msg);
  }

  public static ProgressDialog alertLoading(Context context) {
    ProgressDialog _progress = new ProgressDialog(context);
    _progress.setIcon(ICON_INFO);
    _progress.setTitle(TITLE_INFO);
    _progress.setMessage("Aguarde...");
    _progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    _progress.setCancelable(false);
    return _progress;
  }

}