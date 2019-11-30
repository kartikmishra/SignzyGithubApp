package com.example.signzygithubapp.Activities;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.signzygithubapp.Adapters.ViewPagerAdapter;
import com.example.signzygithubapp.Fragments.FragmentRepository;
import com.example.signzygithubapp.Fragments.FragmentUserDetail;
import com.example.signzygithubapp.Model.User;

import com.example.signzygithubapp.R;
import com.example.signzygithubapp.Retrofit.ApiClient;
import com.example.signzygithubapp.Retrofit.ApiInterface;
import com.google.android.material.tabs.TabLayout;
import com.mancj.materialsearchbar.MaterialSearchBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView githubImage;


    private User user = new User();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager_id);
        githubImage = findViewById(R.id.githubImage);

    }


    public void setUpUsersRecyclerView(User user) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragment(new FragmentUserDetail(user), "Profile");
        viewPagerAdapter.AddFragment(new FragmentRepository(user), "Repositories");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view_menu,menu);
         final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                ApiInterface client = ApiClient.getmInstance().getApi();
                Call<User> call = client.getUser(query);

                if (isNetworkAvailable()) {
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            if (response.message().contentEquals("OK")) {

                                View view = getWindow().getDecorView();
                                view.setBackgroundColor(Color.WHITE);
                                githubImage.setVisibility(View.GONE);
                                user = response.body();
                                setUpUsersRecyclerView(user);
                                user = new User();

                                searchView.setQuery("", false);
                                searchView.setIconified(true);
                            } else if (response.message().contentEquals("Not Found")) {
                                Toast.makeText(MainActivity.this, "User name not found!",
                                        Toast.LENGTH_SHORT).show();
                                viewPager.setSaveFromParentEnabled(false);
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "Limit Exceeded! Try after some time",
                                        Toast.LENGTH_SHORT).show();
                            }
                            hideKeyBoard();

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                   
                } else {
                    hideKeyBoard();
                    Toast.makeText(MainActivity.this, "No Internet Connection",
                            Toast.LENGTH_SHORT).show();

                }

                return true;

            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }


    // Method to check internet connectivity
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    //Method to hide key board
    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}


