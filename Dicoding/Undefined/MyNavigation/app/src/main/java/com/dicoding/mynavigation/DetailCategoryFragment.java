package com.dicoding.mynavigation;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCategoryFragment extends Fragment {

    public DetailCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvCategoryName = view.findViewById(R.id.tv_category_name);
        TextView tvCategoryDesc = view.findViewById(R.id.tv_category_description);

//        String dataName = getArguments().getString(CategoryFragment.EXTRA_NAME);
//        long dataDesc = getArguments().getLong(CategoryFragment.EXTRA_STOCK);

        String dataName = DetailCategoryFragmentArgs.fromBundle(getArguments()).getName();
        long dataDesc = DetailCategoryFragmentArgs.fromBundle(getArguments()).getStock();

        tvCategoryName.setText(dataName);
        tvCategoryDesc.setText("Stocks: "+dataDesc);

        Button btnProfile = view.findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment, null)
        );
    }
}
