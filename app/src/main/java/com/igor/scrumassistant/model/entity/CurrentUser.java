package com.igor.scrumassistant.model.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class CurrentUser {

    private final static String USER_ID = "userId";
    private final static String PROJECT_ID = "projectId";

    public static void setUserId(@NonNull Context context, long userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, Long.toString(userId));
        editor.apply();
    }

    public static void setProjectId(@NonNull Context context, long projectId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PROJECT_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROJECT_ID, Long.toString(projectId));
        editor.apply();
    }

    public static long getUserId(@NonNull Context context) {
        long userId;

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE);
        String stringUserId = sharedPreferences.getString(USER_ID, "");
        if (stringUserId != null && !stringUserId.equals("")) {
            userId = Long.parseLong(stringUserId);
        } else {
            userId = -1;
        }
        return userId;
    }

    public static long getProjectId(@NonNull Context context) {
        long projectId;

        SharedPreferences sharedPreferences = context.getSharedPreferences(PROJECT_ID, Context.MODE_PRIVATE);
        String stringProjectId = sharedPreferences.getString(PROJECT_ID, "");
        if (stringProjectId != null && !stringProjectId.equals("")) {
            projectId = Long.parseLong(stringProjectId);
        } else {
            projectId = -1;
        }
        return projectId;
    }
}
