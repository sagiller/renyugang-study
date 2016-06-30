package com.sagiller.renyugang.chapter2.section3.topic3;

import com.sagiller.renyugang.chapter2.section3.topic3.Book;
import com.sagiller.renyugang.chapter2.section4.topic4.IOnNewBookArrivedListener;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}