package com.eugene.prodobroapp.CatalogueService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.eugene.prodobroapp.App;
import com.eugene.prodobroapp.R;
import com.eugene.prodobroapp.data.source.local.AppDatabase;

/**
 * Формирование на экране списка информационных материалов
 * Created by Maxim on 30.09.2018.
 */

public class CatalogueFragment extends Fragment {

    private static final String CLASS_TAG = "CatalogueFragment";
    private static final AppDatabase appDatabase = App.getInstance().getAppDatabase();


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

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

        return view;
    }
}