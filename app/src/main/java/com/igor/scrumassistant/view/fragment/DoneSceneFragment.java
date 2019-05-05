package com.igor.scrumassistant.view.fragment;

import android.support.v4.app.FragmentActivity;

import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.presentation.fragment.parent.CommonSceneFragmentPresenter;
import com.igor.scrumassistant.presentation.fragment.DoneSceneFragmentPresenter;
import com.igor.scrumassistant.view.fragment.parent.SceneFragment;

import java.util.Objects;


public class DoneSceneFragment extends SceneFragment {

    public DoneSceneFragment() {
        // Required empty public constructor
    }

    public static DoneSceneFragment newInstance() {
        return new DoneSceneFragment();
    }

    @Override
    @ProvidePresenter
    protected CommonSceneFragmentPresenter providePresenter() {
        return new DoneSceneFragmentPresenter(Objects.requireNonNull(getActivity()), ((FragmentActivity) getActivity()).getSupportLoaderManager());
    }
}
