package com.ztzb.data.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.ztzb.data.R;

public class LoadingDialog {

    private AlertDialog alertDialog;
    private ProgressView progress;
    private TextView txtInfo;

    public void showLoading(Context context, String tip) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.darkMode);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        View view = View.inflate(context, R.layout.layout_dialog_loading_progress, null);
        alertDialog.setView(view);
        progress = view.findViewById(R.id.loading_progressview);
        txtInfo = view.findViewById(R.id.loading_message_tv);
        progress.setStrokeColors(new int[]{Color.WHITE});

        if (!TextUtils.isEmpty(tip)) {
            txtInfo.setVisibility(View.VISIBLE);
            txtInfo.setText(tip);
        } else {
            txtInfo.setVisibility(View.GONE);
        }
        alertDialog.show();
    }

    public void dismiss() {
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog.cancel();
            alertDialog = null;
        }
    }

    public boolean isShowing() {
        if (alertDialog == null) {
            return false;
        }
        return alertDialog.isShowing();
    }
}
