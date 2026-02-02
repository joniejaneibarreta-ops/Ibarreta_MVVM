package com.example.ibarreta_mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<List<User>> users = new MutableLiveData<>();
    private final List<User> localUserList = new ArrayList<>();

    public LiveData<List<User>> getUsers() {
        if (users.getValue() == null) {
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        localUserList.clear();
        localUserList.add(new User("Jonie Jane", "Jonie@jane.com"));
        localUserList.add(new User("Princess Rhea", "Princess@rhea.com"));
        localUserList.add(new User("Jenny Ann", "Jenny@ann.com"));
        localUserList.add(new User("Farren Pyxie", "Farren@pyxie.com"));
        localUserList.add(new User("Nathaniel Jay", "Nathaniel@jay.com"));
        localUserList.add(new User("Frenzez Ericka", "Frenzez@ericka.com"));
        localUserList.add(new User("Jewel Carl", "Jewel@carl.com"));

        users.setValue(new ArrayList<>(localUserList));
    }

    public void addUser(String name, String email) {
        if (name == null || name.trim().isEmpty()) return;
        if (email == null || email.trim().isEmpty()) return;

        localUserList.add(new User(name, email));
        users.setValue(new ArrayList<>(localUserList));
    }
}
