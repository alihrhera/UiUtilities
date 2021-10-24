package com.hrhera.uiutilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;


public class ExpandRow extends CardView {

    private TextView titleView;
    private FrameLayout child;
    private ConstraintLayout tail;
    private final Context context;
    private ImageView startIconView;
    private ImageView endIconView;
    private View line;

    private int childId = -1;
    private boolean defaultOpen = false;
    private boolean clickAnyPositionToOpen = false;
    private boolean showDivider = true;

    private float titleTextSize = 14f;
    private String titleText = "Title";

    private int titleTextColor = R.color.black;
    private int endIconClose   = R.drawable.ic_close;
    private int endIconOpen    = R.drawable.ic_open;
    private int endIconColor   = R.color.black;

    private int dividerColor = R.color.black;


    private int startIcon = -1;
    private int startIconTint = -1;

    private int headerBackground = R.color.white;

    private boolean isOpen() {
        return tail.getVisibility() == VISIBLE;
    }

    private void createView() {
        inflate(context, R.layout.extented_row, this);
        titleView = findViewById(R.id.title);
        child = findViewById(R.id.childView);
        tail = findViewById(R.id.tail);
        line = findViewById(R.id.line);
        ConstraintLayout header = findViewById(R.id.header);
        startIconView = findViewById(R.id.start_icon);

        endIconView = findViewById(R.id.end_icon);
        endIconView.setOnClickListener(view -> {
            if (isOpen()) {
                tail.setVisibility(GONE);
                endIconView.setImageResource(endIconOpen);
                return;
            }
            tail.setVisibility(VISIBLE);
            endIconView.setImageResource(endIconClose);
        });
        startIconView.setVisibility(GONE);
        if (startIcon != -1) {
            startIconView.setVisibility(VISIBLE);
            startIconView.setImageResource(startIcon);
        }

        if (defaultOpen) {
            tail.setVisibility(VISIBLE);
            endIconView.setImageResource(endIconClose);
        }


        endIconView.setColorFilter(endIconColor);


        titleView.setTextSize(titleTextSize);
        titleView.setText(titleText);
        titleView.setTextColor(ContextCompat.getColor(context, titleTextColor));

        if (childId != -1) {
            View childView = inflate(context, childId, null);
            child.removeAllViews();
            child.addView(childView);

        }

        if (startIconTint != -1) {
            startIconView.setColorFilter(startIconTint);
        }

        if (headerBackground != -1) {
            header.setBackground(ContextCompat.getDrawable(context, headerBackground));
        }

        if (clickAnyPositionToOpen) {
            tail.setOnClickListener(view -> endIconView.performClick());

            header.setOnClickListener(view -> endIconView.performClick());
        }

        line.setVisibility(GONE);
        if (showDivider) {
            line.setVisibility(VISIBLE);
        }
        line.setBackgroundColor(ContextCompat.getColor(context, dividerColor));

    }

    private void createViewWithAttribute(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ExpandRow, 0, 0);
        startIcon = ta.getResourceId(R.styleable.ExpandRow_startIcon, -1);
        endIconClose = ta.getResourceId(R.styleable.ExpandRow_endIconClose, R.drawable.ic_close);
        endIconOpen = ta.getResourceId(R.styleable.ExpandRow_endIconOpen, R.drawable.ic_open);
        titleText = ta.getString(R.styleable.ExpandRow_title);
        defaultOpen = ta.getBoolean(R.styleable.ExpandRow_defaultOpen, false);
        titleTextSize = ta.getDimension(R.styleable.ExpandRow_titleTextSize, 14f);
        childId = ta.getResourceId(R.styleable.ExpandRow_child_id, -1);
        endIconOpen = ta.getResourceId(R.styleable.ExpandRow_endIconOpen, R.drawable.ic_open);
        dividerColor = ta.getResourceId(R.styleable.ExpandRow_dividerColor, R.color.black);
        titleTextColor = ta.getResourceId(R.styleable.ExpandRow_titleTextColor, R.color.black);
        startIconTint = ta.getResourceId(R.styleable.ExpandRow_startIconColor, -1);
        headerBackground = ta.getResourceId(R.styleable.ExpandRow_headerBackground, headerBackground);
        endIconColor = ta.getResourceId(R.styleable.ExpandRow_endIconColor, R.color.black);
        clickAnyPositionToOpen = ta.getBoolean(R.styleable.ExpandRow_clickAnyPositionToOpen, false);
        showDivider = ta.getBoolean(R.styleable.ExpandRow_showDivider, true);


        createView();
        ta.recycle();
    }


    public ExpandRow(@NonNull Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public ExpandRow(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createViewWithAttribute(attrs);
    }

    public ExpandRow(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        createViewWithAttribute(attrs);
    }


    /**
     * Add view that u want to hide or show
     * by send the view here
     */
    public void setChildView(View childView) {
        child.removeAllViews();
        child.addView(childView);
    }

    /**
     * set The title text
     */
    public void setTitle(String titleText) {
        this.titleText = titleText;
        titleView.setText(titleText);
    }

    /*
    set color filter to start icon
    * */
    public void setStartIcon(@DrawableRes int startIcon) {
        this.startIcon = startIcon;
        if (startIcon != 0) {
            startIconView.setVisibility(VISIBLE);
            startIconView.setImageResource(startIcon);
            return;
        }
        startIconView.setVisibility(GONE);

    }

    /*
    change arrow icon in the end of view
    by add open icon and close icon
    * */
    public void setEndOpenIcon(@DrawableRes int endIconOpen) {
        this.endIconOpen = endIconOpen;
        if (isOpen()) {
            endIconView.setImageResource(endIconOpen);
        }
    }
    /*
    change arrow icon in the end of view
    by add open icon and close icon
    * */
    public void setEndOpenClose(@DrawableRes int endIconClose) {
        this.endIconClose = endIconClose;
        if (!isOpen()) {
            endIconView.setImageResource(endIconClose);
        }
    }

    /*to set text Size of The Title */
    public void setTitleTextSize(float textSize) {
        titleTextSize = textSize;
        titleView.setTextSize(textSize);
    }

    /*to set text Color of The Title */
    public void setTitleTextColor(@ColorInt int textColor) {
        titleTextColor = textColor;
        titleView.setTextColor(textColor);
    }

    /*to set text Color of The Title */

    public void setEndIconColor(@ColorInt int endIconColor) {
        this.endIconColor = endIconColor;
        endIconView.setColorFilter(endIconColor);
    }

    /*
       set color filter to end icon
       * */
    public void setStartIconColor(int startIconColor) {
        this.startIconTint = startIconColor;
        startIconView.setColorFilter(startIconColor);
    }

    /*
       set show Divider line or not
       * */
    public void showDivider(boolean showDivider) {
        this.showDivider = showDivider;
        line.setVisibility(GONE);
        if (showDivider) {
            line.setVisibility(VISIBLE);
        }
    }

    /*
       set  Divider line Color
       * */
    public void setDividerColor(@ColorInt int dividerColor) {
        this.dividerColor = dividerColor;
        line.setBackgroundColor(dividerColor);
    }

    /*
     return the title view
     * */
    public TextView getTitleView() {
        return titleView;
    }

    /**
     * return the child id
     * */
    public int childId() {
        return childId;
    }

    /**
     * return the child view
     * */
    public View child() {
        return child.getChildAt(0);
    }


}
