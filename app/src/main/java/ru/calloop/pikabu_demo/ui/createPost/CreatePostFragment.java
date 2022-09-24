package ru.calloop.pikabu_demo.ui.createPost;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.CreatePostBinding;
import ru.calloop.pikabu_demo.ui.createPost.adapters.BlocksListCreatePostAdapter;

public class CreatePostFragment extends Fragment {

    private CreatePostBinding binding;
    private CreatePostViewModel createPostViewModel;
    private BlocksListCreatePostAdapter adapter;
    private AppCompatActivity activity;
    private NavController navController;
    private ActionMode actionMode;
    private TextView createPostHeadlineText;
    private ActivityResultLauncher<String[]> permissionsLauncher;
    private ActivityResultLauncher<Intent> loadImageLauncher;
    private boolean isReadPermissionGranted;
    private boolean isWritePermissionGranted;
    private boolean minSdkVersion;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPermissionsLauncher();
        requestPermissions();
        getImageLauncher();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CreatePostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        activity = (AppCompatActivity) requireActivity();
        createPostViewModel =
                new ViewModelProvider(requireActivity()).get(CreatePostViewModel.class);
        createPostViewModel.getType().observe(getViewLifecycleOwner(), this::createPostItem);

        adapter = new BlocksListCreatePostAdapter();
        activity.addMenuProvider(
                new menuProvider(), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        navController = NavHostFragment.findNavController(this);

        createPostHeadlineText = binding.createPostHeadlineText;
        setRecyclerView();
        getPreferences();
        checkRecyclerIsEmpty();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        setPreferences();
    }

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(@NonNull ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_contextual_create_post, menu);
            mode.setTitle("Редактирование");
            binding.containerContentButtonsCreatePost.setVisibility(View.GONE);
            adapter.editModeIsActive(true);
            setPreferences();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, @NonNull MenuItem item) {
            if (item.getItemId() == R.id.apply_edit_contextual_create_post) {
                adapter.editModeIsActive(false);
                binding.containerContentButtonsCreatePost.setVisibility(View.VISIBLE);
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
            binding.containerContentButtonsCreatePost.setVisibility(View.VISIBLE);
            getPreferences();
        }
    };

    //region [CLICK EVENTS]
    private void createPostItem(int type) {
        switch (type) {
            case 1:
                adapter.createPostItem(type);
                checkRecyclerIsEmpty();
                setPreferences();
                break;
            case 2:
                loadImageLauncher.launch(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        }

        createPostViewModel.setType(0);
    }
    //endregion

    //region [OPERATIONS]
    private void setRecyclerView() {
        RecyclerView recyclerView = binding.listCreatePost;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void getPreferences() {
        createPostHeadlineText.setText(createPostViewModel.getPostHeadline());
        adapter.updateAdapterList(createPostViewModel.getPostItems());
    }

    private void setPreferences() {
        createPostViewModel
                .setPostHeadline(String.valueOf(createPostHeadlineText.getText()));
        createPostViewModel.setPostItems(adapter.getAdapterList());
    }

    private void checkRecyclerIsEmpty() {
        binding.textViewDescriptionCreatePost
                .setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    private boolean saveImageToStorage(Bitmap bitmap) {
        ContentResolver contentResolver = requireContext().getContentResolver();

        Uri imageCollection;
        //imageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "imageName" + ".jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri imageUri = contentResolver.insert(imageCollection, contentValues);

        try {
            OutputStream outputStream = contentResolver.openOutputStream(imageUri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            return true;
        } catch (Exception exception) {
            Toast.makeText(activity, "Image not saved: \n" + exception, Toast.LENGTH_SHORT).show();
            exception.printStackTrace();
        }
        return false;
    }

    private void getImageLauncher() {
        loadImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri loadedImage = result.getData().getData();
                        Bitmap bitmap = null;

                        if (minSdkVersion) {
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(
                                        requireContext().getContentResolver(),
                                        loadedImage
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ImageDecoder.Source source = ImageDecoder.createSource(
                                    requireContext().getContentResolver(), loadedImage);
                            try {
                                bitmap = ImageDecoder.decodeBitmap(source);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


//                        Bundle bundle = result.getData().getExtras();
//                        Bitmap bitmap = (Bitmap) bundle.get("data");

                        if (isWritePermissionGranted) {
                            if (saveImageToStorage(bitmap)) {
                                Toast.makeText(activity, "Image saved", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(activity, "No permissions", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void setPermissionsLauncher() {
        permissionsLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                        isReadPermissionGranted = Boolean.TRUE.equals(
                                result.get(Manifest.permission.READ_EXTERNAL_STORAGE));
                    }

                    if (result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null) {
                        isWritePermissionGranted = Boolean.TRUE.equals(
                                result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
                    }
                });
    }

    private void requestPermissions() {
        minSdkVersion = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;

        setReadPermissionGranted();
        setWritePermissionGranted();

        List<String> permissionsRequest = new ArrayList<>();
        isWritePermissionGranted = isWritePermissionGranted || minSdkVersion;

        if (!isReadPermissionGranted) {
            permissionsRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!isWritePermissionGranted) {
            permissionsRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionsRequest.isEmpty()) {
            permissionsLauncher.launch(permissionsRequest.toArray(new String[0]));
        }
    }

    private void setReadPermissionGranted() {
        isReadPermissionGranted = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void setWritePermissionGranted() {
        isWritePermissionGranted = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
//endregion

    private class menuProvider implements MenuProvider {
        @Override
        public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
            menuInflater.inflate(R.menu.toolbar_create_post, menu);
        }

        @Override
        public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.edit_create_post && actionMode == null) {
                activity.startSupportActionMode(actionModeCallback);
                return true;
            }

            if (itemId == R.id.add_post_create_post) {
                createPostViewModel.insertPostToDB(createPostViewModel.getAccountId(),
                        String.valueOf(createPostHeadlineText.getText()),
                        adapter.getAdapterList());
                createPostViewModel.clearPreferences();
                getPreferences();
                navController.popBackStack(R.id.homeFragment, false);
                return true;
            }

            return false;
        }
    }
}