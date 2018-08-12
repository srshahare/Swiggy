package com.example.shashank.swiggy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private android.support.v7.widget.Toolbar toolbar;


    ListView mListview;
    ListView lst;

    String[] foodName = {"Biryani", "Chicken", "Burgar", "Tandoori", "Chocolate",
            "Coffee", "Milk Shake","Indian","Biryani", "Chicken", "Burgar", "Tandoori", "Chocolate",
            "Coffee", "Milk Shake", "Indian"};
    String[] desc = {"Hotel Miraj", "Cafe Delight", "Culcutta Roll", "Sabana Bakery", "Paneno",
            "Punjabi Grills", "Hotel Grand Sita", "Delhi Hotel", "Red Chilli", "Hotel Tuli", "Hotel Paradise", "Hotel Gurunanak",
    "Hotel Sabana", "Hotel Empire", "Grand Hotel", "The Gateway Hotel"};
    int[] imgId = {R.drawable.bioryani, R.drawable.boti, R.drawable.burgar,
            R.drawable.chicken, R.drawable.chocko, R.drawable.dark, R.drawable.shake, R.drawable.thali,R.drawable.bioryani,
    R.drawable.boti,R.drawable.burgar, R.drawable.chicken, R.drawable.chocko, R.drawable.dark,R.drawable.shake,R.drawable.thali};

    int[] images = {R.drawable.swiggy_a, R.drawable.swiggy_b, R.drawable.swiggy_c, R.drawable.swiggy_d, R.drawable.swiggy_e,
    R.drawable.swiggy_f, R.drawable.swiggy_g};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Swiggy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListview = (ListView) findViewById(R.id.food_list);
        lst = (ListView) findViewById(R.id.horizontal_list);

        MyAdapter myAdapter = new MyAdapter(MainActivity.this,foodName,desc,imgId);
        mListview.setAdapter(myAdapter);

        HorizontalAdapter adapter = new HorizontalAdapter(MainActivity.this, images);
        lst.setAdapter(adapter);

       mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent i = new Intent(MainActivity.this, ImageActivity.class);
               startActivity(i);
           }
       });

       lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this, ImageActivity.class);
               startActivity(intent);
           }
       });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menu_settings)
        {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.menu_logout)
        {
            mAuth.signOut();
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        if (item.getItemId() == R.id.menu_help)
        {
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
        }
        return true;

    }

}
