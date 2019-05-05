package com.igor.scrumassistant.view.fragment;

import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.presentation.fragment.ToDoSceneFragmentPresenter;
import com.igor.scrumassistant.presentation.fragment.parent.CommonSceneFragmentPresenter;
import com.igor.scrumassistant.view.fragment.parent.SceneFragment;

import java.util.Objects;


public class ToDoSceneFragment extends SceneFragment {

    public ToDoSceneFragment() {
        super();
        // Required empty public constructor
    }

    public static ToDoSceneFragment newInstance() {
        return new ToDoSceneFragment();
    }


    @Override
    @ProvidePresenter
    protected CommonSceneFragmentPresenter providePresenter() {
        return new ToDoSceneFragmentPresenter(Objects.requireNonNull(getActivity()), getActivity().getSupportLoaderManager());
    }


}
