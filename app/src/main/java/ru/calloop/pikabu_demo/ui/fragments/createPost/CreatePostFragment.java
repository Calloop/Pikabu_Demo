package ru.calloop.pikabu_demo.ui.fragments.createPost;

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
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.CreatePostBinding;
//import ru.calloop.pikabu_demo.presentation.utility.createPost.ItemMoveCallback;
import ru.calloop.pikabu_demo.viewmodels.CreatePostViewModel;
import ru.calloop.pikabu_demo.ui.utility.createPost.ItemMoveCallback;
import ru.calloop.pikabu_demo.adapters.createPost.CreatePostAdapter;
import ru.calloop.pikabu_demo.adapters.createPost.listeners.ICreatePostListener;
import ru.calloop.pikabu_demo.data.models.PostItem;

public class CreatePostFragment extends Fragment implements ICreatePostListener {

    private CreatePostBinding binding;
    private CreatePostViewModel createPostViewModel;
    private CreatePostAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    private AppCompatActivity activity;
    private NavController navController;
    private ActionMode actionMode;

    private TextView createPostHeadlineText;
    private boolean actionModeState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CreatePostBinding.inflate(inflater, container, false);
        createPostHeadlineText = binding.createPostHeadlineText;

        activity = (AppCompatActivity) requireActivity();
        activity.addMenuProvider(
                new menuProvider(), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        navController = NavHostFragment.findNavController(this);
        adapter = new CreatePostAdapter(getContext(), this);
        setRecyclerView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createPostViewModel =
                new ViewModelProvider(requireActivity()).get(CreatePostViewModel.class);
        createPostViewModel.getNewPostItem().observe(getViewLifecycleOwner(),
                postItem -> {
                    if (postItem.getType() != 0) {
                        createPost(postItem);
                    }
                });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            getContentFromSavedState();
            actionModeState = createPostViewModel.getActionModeState();

            if (actionModeState) {
                actionMode = activity.startSupportActionMode(actionModeCallback);
            }
        } else {
            getContentFromPreferences();
        }

        setDescriptionTextView();
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        setContentToSavedState();
        createPostViewModel.setActionModeState(actionModeState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!actionModeState) {
            setContentToPreferences();
        }
    }

    // ПЕРЕНЕСТИ В ОТДЕЛЬНЫЙ ФАЙЛ
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(@NonNull ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            binding.containerContentButtonsCreatePost.setVisibility(View.GONE);
            actionModeState = true;
            adapter.setActionModeState(true);
            createPostViewModel.setActionModeState(true);
            setContentToPreferences();
            setDescriptionTextView();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, @NonNull MenuItem item) {
            if (item.getItemId() == R.id.apply_edit_contextual_create_post) {
                actionModeState = false;
                adapter.setActionModeState(false);
                createPostViewModel.setActionModeState(false);
                setContentToPreferences();
                setDescriptionTextView();
                binding.containerContentButtonsCreatePost.setVisibility(View.VISIBLE);
                Toast.makeText(activity, "Успешно отредактирвоано", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionModeState = false;
            adapter.setActionModeState(false);
            createPostViewModel.setActionModeState(false);
            actionMode = null;
            binding.containerContentButtonsCreatePost.setVisibility(View.VISIBLE);
            getContentFromPreferences();
            setDescriptionTextView();
        }
    };

    //region [CONTENT OPERATIONS]
    private void getContentFromPreferences() {
        createPostHeadlineText.setText(createPostViewModel.getPostHeadline());
        adapter.setAdapterList(createPostViewModel.getPostItems());
    }

    private void setContentToPreferences() {
        createPostViewModel.setPostHeadline(String.valueOf(createPostHeadlineText.getText()));
        createPostViewModel.setPostItems(adapter.getAdapterList());
    }

    private void getContentFromSavedState() {
        adapter.setAdapterList(createPostViewModel.getPostItemsFromSavedState());
    }

    private void setContentToSavedState() {
        createPostViewModel.setPostItemsFromSavedState(adapter.getAdapterList());
        createPostViewModel.setActionModeState(true);
    }
//endregion

    //region [RECYCLER SETTINGS]
    private void setRecyclerView() {
        RecyclerView recyclerView = binding.listCreatePost;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setItemTouchHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private ItemTouchHelper setItemTouchHelper() {
        ItemTouchHelper.Callback callback = new ItemMoveCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        return itemTouchHelper;
    }
//endregion

    //region [OPERATIONS]
    private void setDescriptionTextView() {
        binding.descriptionTextView
                .setVisibility(adapter.getItemCount() == 0 && !actionModeState
                        ? View.VISIBLE : View.GONE);
    }

    private void createPost(PostItem postItem) {
        adapter.addItem(postItem);
        createPostViewModel.setNewPostItem(new PostItem(0, 0, null));
        setContentToPreferences();
        setDescriptionTextView();
    }
//endregion

    @Override
    public void setContentValue(int position, String contentValue) {
        adapter.getAdapterList().get(position).setValue(contentValue);
    }

    @Override
    public void onClickRemoveItem(int position) {
        adapter.getAdapterList().remove(position);
        adapter.notifyItemRemoved(position);
    }

    //region [CREATE POST LISTENER]
    @Override
    public void onClickAddItem(int type, int position) {
        //createPostViewModel.setNewPostItem(new PostItem(type, position, null));
    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
//endregion

    // ПЕРЕНЕСТИ В ОТДЕЛЬНЫЙ ФАЙЛ
    private class menuProvider implements MenuProvider {
        @Override
        public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.toolbar_create_post, menu);
        }

        @Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.edit_create_post) {
                if (actionMode == null) {
                    actionMode = activity.startSupportActionMode(actionModeCallback);
                }
                return true;
            }

            if (itemId == R.id.add_post_create_post) {
                createPostViewModel.insertPostToDB(createPostViewModel.getAccountId(),
                        String.valueOf(createPostHeadlineText.getText()),
                        adapter.getAdapterList());
                createPostHeadlineText.setText(null);
                adapter.getAdapterList().clear();
                createPostViewModel.clearPreferences();
                navController.popBackStack(R.id.homeFragment, false);
                return true;
            }

            return false;
        }
    }
}