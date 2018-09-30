package com.eugene.prodobroapp.SendMessageService;

import android.arch.persistence.room.util.StringUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eugene.prodobroapp.App;
import com.eugene.prodobroapp.R;
import com.eugene.prodobroapp.data.model.LastMessage;
import com.eugene.prodobroapp.data.model.Message;
import com.eugene.prodobroapp.data.source.local.AppDatabase;
import com.eugene.prodobroapp.data.source.remote.MessageServer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eugene on 29.09.18.
 */

public class SendMessageFragment extends Fragment {

    private static final AppDatabase appDatabase = App.getInstance().getAppDatabase();
    private static final MessageServer messageServer = App.getInstance().getMessageServer();

    private ImageView noInternetImageView;
    private TextView noInternetTextView;

    public static SendMessageFragment newInstance() {

        Bundle args = new Bundle();

        SendMessageFragment fragment = new SendMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.send_message_layout, container, false);
        noInternetImageView = (ImageView) view.findViewById(R.id.no_internet_image_view);
        noInternetTextView = (TextView) view.findViewById(R.id.no_internet_text_view);
        Button button = (Button) view.findViewById(R.id.button_chatbox_send);
        EditText editText = (EditText) view.findViewById(R.id.edittext_chatbox);
        button.setOnClickListener(l -> {
            String messageString = editText.getText().toString();
            if (!messageString.isEmpty()) {
                editText.getText().clear();
                ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.item_message_sent, container, false);
                TextView textView = (TextView) constraintLayout.getViewById(R.id.text_message_body);
                textView.setText(messageString);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.message_layout);
                linearLayout.addView(constraintLayout);
                Message message = new Message(messageString);

                Single.fromCallable(() -> insertLastMessage(message.getId()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(res -> {
                            messageServer.sendMessage(message)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(sendRes -> System.out.println("ok"),
                                            e -> System.out.println("error")); //TODO add logging
                                },
                                e -> {
                                    //TODO add logic for error situation
                                });


            }
        });

        FloatingActionButton updateButton = (FloatingActionButton) view.findViewById(R.id.update_button);
        updateButton.setOnClickListener(l -> {
            Single.fromCallable(this::getLastMessage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(lastMessage -> {
                        messageServer.getRemoteAnswer(lastMessage.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(remoteAnswer -> {
                                    Single.fromCallable(() -> deleteLastMessage(lastMessage))
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(succ -> {},
                                                    e -> System.out.println(e)); //TODO add error logic
                                    ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.item_message_received, container, false);
                                    TextView textView = (TextView) constraintLayout.getViewById(R.id.text_message_body);
                                    textView.setText(remoteAnswer.getText());
                                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.message_layout);
                                    linearLayout.addView(constraintLayout);
                                    },
                                    e -> {
                                        System.out.println(e); //TODO delete
                                        //TODO show error message
                                    });
                    },
                    e -> System.out.println(e)
                    );
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isOnline()) {
            noInternetImageView.setVisibility(View.INVISIBLE);
            noInternetTextView.setVisibility(View.INVISIBLE);
        } else {
            noInternetImageView.setVisibility(View.VISIBLE);
            noInternetTextView.setVisibility(View.VISIBLE);
        }
    }

    private int insertLastMessage(String id) {
        LastMessage lastMessage = new LastMessage(id);
        appDatabase.getLastMessageDao().insertLastMessage(lastMessage);
        return 0;
    }

    private LastMessage getLastMessage() {
        return appDatabase.getLastMessageDao().getLastMessage();
    }

    private int deleteLastMessage(LastMessage lastMessage) {
        return appDatabase.getLastMessageDao().deleteLastMessage(lastMessage);
    }

    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
