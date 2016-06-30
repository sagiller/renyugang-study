package com.sagiller.renyugang.chapter2.section4.topic4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sagiller.renyugang.chapter2.section3.topic3.Book;
import com.sagiller.renyugang.chapter2.section3.topic3.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by sagiller on 16/5/11.
 */
public class BookManagerService extends Service {
    private static final String TAG = "BookManagerService";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    //private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();
    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (!mListeners.contains(listener)) {
//                mListeners.add(listener);
//            } else {
//                Log.d(TAG, "already exists.");
//            }
            mListeners.register(listener);
            int N = mListeners.beginBroadcast();
            mListeners.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (mListeners.contains(listener)) {
//                mListeners.remove(listener);
//                Log.d(TAG, "unregister listener success.");
//            } else {
//                Log.d(TAG, "not found, can not unregister");
//            }
//            Log.d(TAG, "unregisterListener, current size:" + mListeners.size());
            mListeners.unregister(listener);
            Log.d(TAG, "unregister listener success.");
            int N = mListeners.beginBroadcast();
            mListeners.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "ios"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);

//        for (int i = 0; i < mListeners.size(); i++) {
//            IOnNewBookArrivedListener listener = mListeners.get(i);
//            Log.d(TAG, "onNewBookArrived, notify listener:" + listener);
//            listener.onNewBookArrived(book);
//        }
        final int N = mListeners.beginBroadcast();
        Log.d(TAG, "onNewBookArrived, notify listeners:" + N);
        for (int i = 1; i < N; i++) {
            IOnNewBookArrivedListener l = mListeners.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListeners.finishBroadcast();

    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            //do background processing here.......
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try{
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}



