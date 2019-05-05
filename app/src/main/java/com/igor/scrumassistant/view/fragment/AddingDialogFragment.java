package com.igor.scrumassistant.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igor.scrumassistant.R;
import com.igor.scrumassistant.view.activity.MainActivity;

public class AddingDialogFragment extends Fragment {

    private View mTaskCreatingView;
    private View mProjectCreatingView;

    public static AddingDialogFragment newInstance() {
        return new AddingDialogFragment();
    }

    public AddingDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adding_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        mTaskCreatingView.setOnClickListener(null);
        mProjectCreatingView.setOnClickListener(null);

        super.onDetach();
    }

    private void initViews(@NonNull View view) {
        mTaskCreatingView = view.findViewById(R.id.add_task_text_view);
        mTaskCreatingView.setOnClickListener(v -> showTaskCreatingView());
        mProjectCreatingView = view.findViewById(R.id.add_project_text_view);
        mProjectCreatingView.setOnClickListener(v -> showProjectCreatingView());
    }

    private void showTaskCreatingView() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.openTaskCreatingActivity();
        }
    }

    private void showProjectCreatingView() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.openProjectCreatingActivity();
        }
    }
}
