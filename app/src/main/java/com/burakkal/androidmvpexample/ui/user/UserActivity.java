package com.burakkal.androidmvpexample.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burakkal.androidmvpexample.R;
import com.burakkal.androidmvpexample.model.AppModel;
import com.burakkal.androidmvpexample.model.local.AppDatabase;
import com.burakkal.androidmvpexample.model.local.entity.User;
import com.burakkal.androidmvpexample.presenter.user.UserContract;
import com.burakkal.androidmvpexample.presenter.user.UserPresenter;

import java.util.List;

public class UserActivity extends AppCompatActivity implements UserContract.View {

    private RecyclerView recyclerUserList;
    private TextView tvInfoNoUser;
    private ImageView imgInfoNoUser;

    private AppModel appModel;
    private UserPresenter presenter;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerUserList = findViewById(R.id.rv_user_list);
        tvInfoNoUser = findViewById(R.id.tv_info_title);
        imgInfoNoUser = findViewById(R.id.img_info_icon);

        userAdapter = new UserAdapter();
        recyclerUserList.setAdapter(userAdapter);

        appModel = new AppModel(AppDatabase.getInstance(getApplicationContext()));
        presenter = new UserPresenter(appModel, this);
        presenter.loadUsers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void showUsers(List<User> users) {
        hideInfoView();
        showUserView();
        userAdapter.setUsers(users);
    }

    @Override
    public void showNoUser() {
        hideUserView();
        showInfoView();
    }

    @Override
    public void showGetUserError() {
        showToast("An error occurred while getting users.");
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddUserSuccess() {
        showToast("User added successfully.");
    }

    @Override
    public void showAddUserError() {
        showToast("An error occurred while adding user.");
    }

    public void onAddUserClick(View view) {
        presenter.addUser("Burak", "KAL");
    }

    private void hideUserView() {
        recyclerUserList.setVisibility(View.GONE);
    }

    private void showUserView() {
        recyclerUserList.setVisibility(View.VISIBLE);
    }

    private void hideInfoView() {
        tvInfoNoUser.setVisibility(View.GONE);
        imgInfoNoUser.setVisibility(View.GONE);
    }

    private void showInfoView() {
        tvInfoNoUser.setVisibility(View.VISIBLE);
        imgInfoNoUser.setVisibility(View.VISIBLE);
    }
}
