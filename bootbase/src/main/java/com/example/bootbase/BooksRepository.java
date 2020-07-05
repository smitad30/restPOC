package com.example.bootbase;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class BooksRepository {
	
	@Autowired
	Books b;
	
	
	public void addNewBook(Books book)
	{
		
		b.addBook(book);
		
	}
	
	
	
	public Map<Integer,Books> findAllBooks() {
        return b.book;
    }

}
