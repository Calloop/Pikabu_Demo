package ru.calloop.pikabu_demo.adapters.createPost.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Timer;
import java.util.TimerTask;

public class TextAdapterListener implements TextWatcher {
    private Timer timer;
    private ICreatePostListener listener;
    private int position;

    public void setListener(ICreatePostListener listener) {
        this.listener = listener;
    }

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