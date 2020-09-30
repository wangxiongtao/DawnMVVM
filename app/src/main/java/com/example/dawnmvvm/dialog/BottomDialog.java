package com.example.dawnmvvm.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomDialog extends BottomSheetDialog {
    public BottomDialog(@NonNull Context context) {
        super(context);
    }

    public BottomDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }
}
