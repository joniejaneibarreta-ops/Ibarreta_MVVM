package com.example.ibarreta_mvvm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize RecyclerView and Adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        // 2. Initialize ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // 3. Observe the LiveData
        // Whenever the user list in the ViewModel changes, this onChanged method triggers
        userViewModel.getUsers().observe(this, users -> {
            if (users != null) {
                // Update the adapter with the new list
                userAdapter.setUsers(users);
            }
        });
    }
}