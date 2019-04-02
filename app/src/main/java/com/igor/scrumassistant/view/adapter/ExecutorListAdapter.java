package com.igor.scrumassistant.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.Role;
import com.igor.scrumassistant.model.entity.Executor;

import java.util.ArrayList;
import java.util.List;

public class ExecutorListAdapter extends RecyclerView.Adapter<ExecutorListAdapter.ExecutorViewHolder>
//implements HolderClickObserver, SelectionObserver
{

//    private SelectionHelper mSelectionHelper;
//    private WeakHolderTracker mHolderTracker;

//    private final WeakReference<CreatingActivity> mActivityRef;

    private List<Executor> mExecutorList = new ArrayList<>();
    private int mPositionChosen = -1;

//    public ExecutorListAdapter(@NonNull List<Executor> executorList, @NonNull CreatingActivity activity) {
//        mExecutorList = executorList;
//        mActivityRef = new WeakReference<>(activity);
//        mHolderTracker = new WeakHolderTracker();
//        mSelectionHelper = new SelectionHelper(mHolderTracker);
//        mSelectionHelper.registerSelectionObserver(this);
//    }

    public ExecutorListAdapter(@NonNull List<Executor> executorList) {
        mExecutorList = executorList;
    }
//    public SelectionHelper getSelectionHelper() {
//        return mSelectionHelper;
//    }

    @NonNull
    @Override
    public ExecutorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.executor_list_item, viewGroup, false);
//        ExecutorViewHolder holder = new ExecutorViewHolder(view);
//        return mSelectionHelper.wrapSelectable(holder);
        return new ExecutorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExecutorViewHolder executorViewHolder, int position) {
        String executorName = mExecutorList.get(position).getName() + " " + mExecutorList.get(position).getSurname();

        executorViewHolder.mNameTextView.setText(executorName);
        setRole(executorViewHolder.mRoleTextView, mExecutorList.get(position).getRole());
        executorViewHolder.mRadioButton.setChecked(mExecutorList.get(position).isChosen());
//        Checkable view = (Checkable) executorViewHolder.itemView;
//        view.setChecked(mSelectionHelper.isItemSelected(position));
//        mHolderTracker.bindHodler(executorViewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mExecutorList.size();
    }

    public int getChosenPosition() {
        return mPositionChosen;
    }

//    @Override
//    public void onSelectedChanged(RecyclerView.ViewHolder holder, boolean isSelected) {
//        ((Checkable) holder.itemView).setChecked(isSelected);
//    }

//    @Override
//    public void onSelectableChanged(boolean isSelectable)
//    {
//        if (isSelectable)
//        {
//            mActivityRef.get().startActionMode();
//        }
//    }

    private void setRole(@NonNull TextView roleText, @NonNull Role role) {
        switch (role) {
            case ANALYTIC:
                roleText.setText(R.string.analytic_role);
                break;
            case DESIGNER:
                roleText.setText(R.string.designer_role);
                break;
            case DEVELOPER:
                roleText.setText(R.string.developer_role);
                break;
            case SCRUM_MASTER:
                roleText.setText(R.string.scrum_master_role);
                break;
            case PRODUCT_OWNER:
                roleText.setText(R.string.product_owner_role);
                break;
        }
    }

//    @Override
//    public void onHolderClick(RecyclerView.ViewHolder holder) {
//
//    }

    private void onExecutorClicked(@NonNull RadioButton radioButton, int newPositionChosen){
        if(mPositionChosen != -1) {
            notifyItemChanged(mPositionChosen);
        }
        radioButton.setChecked(true);
        mPositionChosen = newPositionChosen;
    }
//
//    @Override
//    public boolean onHolderLongClick(RecyclerView.ViewHolder holder) {
//        return false;
//    }

    class ExecutorViewHolder extends RecyclerView.ViewHolder {

        private final TextView mRoleTextView;
        private final TextView mNameTextView;
        private final RadioButton mRadioButton;

        ExecutorViewHolder(@NonNull View itemView) {
            super(itemView);

            mRoleTextView = itemView.findViewById(R.id.executor_role_text_view);
            mNameTextView = itemView.findViewById(R.id.executor_name_text_view);
            mRadioButton = itemView.findViewById(R.id.executor_chosen_radio_button);

            mRadioButton.setOnClickListener(v -> onExecutorClicked(mRadioButton, getAdapterPosition()));
        }
    }
}
