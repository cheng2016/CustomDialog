package com.cheng.app.dialog;

import android.app.Dialog;

/**
 * dialog 点击事件监听接口
 */
public interface OnDialogClickListener {
    void onConfirm(Dialog dialog);

    void onCancel(Dialog dialog);
}
