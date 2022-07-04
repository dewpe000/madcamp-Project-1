package com.example.project1.Tab3.Lottery;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.project1.R;

import java.util.Objects;

public class LotteryDialog extends Dialog {

    private TextView lotteryText;
    private Button checkBtn;
    private Context context;
    String lotteryVal;

    public LotteryDialog(@NonNull Context context, String lotteryVal) {
        super(context);
        this.context = context;
        this.lotteryVal = lotteryVal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_lottery);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        checkBtn = (Button) findViewById(R.id.checkBtn);
        lotteryText = (TextView) findViewById(R.id.lotteryText);
        lotteryText.setText(lotteryVal);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
