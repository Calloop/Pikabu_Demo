package ru.calloop.pikabu_demo.ui.createPost.fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.databinding.CreatePostBinding;
//import ru.calloop.pikabu_demo.ui.createPost.ItemMoveCallback;
import ru.calloop.pikabu_demo.ui.createPost.CreatePostViewModel;
import ru.calloop.pikabu_demo.ui.createPost.ItemMoveCallback;
import ru.calloop.pikabu_demo.ui.createPost.adapters.CreatePostAdapter;
import ru.calloop.pikabu_demo.ui.createPost.adapters.listeners.ICreatePostListener;
import ru.calloop.pikabu_demo.ui.models.PostItem;

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

    PostItem postItem;


//    private final ActivityResultLauncher<Intent> loadImageLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(), result -> {
//                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
//                    Uri externalImageUri = result.getData().getData();
//
//                    ImageDecoder.Source source = ImageDecoder
//                            .createSource(contentResolver, externalImageUri);
//
//                    try {
//                        Bitmap bitmap = ImageDecoder.decodeBitmap(source);
////                        if (adapter.createPostItem
////                                (position, type, saveImageToInternalStorage(bitmap))) {
////                            setContentToPreferences();
////                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
////                            try {
////                                bitmap = MediaStore.Images.Media
////                                        .getBitmap(contentResolver, externalImageUri);
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
//                }
//            });

    private final ActivityResultLauncher<Intent> loadImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    Bitmap bitmap = getBitmapFromUri(imageUri);
                    String path = saveImageToInternalStorage(bitmap);

                    PostItem last = adapter.getLastPostItemCreated();
                    adapter.getAdapterList().get(last.getPosition()).setValue(path);
                    //setContentToPreferences();
                    adapter.clearLastPostItemCreated();


                    //PostItem postItem = new PostItem(createPostViewModel.getNewPostItem())


//                        if (createPostItem(newPostItemType, newPostItemPosition, path)) {
//                            setContentToPreferences();
//                        }


//                    Intent intent = result.getData();
//                    int position = 0;
//                    int type = 0;
//
//                    position = intent.getIntExtra("position", 0);
//                    type = intent.getIntExtra("type", 3);
//                    Log.d("TAG", "pos: " + position);
//                    Log.d("TAG", "type: " + type);
//
//                    String path = saveImageToInternalStorage(bitmap, "" + position); // return internal Uri
//
//                    if (path != null) {
//                        if (adapter.createPostItem(position, type, path)) {
//                            setContentToPreferences();
//                        }
//                    }
//                    path = saveImageToInternalStorage(bitmap);
//                    Log.d("TAG", "path " + path);

//                        if (saveImageToInternalStorage(bitmap)) {
//                            Toast.makeText(activity, "SUCCESS", Toast.LENGTH_SHORT).show();
//                            path = test.getPath();
//                        }
                }
//
//                    ImageDecoder.Source source = ImageDecoder
//                            .createSource(contentResolver, externalImageUri);
//
//                    try {
//                        Bitmap bitmap = ImageDecoder.decodeBitmap(source);
//                        saveImageToInternalStorage(bitmap);
////                        if (adapter.createPostItem
////                                (position, type, saveImageToInternalStorage(bitmap))) {
////                            setContentToPreferences();
////                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
            });

    @SuppressLint("ObsoleteSdkInt")
    private final ActivityResultLauncher<String[]> permissionsLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                boolean isReadPermissionGranted = false;
                boolean isWritePermissionGranted = false;

                if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                    isReadPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.READ_EXTERNAL_STORAGE));
                }

                if (result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null) {
                    @SuppressLint("ObsoleteSdkInt")
                    boolean minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;

                    isWritePermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)) || minSdk29;
                }

                if (!isReadPermissionGranted || !isWritePermissionGranted) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                            || !shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        getPermissionsDisallowedDialog().show();
                    }
                } else {
                    createPostViewModel.setIsPermissionsGranted(true);

                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    loadImageLauncher.launch(intent);
                }
            });

//    {
//                @Override
//                public void onActivityResult(Map<String, Boolean> permissions) {
//                    if (permissions == Manifest.permission.READ_EXTERNAL_STORAGE) {
//                        permissions.containsValue(Manifest.permission.READ_EXTERNAL_STORAGE);
//                    }
//                    String[] permissions = results.keySet().toArray(new String[0]);
//                    if (checkSelfPermission(permissions)) {
//                        // all permissions are granted
//                        callback.onActivityResult(currentRequestCode, PermissionResult.GRANTED);
//                    } else if (CreatePostFragment.this.shouldShowRequestPermissionRationale(permissions)) {
//                        callback.onActivityResult(currentRequestCode, PermissionResult.DENIED);
//                    } else {
//                        // permissions are permanently disabled
//                        callback.onActivityResult(currentRequestCode, PermissionResult.PERMANENTLY_DENIED);
//                    }


