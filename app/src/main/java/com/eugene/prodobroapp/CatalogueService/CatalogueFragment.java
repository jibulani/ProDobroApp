package com.eugene.prodobroapp.CatalogueService;

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

public class CatalogueFragment extends Fragment {

    public static CatalogueFragment newInstance() {

        Bundle args = new Bundle();

        CatalogueFragment fragment = new CatalogueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.catalogue_layout, container, false);
    }
}
