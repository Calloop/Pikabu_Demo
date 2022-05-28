package ru.calloop.pikabu_demo.ui.main.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import ru.calloop.pikabu_demo.R;
import ru.calloop.pikabu_demo.ui.BaseFragment;

public class SettingsFragment extends BaseFragment {

    @Override
    public BaseFragment fragment() {
        return new SettingsFragment();
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.activity_navigation_controller);
        Log.d("TEST", "" + navHostFragment.getChildFragmentManager().getBackStackEntryCount());
//        Toolbar toolbar = view.findViewById(R.id.toolbar_settings);
//        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
//        DrawerLayout drawerLayout = Objects.requireNonNull(getActivity()).findViewById(R.id.drawer_layout);
//        NavController navController = NavHostFragment.findNavController(this);
//        NavigationUI.setupActionBarWithNavController((AppCompatActivity) getActivity(), navController, drawerLayout);


//        button.setOnClickListener(buttonView -> {
//            SignInFragment fragment = new SignInFragment();
//            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.nav_host_fragment, fragment, "SignInFragment")
//                    .commit();
//        });

        return view;
    }

    //    private SettingsViewModel settingsViewModel;
//    private FragmentSettingsBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        settingsViewModel =
//                new ViewModelProvider(this).get(SettingsViewModel.class);
//
//        binding = FragmentSettingsBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textSettings;
//        settingsViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}