//                if (result.containsValue(true)) {
//                    Toast.makeText(activity, "GRANTED", Toast.LENGTH_SHORT).show();
//                    permGranted = true;
//                    loadImageLauncher.launch(new Intent(Intent.ACTION_PICK,
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
//                } else {
//                    Toast.makeText(activity, "DENIED", Toast.LENGTH_SHORT).show();
//                    permGranted = false;
//                }

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
                postItem1 -> {
                        Log.d("TAG", "type: " + postItem1.getType() + " |pos: " + postItem1.getPosition() + " |val: " + postItem1.getValue());
                        createPostItem(postItem1);
                });
        //createPostViewModel.getIsPermissionsGranted().observe(getViewLifecycleOwner(), aBoolean -> testMethod());
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
                Toast.makeText(activity, "EDITING APPLIED", Toast.LENGTH_SHORT).show();
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
        if (createPostViewModel.upToDateMediaLinks(CreatePostViewModel.POST_ITEMS_FROM_PREFERENCES,
                createPostViewModel.getPostItems())) {
            Toast.makeText(activity, "Block was removed cause its Media was lost",
                    Toast.LENGTH_SHORT).show();
        }
        createPostHeadlineText.setText(createPostViewModel.getPostHeadline());
        adapter.setAdapterList(createPostViewModel.getPostItems());
        Log.d("TAG", "getContentFromPreferences: " + createPostViewModel.getPostItems());
    }

    private void setContentToPreferences() {
        createPostViewModel.setPostHeadline(String.valueOf(createPostHeadlineText.getText()));
        createPostViewModel.setPostItems(adapter.getAdapterList());
    }

    private void getContentFromSavedState() {
        if (createPostViewModel.upToDateMediaLinks(CreatePostViewModel.POST_ITEMS_FROM_SAVED_STATE,
                createPostViewModel.getPostItemsFromSavedState())) {
            Toast.makeText(activity, "Block was removed cause its Media was lost",
                    Toast.LENGTH_SHORT).show();
        }

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

    private void testMethod() {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        loadImageLauncher.launch(intent);
    }

    private void createPostItem(@NonNull PostItem postItem) {
        Log.d("TAG", "createPostItem: ");
        switch (postItem.getType()) {
            case 1:
                adapter.addItem(postItem);
                setContentToPreferences();
                //createPostViewModel.setNewPostItem(0, 0); // можно скрыть?

//                if (adapter.createPostItem(type, position, null)) {
//                    setContentToPreferences();
//                }
                break;
            case 2:
                break;
            case 3:
                adapter.addItem(postItem);
                adapter.notifyItemInserted(postItem.getPosition());

                //setContentToPreferences();

                    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE};

                    permissionsLauncher.launch(permissions);


                break;
//                    Intent intent = new Intent(Intent.ACTION_PICK,
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.putExtra("position", position);
//                    intent.putExtra("type", type);
//
//                    loadImageLauncher.launch(intent);

//                    ContextWrapper contextWrapper = new ContextWrapper(requireContext());
//                    File fileDirectory = contextWrapper.getDir("Images", MODE_PRIVATE);
//                    String fileName = String.format(Locale.getDefault(), "%s.%s",
//                            "" + type, "jpg");
//                    File filePath = new File(fileDirectory, fileName);
//
//                    if (adapter.createPostItem(position, type, filePath.getPath())) {
//                        setContentToPreferences();
//                    }
//                if (permGranted) {
//                    Log.d("TAG", "createPostItem: ");
//                    loadImageLauncher.launch(new Intent(Intent.ACTION_PICK,
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
//
//                    if (path != null) {
//                        if (adapter.createPostItem(position, type, path)) {
//                            setContentToPreferences();
//                        }
//                    }
//                }
            case 4:
                break;
        }

        createPostViewModel.setNewPostItem(new PostItem(0, 0, null));
        setDescriptionTextView();
    }
//endregion

    private void permResult(Map<String, Boolean> grantResults) {
//        if (grantResults.entrySet().al) {
//            // granted
//        } else {
//            if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                // dailog
//            } else {
//                Toast.makeText(activity, "DENIED", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    private void updatePermissions() {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        @SuppressLint("ObsoleteSdkInt")
        boolean minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
        boolean isReadPermissionGranted = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean isWritePermissionGranted = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                || minSdk29;

        if (!isReadPermissionGranted || !isWritePermissionGranted) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Perms");
                builder.setMessage("message");
                builder.setPositiveButton("OK", (dialog, which) -> {
                    permissionsLauncher.launch(permissions);
                });
                builder.setNegativeButton("Canc", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                ActivityCompat.requestPermissions(requireActivity(), permissions, 0);
            }
        }


