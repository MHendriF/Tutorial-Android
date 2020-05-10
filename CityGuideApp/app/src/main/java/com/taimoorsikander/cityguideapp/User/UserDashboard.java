package com.taimoorsikander.cityguideapp.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter.Categories;
import com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter.Featured;
import com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter.MostViewed;
import com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.taimoorsikander.cityguideapp.R;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView rvFeatured;
    RecyclerView rvMostViewed;
    RecyclerView rvCategories;
    FeaturedAdapter featuredAdapter;
    MostViewedAdapter mostViewedAdapter;
    CategoriesAdapter categoriesAdapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        rvFeatured = findViewById(R.id.rv_featured);
        rvMostViewed = findViewById(R.id.rv_most_viewed);
        rvCategories = findViewById(R.id.rv_categories);

        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
    }

    private void categoriesRecycler() {
        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});

        ArrayList<Categories> model = new ArrayList<>();
        model.add(new Categories(R.drawable.hospital, "Hospital", gradient1));
        model.add(new Categories(R.drawable.restaurant, "Restaurant", gradient2));
        model.add(new Categories(R.drawable.education, "Education", gradient3));
        model.add(new Categories(R.drawable.market, "Market", gradient4));
        model.add(new Categories(R.drawable.barbershop, "Barber Shop", gradient1));

        categoriesAdapter = new CategoriesAdapter(model);
        rvCategories.setAdapter(categoriesAdapter);
    }

    private void mostViewedRecycler() {
        rvMostViewed.setHasFixedSize(true);
        rvMostViewed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewed> model = new ArrayList<>();
        model.add(new MostViewed(R.drawable.icon_image, "McDonal's", "asdasd hjjkhj klklklkdsadas sdfsdfdskhfbnmbdsfsdfsd"));
        model.add(new MostViewed(R.drawable.icon_image, "Rudolph R", "asdasd hjjkhj klklklkdsadas sdfsdfdskhfbnmbdsfsdfsd"));
        model.add(new MostViewed(R.drawable.icon_image, "Sweat and Bakers", "asdasd hjjkhj klklklkdsadas sdfsdfdskhfbnmbdsfsdfsd"));

        mostViewedAdapter = new MostViewedAdapter(model);
        rvMostViewed.setAdapter(mostViewedAdapter);

    }

    private void featuredRecycler() {
        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<Featured> model = new ArrayList<>();
        model.add(new Featured(R.drawable.icon_image, "McDonal's", "asdasd hjjkhj klklklkdsadas sdfsdfdskhfbnmbdsfsdfsd"));
        model.add(new Featured(R.drawable.icon_image, "Rudolph R", "asdasd hjjkhj klklklkdsadas sdfsdfdskhfbnmbdsfsdfsd"));
        model.add(new Featured(R.drawable.icon_image, "Sweat and Bakers", "asdasd hjjkhj klklklkdsadas sdfsdfdskhfbnmbdsfsdfsd"));

        featuredAdapter = new FeaturedAdapter(model);
        rvFeatured.setAdapter(featuredAdapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaf600});

    }
}
