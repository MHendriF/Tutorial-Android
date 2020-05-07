package com.dicoding.droidcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class FirstFragment extends Fragment {

    Badge badge;
    ImageView ivDonut;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivDonut = view.findViewById(R.id.donut);
        badge =  new QBadgeView(getActivity()).bindTarget(ivDonut);
        badge.setBadgeTextSize(20, true).setBadgeNumber(5);

        view.findViewById(R.id.donut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDonutOrder(view);
            }
        });

        view.findViewById(R.id.ice_cream).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showIceCreamOrder(view);
            }
        });

        view.findViewById(R.id.froyo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFroyoOrder(view);
            }
        });
    }

    private void showDonutOrder(View view) {
        displayToast(getString(R.string.donut_order_message));
    }

    private void showIceCreamOrder(View view) {
        displayToast(getString(R.string.ice_cream_order_message));
    }

    private void showFroyoOrder(View view) {
        displayToast(getString(R.string.froyo_order_message));
    }

    private void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
