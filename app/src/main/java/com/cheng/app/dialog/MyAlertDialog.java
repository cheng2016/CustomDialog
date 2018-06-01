package com.cheng.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cheng.app.R;

public class MyAlertDialog extends Dialog implements View.OnClickListener {

    protected Context mContext;

    private Button cancelBtn, confirmBtn;

    private TextView titleTv, contentTv;

    private OnDialogClickListener listener;
    ;

    public MyAlertDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MyAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_alert, null);
        titleTv = (TextView) dialogView.findViewById(R.id.title);
        contentTv = (TextView) dialogView.findViewById(R.id.content);
        confirmBtn = (Button) dialogView.findViewById(R.id.confirm);
        cancelBtn = (Button) dialogView.findViewById(R.id.cancel);
        confirmBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
//        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(dialogView);
    }

    public void setTitle(String message) {
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText(message);
    }

    public void setMessage(String message) {
        contentTv.setVisibility(View.VISIBLE);
        contentTv.setText(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                if (listener != null) {
                    listener.onConfirm(this);
                }
                dismiss();
                break;
            case R.id.cancel:
                if (listener != null) {
                    listener.onCancel(this);
                }
                dismiss();
                break;
            default:
                break;
        }
    }


    public void setOnDialogClickListener(OnDialogClickListener listener) {
        this.listener = listener;
    }
}