//        if (!isWritePermissionGranted) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Toast.makeText(activity, "need write", Toast.LENGTH_SHORT).show();
//            }
//        }

//        if (!isReadPermissionGranted || !isWritePermissionGranted) {
//            String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
//            permissionsLauncher.launch(permissions);
//        } else {
//            createPostViewModel.setIsPermissionsGranted(true);
//            Log.d("TAG", "updatePermissions: GRANTED");
//        }
    }

    private AlertDialog.Builder getPermissionsRequiredDialog() {
        return new AlertDialog.Builder(getContext())
                .setTitle("Permissions required")
                .setMessage("Allow to access to read storage for using content")
                .setPositiveButton("Allow", (dialog, which) -> {
                    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    permissionsLauncher.launch(permissions);
                })
                .setNegativeButton("Reject", (dialog, which) ->
                        dialog.dismiss());
    }

    private AlertDialog.Builder getPermissionsDisallowedDialog() {
        return new AlertDialog.Builder(getContext())
                .setTitle("Permissions disallowed")
                .setMessage("Allow to access to read storage for adding content")
                .setPositiveButton("OK", (dialog, which) ->
                        dialog.dismiss())
                .setNegativeButton("Settings", (dialog, which) ->
                        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package",
                                        requireActivity().getPackageName(), null))));
    }

    private void testtest() {
//        @SuppressLint("ObsoleteSdkInt")
//        boolean minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
//
//        boolean isReadPermissionGranted = ContextCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
//
//        boolean isWritePermissionGranted = ContextCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//                || minSdk29;
//
//        if (!isReadPermissionGranted || !isWritePermissionGranted) {
//            createPostViewModel.setIsPermissionsGranted(true);
//        }
//
//        if (ContextCompat.checkSelfPermission(
//                requireContext(), Manifest.permission.REQUESTED_PERMISSION) ==
//                PackageManager.PERMISSION_GRANTED) {
//            // You can use the API that requires the permission.
//            performAction(...);
//        } else if (shouldShowRequestPermissionRationale(...)) {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected, and what
//            // features are disabled if it's declined. In this UI, include a
//            // "cancel" or "no thanks" button that lets the user continue
//            // using your app without granting the permission.
//            showInContextUI(...);
//        } else {
//            // You can directly ask for the permission.
//            // The registered ActivityResultCallback gets the result of this request.
//            requestPermissionLauncher.launch(
//                    Manifest.permission.REQUESTED_PERMISSION);
//        }
    }

    private Bitmap getBitmapFromUri(Uri imageUri) {
        @SuppressLint("ObsoleteSdkInt")
        boolean minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
        Bitmap bitmap = null;

        if (minSdk29) {
            try {
                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().getContentResolver(), imageUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    // ПЕРЕНЕСТИ ВО VIEW MODEL
//region [IMAGE OPERATIONS]
    @Nullable
    private String saveImageToInternalStorage(Bitmap bitmap) {
//        @SuppressLint("ObsoleteSdkInt") Uri build =
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ?
//                        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//                        : MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "image.jpg");
//        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//
//        try {
//            Uri test = contentResolver.insert(build, contentValues);
//            OutputStream stream = contentResolver.openOutputStream(test);
//
//            if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)) {
//                Toast.makeText(activity, "Couldn't create MediaStore entry", Toast.LENGTH_SHORT).show();
//            } else {
//                return test.getPath();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;

        ContextWrapper contextWrapper = new ContextWrapper(requireContext());
        File fileDirectory = contextWrapper.getDir("Images", MODE_PRIVATE);
        String fileName = String.format(Locale.getDefault(), "%s.%s",
                Objects.requireNonNull(fileDirectory.listFiles()).length + 1, "jpg");
        File filePath = new File(fileDirectory, fileName);

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
            return filePath.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private void getImageFromExternalStorageLauncher(int position, int type) {
//        loadImageLauncher = registerForActivityResult(new ActivityResultContracts
//                .StartActivityForResult(), result -> {
//            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
//                if (isWritePermissionGranted) {
//                    Uri externalImageUri = result.getData().getData();
//                    Bitmap bitmap = null;
//
//                    if (isVersionCodeQ) {
//                        try {
//                            bitmap = MediaStore.Images.Media
//                                    .getBitmap(contentResolver, externalImageUri);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        ImageDecoder.Source source = ImageDecoder
//                                .createSource(contentResolver, externalImageUri);
//                        try {
//                            bitmap = ImageDecoder.decodeBitmap(source);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    saveImageToInternalStorage(bitmap, position, type);
//                }
//            }
//        });
//    }
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
        createPostViewModel.setNewPostItem(new PostItem(type, position, null));
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
                        createPostViewModel.getPostItems());
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