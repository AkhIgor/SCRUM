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
import com.igor.scrumassistant.view.IParicipantSelecter;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsListAdapter extends RecyclerView.Adapter<ParticipantsListAdapter.ExecutorViewHolder> {

    private List<Executor> mExecutorList;
    private List<Integer> mChosenExecutorPositions = new ArrayList<>();
    private IParicipantSelecter mParticipantSelecter;

    public ParticipantsListAdapter(@NonNull List<Executor> executorList, @NonNull IParicipantSelecter paricipantSelecter) {
        mExecutorList = executorList;
        mParticipantSelecter = paricipantSelecter;
    }

    @NonNull
    @Override
    public ExecutorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.executor_list_item, viewGroup, false);
        return new ExecutorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExecutorViewHolder executorViewHolder, int position) {
        String executorName = mExecutorList.get(position).getName() + " " + mExecutorList.get(position).getSurname();

        executorViewHolder.mNameTextView.setText(executorName);
        setRole(executorViewHolder.mRoleTextView, Role.valueOf(mExecutorList.get(position).getRole()));
        executorViewHolder.mRadioButton.setChecked(mExecutorList.get(position).isChosen());
    }

    @Override
    public int getItemCount() {
        return mExecutorList.size();
    }

    public List getChosenPosition() {
        return mChosenExecutorPositions;
    }

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

    class ExecutorViewHolder extends RecyclerView.ViewHolder {

        private final TextView mRoleTextView;
        private final TextView mNameTextView;
        private final RadioButton mRadioButton;

        ExecutorViewHolder(@NonNull View itemView) {
            super(itemView);

            mRoleTextView = itemView.findViewById(R.id.executor_role_text_view);
            mNameTextView = itemView.findViewById(R.id.executor_name_text_view);
            mRadioButton = itemView.findViewById(R.id.executor_chosen_radio_button);

            mRadioButton.setOnClickListener(v -> mParticipantSelecter.participantSelected(getAdapterPosition()));
        }
    }
}