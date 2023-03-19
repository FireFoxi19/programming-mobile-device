package com.example.mob_lab2;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ThreeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);

        TableLayout table = new TableLayout(this);

        table.setBackgroundColor(Color.CYAN);

        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        TableRow rowDayLabels = new TableRow(this);
        TableRow rowHighs = new TableRow(this);
        TableRow rowLows = new TableRow(this);
        TableRow rowConditions = new TableRow(this);
        rowConditions.setGravity(Gravity.CENTER);

        TextView empty = new TextView(this);

        // title column/row
        TextView title = new TextView(this);
        title.setText("Weather Table");

        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 6;

        rowTitle.addView(title, params);

        // labels column
        TextView highsLabel = new TextView(this);
        highsLabel.setText("Day High");
        highsLabel.setTypeface(Typeface.DEFAULT_BOLD);

        TextView lowsLabel = new TextView(this);
        lowsLabel.setText("Day Low");
        lowsLabel.setTypeface(Typeface.DEFAULT_BOLD);

        TextView conditionsLabel = new TextView(this);
        conditionsLabel.setText("Conditions");
        conditionsLabel.setTypeface(Typeface.DEFAULT_BOLD);

        rowDayLabels.addView(empty);
        rowHighs.addView(highsLabel);
        rowLows.addView(lowsLabel);
        rowConditions.addView(conditionsLabel);

        // day 1 column
        TextView day1Label = new TextView(this);
        day1Label.setText("Feb 16");
        day1Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day1High = new TextView(this);
        day1High.setText("-12°C");
        day1High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day1Low = new TextView(this);
        day1Low.setText("-24°C");
        day1Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day1Conditions = new ImageView(this);
        day1Conditions.setImageResource(R.drawable.cloudy);

        rowDayLabels.addView(day1Label);
        rowHighs.addView(day1High);
        rowLows.addView(day1Low);
        rowConditions.addView(day1Conditions);

        // day2 column
        TextView day2Label = new TextView(this);
        day2Label.setText("Feb 17");
        day2Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day2High = new TextView(this);
        day2High.setText("-13°C");
        day2High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day2Low = new TextView(this);
        day2Low.setText("-23°C");
        day2Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day2Conditions = new ImageView(this);
        day2Conditions.setImageResource(R.drawable.sun);

        rowDayLabels.addView(day2Label);
        rowHighs.addView(day2High);
        rowLows.addView(day2Low);
        rowConditions.addView(day2Conditions);

        // day3 column
        TextView day3Label = new TextView(this);
        day3Label.setText("Feb 18");
        day3Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day3High = new TextView(this);
        day3High.setText("-5°C");
        day3High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day3Low = new TextView(this);
        day3Low.setText("-10°C");
        day3Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day3Conditions = new ImageView(this);
        day3Conditions.setImageResource(R.drawable.sun);

        rowDayLabels.addView(day3Label);
        rowHighs.addView(day3High);
        rowLows.addView(day3Low);
        rowConditions.addView(day3Conditions);

        // day4 column
        TextView day4Label = new TextView(this);
        day4Label.setText("Feb 10");
        day4Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day4High = new TextView(this);
        day4High.setText("-5°C");
        day4High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day4Low = new TextView(this);
        day4Low.setText("-9°C");
        day4Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day4Conditions = new ImageView(this);
        day4Conditions.setImageResource(R.drawable.cloudy);

        rowDayLabels.addView(day4Label);
        rowHighs.addView(day4High);
        rowLows.addView(day4Low);
        rowConditions.addView(day4Conditions);

        // day5 column
        TextView day5Label = new TextView(this);
        day5Label.setText("Feb 20");
        day5Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TextView day5High = new TextView(this);
        day5High.setText("-4°C");
        day5High.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView day5Low = new TextView(this);
        day5Low.setText("-7°C");
        day5Low.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView day5Conditions = new ImageView(this);
        day5Conditions.setImageResource(R.drawable.cloud);

        rowDayLabels.addView(day5Label);
        rowHighs.addView(day5High);
        rowLows.addView(day5Low);
        rowConditions.addView(day5Conditions);

        table.addView(rowTitle);
        table.addView(rowDayLabels);
        table.addView(rowHighs);
        table.addView(rowLows);
        table.addView(rowConditions);

        setContentView(table);
    }
}