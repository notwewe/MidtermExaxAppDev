package com.example.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class Batch2 extends AppCompatActivity {
    private GridLayout gridLayout;
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    private int currentColor = 0;
    private boolean gameEnded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch2);
        gridLayout = findViewById(R.id.gridLayout);
        randomizeColors();
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int index = i;
            Button button = (Button) gridLayout.getChildAt(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!gameEnded) {
                        changeColor(button, index);
                        checkWinCondition();
                    }
                }
            });
        }
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizeColors();
                gameEnded = false;
            }
        });
    }

    private static final int[] SELECTED_STATE_SET = new int[] {

            android.R.attr.state_selected
    };
    private void randomizeColors() {
        currentColor = (int) (Math.random() * 3);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setBackgroundColor(colors[getRandomColor()]);
        }
    }
    private int getRandomColor() {
        int randomColor = currentColor;
        while (randomColor == currentColor) {
            randomColor = (int) (Math.random() * 3);
        }
        return randomColor;
    }private void changeColor(Button button, int index) {
        int nextColor = getNextColor(index);
        button.setBackgroundColor(colors[nextColor]);
        changeSurroundingColors(index, nextColor);
    }
    private int getNextColor(int index) {
        int nextColor = (currentColor + 1) % 3;
        if (index % 3 == 0 && nextColor == 0) {
            nextColor = 2;
        } else if (index % 3 == 2 && nextColor == 2) {
            nextColor = 0;
        } else if (index > 5 && index % 3 == 0 && nextColor == 2) {
            nextColor = 0;
        } else if (index > 5 && index % 3 == 2 && nextColor == 0) {
            nextColor = 2;
        } else if (index > 5 && index % 3 == 1 && nextColor == 0) {
            nextColor = 1;
        } else if (index > 5 && index % 3 == 1 && nextColor == 2) {
            nextColor = 1;
        } else {
            nextColor = (currentColor + 1) % 3;
        }
        return nextColor;
    }
    private void changeSurroundingColors(int index, int nextColor) {
        if (index > 0) {
            changeColor((Button) gridLayout.getChildAt(index - 1), index - 1, nextColor);
        }
        if (index < 8) {
            changeColor((Button) gridLayout.getChildAt(index + 1), index + 1, nextColor);
        }
        if (index > 2) {
            changeColor((Button) gridLayout.getChildAt(index - 3), index - 3, nextColor);
        }
        if (index < 6) {
            changeColor((Button) gridLayout.getChildAt(index + 3), index + 3, nextColor);
        }
    }
    private void changeColor(Button button, int index, int nextColor) {button.setBackgroundColor(colors[nextColor]);
    }
    private boolean checkWinCondition() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            Drawable drawable = button.getBackground();
            if (drawable instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) drawable;
                int color = colorDrawable.getColor();
                if (color != colors[currentColor]) {
                    return false;
                }
            } else {
                return false;
            }
        }
        Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        gameEnded = true;
        return true;
    }
}
