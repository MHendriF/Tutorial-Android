package com.dicoding.mynotesapp.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "com.dicoding.mynotesapp";
    private static final String SCHEME = "content";

    private DatabaseContract(){}

    public static final class NoteColumns implements BaseColumns {
        public static String TABLE_NAME = "note";
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String DATE = "date";

        // untuk membuat URI content://com.dicoding.mynotesapp/note
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}
