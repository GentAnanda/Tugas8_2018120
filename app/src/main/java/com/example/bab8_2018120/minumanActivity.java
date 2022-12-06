package com.example.bab8_2018120;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.bab8_2018120.databinding.ActivityMinumanBinding;
import com.google.android.material.navigation.NavigationView;

public class minumanActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private ActivityMinumanBinding binding;
    RecyclerView recylerView;
    String s1[], s2[],s3[];
    int images[] =
            {R.drawable.coksu,R.drawable.matcha2,R.drawable.kopsu,R.drawable.logo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMinumanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dl = (DrawerLayout)findViewById(R.id.dl);
                abdt = new
                ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        //action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view =
                (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_timer){
                    Intent a = new
                            Intent(minumanActivity.this, MainActivity.class);
                    startActivity(a);
                }else if (id == R.id.nav_store){
                    Intent a = new
                            Intent(minumanActivity.this, minumanActivity.class);
                    startActivity(a);
                }else if (id == R.id.nav_profile){
                    Intent a = new
                            Intent(minumanActivity.this, Profile.class);
                    startActivity(a);
                }
                else if (id == R.id.nav_data){
                    Intent a = new Intent(minumanActivity.this, data.class);
                    startActivity(a);
                }
                return true;
            }
        });
        //recycle View
        recylerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.Minuman);
        s2 = getResources().getStringArray(R.array.Deskripsi);
        s3 = getResources().getStringArray(R.array.Star);
        minumanAdapter appAdapter = new
                minumanAdapter(this,s1,s2,s3,images);
        recylerView.setAdapter(appAdapter);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(minumanActivity.this,LinearLayoutManager
                .HORIZONTAL, false
        );
        recylerView.setLayoutManager(layoutManager);
        recylerView.setItemAnimator(new DefaultItemAnimator());
        //work manager
        final OneTimeWorkRequest request = new
                OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork("Notifikasi",
                        ExistingWorkPolicy.REPLACE, request);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }
}
