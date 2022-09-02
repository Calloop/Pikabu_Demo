package ru.calloop.pikabu_demo.ui.createPost;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.BaseFragment;
import ru.calloop.pikabu_demo.ui.createPost.adapters.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost.ICreatePostPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.ISessionPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.createPost.createPostPreferences;
import ru.calloop.pikabu_demo.ui.repositories.SharedPreferences.session.SessionPreferences;

public class CreatePostFragment extends BaseFragment {

    private ICreatePostPreferences createPostPreferences;
    private ISessionPreferences sessionPreferences;
    private CreatePostViewModel createPostViewModel;
    private BlocksListCreatePostAdapter adapter;
    private AppCompatActivity activity;
    private NavController navController;
    private TextView descriptionCreatePost, postHeadline;
    private ActionMode actionMode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public BaseFragment fragment() {
        return newInstance();
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        activity = (AppCompatActivity) requireActivity();
        descriptionCreatePost = view.findViewById(R.id.textView_description_create_post);
        postHeadline = view.findViewById(R.id.create_post_headline_text);
        RecyclerView recyclerView = view.findViewById(R.id.list_create_post);
        Toolbar toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);

        createPostPreferences = new createPostPreferences(activity);
        sessionPreferences = new SessionPreferences(activity);
        createPostViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        adapter = new BlocksListCreatePostAdapter();

        NavHostFragment navHostFragment = (NavHostFragment) activity
                .getSupportFragmentManager().findFragmentById(R.id.activity_navigation_controller);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        requireActivity().addMenuProvider(new MenuProvider() {
                                              @Override
                                              public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                                                  menuInflater.inflate(R.menu.toolbar_create_post, menu);

                                                  // Add option Menu Here

                                              }

                                              @Override
                                              public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                                                  int itemId = menuItem.getItemId();

                                                  if (itemId == R.id.edit_create_post) {
                                                      if (actionMode != null) {
                                                          return false;
                                                      }

                                                      activity.startSupportActionMode(actionModeCallback);
                                                      return true;
                                                  }

                                                  if (itemId == R.id.add_post_create_post) {
                                                      int userId = sessionPreferences.getAccountId();
                                                      createPostViewModel.insertPostToDB(userId, postHeadline.getText().toString(),
                                                              adapter.getAdapterList());
                                                      createPostPreferences.clearPreference();
                                                      getPreferences();
                                                      navController.popBackStack(R.id.homeFragment, false);
                                                  }

                                                  return false;
                                              }
                                          },
                getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        getPreferences();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        getChildFragmentManager()
                .setFragmentResultListener("requestKey", this,
                        (requestKey, bundle) -> {
                            int result = bundle.getInt("bundleKey");
                            createPostItem(result);
                        });

        listIsEmpty();

        return view;
    }

    private void getPreferences() {
        postHeadline.setText(createPostPreferences.getPostHeadline());
        adapter.updateAdapterList(createPostPreferences.getPostItems());
    }

    private void setPreferences() {
        createPostPreferences.setPostHeadline(postHeadline.getText().toString());
        createPostPreferences.setPostItems(adapter.getAdapterList());
    }

    //region [ACTION MODE]
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            adapter.editModeIsActive(true);
            setPreferences();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.apply_edit_contextual_create_post) {
                adapter.editModeIsActive(false);
                setPreferences();
                Toast.makeText(activity, "EDITING APPLIED", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            adapter.editModeIsActive(false);
            getPreferences();
            //Log.d("TAG", "onDestroyActionMode: " + createPostPreferences.getPostItems().size());
            //Log.d("TAG", "listSize: " + adapter.getAdapterList().size());
        }
    };
    //endregion

    //region [CLICK EVENTS]
    private void createPostItem(int type) {
        if (type != 0) {
            adapter.createPostItem(type);
            listIsEmpty();
        }
    }
    //endregion

    //region [OPERATIONS]
    public void listIsEmpty() {
        descriptionCreatePost
                .setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
    //endregion
}