package com.cheng.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cheng.app.R;

public class EditTextDialog extends Dialog implements View.OnClickListener {

    protected Context mContext;

    protected WindowManager.LayoutParams mLayoutParams;

    private Button cancelBtn, confirmBtn;

    public EditText nameEdit, phoneEdit;

    public TextView contactTv;

    private OnDialogClickListener listener;

    public EditTextDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public EditTextDialog(@NonNull Context context, String name, String phone) {
        super(context);
        initView(context);
        setNameEdit(name);
        setPhoneEdit(phone);
    }

    public EditTextDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected EditTextDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(R.drawable.transparent_bg);
        mContext = context;
        Window window = this.getWindow();
        mLayoutParams = window.getAttributes();
        mLayoutParams.alpha = 1f;
        window.setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity = Gravity.CENTER;
        }

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_choose, null);
        nameEdit = dialogView.findViewById(R.id.name);
        phoneEdit = dialogView.findViewById(R.id.phone);
        contactTv = dialogView.findViewById(R.id.contact);
        confirmBtn = dialogView.findViewById(R.id.confirm);
        cancelBtn = dialogView.findViewById(R.id.cancel);
        confirmBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        contactTv.setOnClickListener(this);
//        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(dialogView);
    }

    public void setNameEdit(String message) {
        nameEdit.setText(message);
    }

    public String getNameText() {
        return nameEdit.getText().toString().trim();
    }

    public String getPhoneText() {
        return phoneEdit.getText().toString().trim();
    }

    public void setPhoneEdit(String message) {
        phoneEdit.setText(message);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.confirm:
                if (listener != null) {
                    listener.onConfirm(this);
                }
                dismiss();
                break;
            case R.id.contact:
                break;
            default:
                break;
        }

    }

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        this.listener = listener;
    }
}