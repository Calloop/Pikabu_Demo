package ru.calloop.pikabu_demo.ui.createPost.adapters;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Timer;
import java.util.TimerTask;

import ru.calloop.pikabu_demo.ui.createPost.adapters.listeners.ICreatePostListener;

public class TextAdapterListener implements TextWatcher {
    private Timer timer;
    private ICreatePostListener listener;
    private int position;
    private String contentValue;

    public void setListener(ICreatePostListener listener) {
        this.listener = listener;
    }

    public String getContentValue() {
        return contentValue;
    }

//    public int getPosition() {
//        return position;
//    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        timer = new Timer();
        int DELAY = 250;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.setContentValue(position, editable.toString());
            }
        }, DELAY);
    }
}