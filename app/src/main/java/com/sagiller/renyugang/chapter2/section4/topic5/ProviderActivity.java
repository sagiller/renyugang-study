package com.sagiller.renyugang.chapter2.section4.topic5;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.sagiller.renyugang.R;
import com.sagiller.renyugang.chapter2.section3.topic3.Book;

/**
 * Created by sagiller on 16/5/13.
 */
public class ProviderActivity extends Activity {
    private static final String TAG = "BookProviderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_2_4_5);
//        Uri uri = Uri.parse("content://com.sagiller.renyugang.book.provider");
//        getContentResolver().query(uri,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null);
//        getContentResolver().query(uri,null,null,null,null);

        Uri bookUri = BookProvider.BOOK_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put("_id" ,6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri,values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[] {"_id","name"}, null,null,null);
        while(bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.d(TAG, "query book:" + book.bookName.toString());
        }
        bookCursor.close();

        Uri userUri = BookProvider.USER_CONTENT_URI;
        Cursor userCursor = getContentResolver().query(userUri, new String[] {"_id", "name", "sex"}, null ,null, null);
        while(userCursor.moveToNext()) {
            User user = new User();
            user.setId(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setMale(userCursor.getInt(2) == 1);
            Log.d(TAG, "query user:" + user.getName().toString());

        }
        userCursor.close();
    }
}
