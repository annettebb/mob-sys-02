package com.example.lab02;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private TextView gestureText;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gestureText = findViewById(R.id.gestureText);

        gestureDetector = new GestureDetector(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        findViewById(R.id.main).setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
        findViewById(R.id.infoButton).setOnClickListener(v -> showAuthorDialog());
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gestureText.setText("Жест: Нажатие");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gestureText.setText("Жест: Долгое нажатие");
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText("Жест: Прокрутка");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureText.setText("Жест: Долгое нажатие");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gestureText.setText("Жест: Быстрое движение");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gestureText.setText("Жест: Двойное нажатие");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        gestureText.setText("Жест: Двойное нажатие");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureText.setText("Жест: Одиночное нажатие");
        return true;
    }

    private void showAuthorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("О разработчике")
                .setMessage("Будник Анна Андреевна")
                .setPositiveButton("ОК", (dialog, which) -> dialog.dismiss())
                .show();
    }
}