package com.cheng.app.dialog;


import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.cheng.app.R;

public class MyAlertDialog {
	private Context context;
	private Dialog dialog;
	private LinearLayout lLayout_bg;
	private TextView txt_title;
	private TextView txt_msg;
	private EditText edittxt_result;
	private LinearLayout dialog_Group;
	private ImageView dialog_marBottom;
	private Button btn_neg;
	private Button btn_pos;
	private ImageView img_line;
	private Display display;
	private boolean showTitle = false;
	private boolean showMsg = false;
	private boolean showEditText = false;
	private boolean showLayout = false;
	private boolean showPosBtn = false;
	private boolean showNegBtn = false;
	private int winWidth=0;//屏幕宽度
	private float density=0.0f;//屏幕密度

	public MyAlertDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        density = dm.density;
        winWidth = windowManager.getDefaultDisplay().getWidth();
	}

	public MyAlertDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				R.layout.toast_view_alertdialog, null);

		// 获取自定义Dialog布局中的控件
		lLayout_bg = view.findViewById(R.id.lLayout_bg);
		txt_title = view.findViewById(R.id.txt_title);
		txt_title.setVisibility(View.GONE);
		txt_msg = view.findViewById(R.id.txt_msg);
		txt_msg.setVisibility(View.GONE);
		edittxt_result = view.findViewById(R.id.edittxt_result);
		edittxt_result.setVisibility(View.GONE);
		dialog_Group = view.findViewById(R.id.dialog_Group);
		dialog_Group.setVisibility(View.GONE);
		dialog_marBottom = view.findViewById(R.id.dialog_marBottom);
		btn_neg = view.findViewById(R.id.btn_neg);
		btn_neg.setVisibility(View.GONE);
		btn_pos = view.findViewById(R.id.btn_pos);
		btn_pos.setVisibility(View.GONE);
		img_line = view.findViewById(R.id.img_line);
		img_line.setVisibility(View.GONE);

		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.AlertDialogStyle);
		dialog.setContentView(view);

		// 调整dialog背景大小
		lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
				.getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

		return this;
	}

	public MyAlertDialog setTitle(String title) {
		showTitle = true;
		if ("".equals(title)) {
			txt_title.setText("标题");
		} else {
			txt_title.setText(title);
		}
		return this;
	}

	public MyAlertDialog setEditText(String msg) {
		showEditText = true;
		if ("".equals(msg)) {
			edittxt_result.setHint("内容");
		} else {
			edittxt_result.setText(msg);
		}
		return this;
	}

	public String getResult() {
		return edittxt_result.getText().toString();
	}

	public MyAlertDialog setMsg(String msg) {
		showMsg = true;
		if ("".equals(msg)) {
			txt_msg.setText("内容");
		} else {
			txt_msg.setText(msg);
		}
		return this;
	}

	public MyAlertDialog setView(View view) {
		showLayout = true;
		if (view == null) {
			showLayout = false;
		} else
			dialog_Group.addView(view,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		return this;
	}

	public MyAlertDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}
	
	public MyAlertDialog setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	public MyAlertDialog setPositiveButton(String text,
			final OnClickListener listener) {
		showPosBtn = true;
		if ("".equals(text)) {
			btn_pos.setText("确定");
		} else {
			btn_pos.setText(text);
		}
		btn_pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public MyAlertDialog setNegativeButton(String text,
			final OnClickListener listener) {
		showNegBtn = true;
		if ("".equals(text)) {
			btn_neg.setText("取消");
		} else {
			btn_neg.setText(text);
		}
		btn_neg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	private void setLayout() {
		if (!showTitle && !showMsg) {
			txt_title.setText("提示");
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showTitle) {
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showEditText) {
			edittxt_result.setVisibility(View.VISIBLE);
		}

		if (showMsg) {
			txt_msg.setVisibility(View.VISIBLE);
		}

		if (showLayout) {
			dialog_Group.setVisibility(View.VISIBLE);
			dialog_marBottom.setVisibility(View.GONE);
		}
		if(showTitle && !showMsg && !showLayout && !showEditText){
			LayoutParams linearParams = (LayoutParams) txt_title.getLayoutParams();
			linearParams.height = (int)(65*density);// 强制设控件的高
			linearParams.width = winWidth-(int)(30*density);// 强制设控件的宽  
			txt_title.setLayoutParams(linearParams);
		}

		if (!showPosBtn && !showNegBtn) {
			btn_pos.setText("确定");
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
			btn_pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}

		if (showPosBtn && showNegBtn) {
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setBackgroundResource(R.drawable.dialog_btn_right);
			btn_neg.setVisibility(View.VISIBLE);
			btn_neg.setBackgroundResource(R.drawable.dialog_btn_left);
			img_line.setVisibility(View.VISIBLE);
		}

		if (showPosBtn && !showNegBtn) {
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
		}

		if (!showPosBtn && showNegBtn) {
			btn_neg.setVisibility(View.VISIBLE);
			btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
		}
	}

	public MyAlertDialog show() {
		setLayout();
		dialog.show();
		return this;
	}
}
