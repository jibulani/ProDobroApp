package com.eugene.prodobroapp.CatalogueService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.eugene.prodobroapp.MainActivity;
import com.eugene.prodobroapp.R;

/**
 * Формирование на экране списка информационных материалов
 * Created by Maxim on 30.09.2018.
 */

public class CatalogueFragment extends Fragment {

    private static final String CLASS_TAG = "CatalogueFragment";

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Dialog frmDialog;// форма диалогового окна для визуализации информации по выбранному пункту из каталога
    TextView text;// текст, содержащийся в пункте меню, который будет визаулизирован при нажатии на пункт в каталоге

    /**
     * данный метод создаёт экземпляр фрагмента,
     * метод вызывается из MainActivity.createFragment()
     */
    public static CatalogueFragment newInstance() {

        Bundle args = new Bundle();

        CatalogueFragment fragment = new CatalogueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.catalogue_layout, container, false);

        frmDialog = new Dialog(getActivity());

        expListView = view.findViewById(R.id.lvExp);// get Expandable ListView

            //Preparing the list data ==============================================
            listDataHeader = new ArrayList<String>();// list of topics
            listDataChild = new HashMap<String, List<String>>();

                // Adding child data
                listDataHeader.add("ОСТАНОВИТЬ НАСИЛИЕ");
                listDataHeader.add("РЕШИТЬ ПРОБЛЕМУ");
                listDataHeader.add("ПОМОЧЬ СВОЕМУ ОРГАНИЗМУ");
                listDataHeader.add("ЖИТЬ СЧАСТЛИВО");

                // Adding child data
                List<String> stopvailence = new ArrayList<String>();
                stopvailence.add("План спасения");
                stopvailence.add("Законодательство");
                stopvailence.add("Права человека");
                stopvailence.add("Виды насилия");
                stopvailence.add("Цикл насилия");
                stopvailence.add("Виды насилия");
                stopvailence.add("Компьютерная безопастность");
                stopvailence.add("Ненасильственное общение");

                List<String> solveproblem = new ArrayList<String>();
                solveproblem.add("Депрессия");
                solveproblem.add("Тревожные расстройства/психозы");
                solveproblem.add("Употребление психоактивных веществ");
                solveproblem.add("Суицид/самоповреждения");
                solveproblem.add("Агрессия");
                solveproblem.add("Пост-травматический синдром");

                List<String> helpyourbody = new ArrayList<String>();
                helpyourbody.add("Здоровый образ жизни");
                helpyourbody.add("Сексуальное здоровье");
                helpyourbody.add("Сон");
                helpyourbody.add("Физические упражнения");
                helpyourbody.add("Внимательность к себе");

                List<String> life = new ArrayList<String>();
                life.add("Взросление");
                life.add("Творчество");
                life.add("Этика/философия");
                life.add("Религия");
                life.add("Предназначение");
                life.add("Самопознание/осознанность");

            listDataChild.put(listDataHeader.get(0), stopvailence); // Header, Child data
            listDataChild.put(listDataHeader.get(1), solveproblem);
            listDataChild.put(listDataHeader.get(2), helpyourbody);
            listDataChild.put(listDataHeader.get(3), life);
            // =====================================================================

        listAdapter = new ExpandableListAdapter(view.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                showItemContent(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                return false;
            }
        });

        return view;
    }

    // выводит диалоговое окно с информацией, содержащийся в выбранном пункте каталога
    private void showItemContent(String item){
        String content = "";

        switch (item){
            case "План спасения":
                content = "1) держи под рукой: свидетельство о рождении, банковскую карту, ключи, мобильный телефон.\n" +
                        "2) сообщи людям, которые могут тебя поддержать, что происходит, и держи под рукой их контакты.\n" +
                        "3) чувства испуга, злости, смущения, стыда, грусти нормальны в данной ситуации. Постарайся проговорить/проиграть то, что ты чувствуешь.\n" +
                        "4) насилие – не твоя вина, оно противозаконно, но с другими тоже это происходит\n" +
                        "5) найди надежного взрослого, чтобы с ним поговорить (родственник, друг или школьный психолог, детский телефон доверия). Обратись в центр помощи.";
                break;
            case "Депрессия":
                content = "1) ВСЕГДА есть другое решение, даже если вы его не видите прямо сейчас.\n" +
                        "2) наличие мыслей причинить вред себе или другим не делает вас плохим человеком.\n" +
                        "3) если ваши чувства неконтролируемы, скажите себе подождать 24 часа перед тем, как предпринять что-либо.\n" +
                        "4) если вы боитесь, что вы не сможете контролировать себя, убедитесь, что вы никогда не остаетесь в одиночестве\n" +
                        "5) Попробуйте заснуть.";
                break;
        }

        // Установите заголовок
        frmDialog.setTitle(item);
        // Передайте ссылку на разметку
        frmDialog.setContentView(R.layout.catalogue_item_layout);
        // Найдите элемент TextView внутри вашей разметки
        // и установите ему соответствующий текст
        text = frmDialog.findViewById(R.id.catalogue_item_text);
        text.setText(content);
        // Выводим диалоговое окно на экран
        frmDialog.show();
    }
}