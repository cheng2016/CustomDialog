package com.cheng.app.dialog;

import android.app.Dialog;
import android.content.res.Configuration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * @ClassName SimpleDialog
 * @Description TODO
 * @Author chengzj
 * @Date 2021/7/15 15:33
 */
public class SimpleDialog extends Dialog {
    private Context context;
    private TextView titleTv;
    private TextView messageTv;

    private Button confrimBtn;
    private Button cancelBtn;

    private boolean showTitle = false;
    private boolean showMessage = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public SimpleDialog(@NonNull Context context) {
        this(context, getStyleId(context, "HYGame_base_fragment_pop"));
    }

    public SimpleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        setContentView(getLayoutId(context, "hygame_float_alert_dialog"));
        setCanceledOnTouchOutside(false);
        Utils.setAlertDialogWindow(this);
        initView();
    }
  
    public static void setAlertDialogWindow(Dialog dialog) {
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        Display display = window.getWindowManager().getDefaultDisplay();
        Configuration cf = dialog.getContext().getResources().getConfiguration();
        int ori = cf.orientation;
        if (ori == cf.ORIENTATION_LANDSCAPE) {
            lp.width = (int) (display.getWidth() * 0.38);
            lp.height = (int) (display.getHeight() * 0.76);
            if (HYGame_FloatMainFragment.ISRIGHT) {
                window.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
            } else {
                window.setGravity(Gravity.LEFT | Gravity.BOTTOM);
            }
            lp.x = display.getWidth() / 10;
            window.getDecorView().setPadding(0, 0, 0, 10);
        } else if (ori == cf.ORIENTATION_PORTRAIT) {
            lp.width = (int) (display.getWidth() * 0.9);
        }
        Log.i("HY", "width : " + lp.width + " , height : " + lp.height + " display width : " + display.getWidth());
        window.setAttributes(lp);
    }

    void initView() {
        titleTv = findViewById(getId(context, "hy_title"));
        messageTv = findViewById(getId(context, "hy_message"));
        confrimBtn = findViewById(getId(context, "hy_confirm_btn"));
        cancelBtn = findViewById(getId(context, "hy_cancel_btn"));
    }

    public SimpleDialog setTitle(String title) {
        showTitle = true;
        titleTv.setText(TextUtils.isEmpty(title) ? "" : title);
        return this;
    }

    public SimpleDialog setMessage(String msg) {
        showMessage = true;
        messageTv.setText(TextUtils.isEmpty(msg) ? "" : msg);
        return this;
    }

    public SimpleDialog setDefaultGrivity(){
        getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.x = 0;
        lp.height = (int) (getWindow().getWindowManager().getDefaultDisplay().getHeight() * 0.5);
        getWindow().setAttributes(lp);
        return this;
    }

    public SimpleDialog setConfirmButton(String text,
                                               final View.OnClickListener listener) {
        showPosBtn = true;
        confrimBtn.setText(TextUtils.isEmpty(text) ? "确定" : text);
        confrimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }


    public SimpleDialog setCancelButton(String text,
                                              final View.OnClickListener listener) {
        showNegBtn = true;
        cancelBtn.setText(TextUtils.isEmpty(text) ? "取消" : text);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    @Override
    public void show() {
        super.show();
        titleTv.setVisibility(showTitle ? View.VISIBLE : View.GONE);
        messageTv.setVisibility(showMessage ? View.VISIBLE : View.GONE);
        confrimBtn.setVisibility(showPosBtn ? View.VISIBLE : View.GONE);
        cancelBtn.setVisibility(showNegBtn ? View.VISIBLE : View.GONE);
    }
    
   public static int getLayoutId(Context paramActivity, String id) {
        String packageName = paramActivity.getPackageName();
        return paramActivity.getResources().getIdentifier(id, "layout",
                packageName);
    }
	
   public static int getStyleId(Context paramActivity, String id) {
        String packageName = paramActivity.getPackageName();
        return paramActivity.getResources().getIdentifier(id, "style",
                packageName);
    }

   public static int getId(Context paramActivity, String id) {
		String packageName = paramActivity.getPackageName();
		return paramActivity.getResources()
				.getIdentifier(id, "id", packageName);
   }
}
