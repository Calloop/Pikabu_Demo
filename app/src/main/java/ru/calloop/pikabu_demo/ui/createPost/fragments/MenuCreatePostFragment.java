package ru.calloop.pikabu_demo.ui.createPost.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.calloop.pikabu_demo.databinding.CreatePostContentButtonsBinding;
import ru.calloop.pikabu_demo.ui.createPost.CreatePostViewModel;

public class MenuCreatePostFragment extends Fragment implements View.OnClickListener {

    private CreatePostContentButtonsBinding binding;
    private CreatePostViewModel viewModel;
    private ActivityResultLauncher<String> storagePermissionLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStoragePermissionsLauncher();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CreatePostContentButtonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(CreatePostViewModel.class);
        binding.buttonAddTextCreatePost.setOnClickListener(this);
        binding.buttonAddImageCreatePost.setOnClickListener(this);
        binding.buttonDeleteBlockCreatePost.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();

        if (id == binding.buttonAddTextCreatePost.getId()) {
            viewModel.setType(1);
        } else if (id == binding.buttonAddImageCreatePost.getId()) {
            checkStoragePermissions();
        } else if (id == binding.buttonDeleteBlockCreatePost.getId()) {
            viewModel.setType(3);
        }
    }

    private void checkStoragePermissions() {
        storagePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void setStoragePermissionsLauncher() {
        storagePermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        viewModel.setType(2);
                    } else {
                        getPermissions();
                    }
                }
        );
    }

    private void getPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            viewModel.setType(2);
        } else if (shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            getPermissionsRequiredDialog().show();
        } else {
            getPermissionsDisallowedDialog().show();
        }
    }

    private AlertDialog.Builder getPermissionsRequiredDialog() {
        return new AlertDialog.Builder(getContext())
                .setTitle("Permissions required")
                .setMessage("Allow to access to read storage for using content")
                .setPositiveButton("Allow", (dialog, which) ->
                        checkStoragePermissions())
                .setNegativeButton("Reject", (dialog, which) ->
                        dialog.dismiss());
    }

    private AlertDialog.Builder getPermissionsDisallowedDialog() {
        return new AlertDialog.Builder(getContext())
                .setTitle("Permissions disallowed")
                .setMessage("Allow to access to read storage for adding content:")
                .setPositiveButton("OK", (dialog, which) ->
                        dialog.dismiss())
                .setNegativeButton("Settings", (dialog, which) ->
                        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package",
                                        requireActivity().getPackageName(), null))));
    }
}