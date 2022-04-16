package ru.calloop.pikabu_demo.ui.createPost;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.base.BaseFragment;
import ru.calloop.pikabu_demo.ui.createPost.adapters.BlocksListCreatePostAdapter;
import ru.calloop.pikabu_demo.ui.createPost.models.Post;
import ru.calloop.pikabu_demo.ui.createPost.models.PostItem;
import ru.calloop.pikabu_demo.ui.main.home.HomeViewModel;
import ru.calloop.pikabu_demo.ui.signing.models.SessionManager;

public class CreatePostFragment extends BaseFragment implements CreatePostContract.IView {

    private AppCompatActivity activity;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private HomeViewModel homeViewModel;
    private CreatePostViewModel createPostViewModel;

    //private CreatePostPresenter presenter;
    private BlocksListCreatePostAdapter adapter;
    //private BlocksListCreatePostAdapter.OnItemClickListener listener;

    private TextView textViewDescriptionCreatePost;
    private ActionMode actionMode;

    @Override
    public BaseFragment providerFragment() {
        return new CreatePostFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View providerFragmentView
            (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        activity = (AppCompatActivity) requireActivity();
        recyclerView = view.findViewById(R.id.list_create_post);
        textViewDescriptionCreatePost = view.findViewById(R.id.textView_description_create_post);

        adapter = new BlocksListCreatePostAdapter();
        createPostViewModel = new ViewModelProvider(activity).get(CreatePostViewModel.class);
        createPostViewModel.loadArrayList().observe(activity,
                postItems -> adapter.updateList(postItems));
        //adapter = new BlocksListCreatePostAdapter(createPostViewModel.getPostItems().getValue());

        setToolbar();
        setAdapter();

//        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
//                int result = bundle.getInt("bundleKey");
//                CreatePostFragment.this.createPostItem(result);
//            }
//        });

        getChildFragmentManager()
                .setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
                    int result = bundle.getInt("bundleKey");
                    createPostItem(result);
                });

        return view;
    }

    //region [SET: TOOLBAR, FRAGMENT MANAGER, RECYCLER VIEW]
    private void setToolbar() {
        toolbar = activity.findViewById(R.id.toolbar_activity);
        activity.setSupportActionBar(toolbar);
    }

    private void setAdapter() {
//        presenter = new CreatePostPresenter();
//        presenter.attachView(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
//        createPostViewModel.getLocalPostItemList()
//                .observe(activity,
//                        postItems -> {
//                            adapter = new BlocksListCreatePostAdapter(postItems);
//                            recyclerView.setAdapter(adapter);
//                        });

        //Log.d("TEST", "" + adapter.getItemCount());
    }
    //endregion

    //region [TOOLBAR OPTION MENU]
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_create_post, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.edit_create_post) {
            if (actionMode != null) {
                return false;
            }

            activity.startSupportActionMode(actionModeCallback);
            //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (itemId == R.id.add_post_create_post) {
            createPostViewModel.clearArrayList();
            SharedPreferences userSharedPreferences = activity
                    .getSharedPreferences(SessionManager.KEY, Context.MODE_PRIVATE);
            int userId = userSharedPreferences.getInt(SessionManager.ID, 0);
            Post post = new Post(userId);
            createPostViewModel.setPostItems(post, adapter.getAdapterList());

            //createPostViewModel.setArrayList(adapter.getAdapterList());
            //adapter.savePostItems();
            //presenter.insert(new Post(1), adapter.postItemList);
            //startActivity(new Intent(CreatePostFragment.this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    //endregion

    //region [ACTION MODE]
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            adapter.editModeIsActive(true);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.apply_edit_contextual_create_post) {
                adapter.clearPreparedToDeleteItemList();
                createPostViewModel.setArrayList(adapter.getAdapterList());
                showToast("EDITING APPLIED");
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
            if (adapter.getPrepareToDeleteItemsList().size() > 0) {
                adapter.getAdapterList().addAll(adapter.getPrepareToDeleteItemsList());
                adapter.getAdapterList().sort(Comparator.comparing(PostItem::getDataPosition));
            }
            adapter.clearPreparedToDeleteItemList();
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        }
    };
    //endregion

    //region [SHOW EVENTS]
    public void showPostItems(List<PostItem> postItems) {
        //adapter.loadPostItems(postItems);
    }

    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region [CLICK EVENTS]
    private void createPostItem(int type) {
        if (type != 0) {
//            List<PostItem> test = new ArrayList<>(1);
//            PostItem postItem = new PostItem(0, type, null);
//            test.add(postItem);
//     //       Log.d("TEST", "" + adapter.getItemCount());
//            CreatePostDiffUtilCallback createPostDiffUtilCallback =
//                    new CreatePostDiffUtilCallback(adapter.getAdapterList(),
//                            test);
//            DiffUtil.DiffResult diffResult =
//                    DiffUtil.calculateDiff(createPostDiffUtilCallback);
//
//            adapter.createPostItem(type);
//            diffResult.dispatchUpdatesTo(adapter);
//            listIsEmpty();

            adapter.createPostItem(type);
            listIsEmpty();

        }
    }
    //endregion

    //region [OPERATIONS]
    public void listIsEmpty() {
        if (adapter.getItemCount() == 0) {
            textViewDescriptionCreatePost.setVisibility(View.VISIBLE);
        } else
            textViewDescriptionCreatePost.setVisibility(View.GONE);
    }

    public void checkEditMode() {
        // скрыть кнопки редактирования
    }

    @Override
    public void onDestroy() {
        //presenter.detachView();
        super.onDestroy();
    }
    //endregion
}