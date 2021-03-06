/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Useful links:
// https://android.googlesource.com/platform/frameworks/base/+/de47f1c358c8186ff3e14b887d5869f69b9a9d6c/core/java/com/android/internal/widget/DialogTitle.java
// com.android.internal.widget.DialogTitle: https://android.googlesource.com/platform/frameworks/base/+/master/core/res/res/layout/alert_dialog.xml
// https://android.googlesource.com/platform/frameworks/base.git/+/master/core/java/com/android/internal/app/AlertController.java
// <declare-styleable name="TextAppearance">: https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/res/res/values/attrs.xml


package com.liskovsoft.inputchooser.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.liskovsoft.leankeykeyboard.R;

/**
 * Used by dialogs to change the font size and number of lines to try to fit
 * the text to the available space.
 */
public class DialogTitle extends android.support.v7.widget.AppCompatTextView {

    public DialogTitle(Context context, AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);
    }
    public DialogTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public DialogTitle(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final Layout layout = getLayout();
        if (layout != null) {
            final int lineCount = layout.getLineCount();
            if (lineCount > 0) {
                final int ellipsisCount = layout.getEllipsisCount(lineCount - 1);
                if (ellipsisCount > 0) {
                    setSingleLine(false);

                    TypedArray a = getContext().obtainStyledAttributes(null,
                            R.styleable.TextAppearance,
                            android.R.attr.textAppearanceMedium,
                            android.R.style.TextAppearance_Medium);
                    final int textSize = a.getDimensionPixelSize(
                                R.styleable.TextAppearance_textSize,
                            (int) (20 * getResources().getDisplayMetrics().density));
                    final int textColor = a.getColor(
                            R.styleable.TextAppearance_textColor, 0xffffffff);
                    // textSize is already expressed in pixels
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    setTextColor(textColor);
                    setMaxLines(2);
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
            }
        }
    }
}
