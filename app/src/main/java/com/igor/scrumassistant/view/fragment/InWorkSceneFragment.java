package com.igor.scrumassistant.view.fragment;

import android.support.v4.app.FragmentActivity;

import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.presentation.fragment.parent.CommonSceneFragmentPresenter;
import com.igor.scrumassistant.presentation.fragment.InWorkSceneFragmentPresenter;
import com.igor.scrumassistant.view.fragment.parent.SceneFragment;

import java.util.Objects;


public class InWorkSceneFragment extends SceneFragment {

    public InWorkSceneFragment() {
        // Required empty public constructor
    }

    public static InWorkSceneFragment newInstance() {
        return new InWorkSceneFragment();
    }

    @Override
    @ProvidePresenter
    protected CommonSceneFragmentPresenter providePresenter() {
        return new InWorkSceneFragmentPresenter(Objects.requireNonNull(getActivity()), ((FragmentActivity) getActivity()).getSupportLoaderManager());
    }
}
