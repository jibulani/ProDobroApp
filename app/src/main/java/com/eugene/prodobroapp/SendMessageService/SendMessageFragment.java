package com.eugene.prodobroapp.SendMessageService;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.prodobroapp.R;

/**
 * Created by eugene on 29.09.18.
 */

public class SendMessageFragment extends Fragment {

    public static SendMessageFragment newInstance() {

        Bundle args = new Bundle();

        SendMessageFragment fragment = new SendMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.send_message_layout, container, false);
    }
}
