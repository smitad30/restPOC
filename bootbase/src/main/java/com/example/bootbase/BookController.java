package com.example.bootbase;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BookController {
	
	@Autowired
	BooksRepository br;
	
	@GetMapping("/Books")
	public List<Books> AllBooks() {
	return Arrays.asList(
				new Books(1,"Mastering Spring 5.2", "Ranga Karanam"),
				new Books(2,"JAVA Spring 5.2", "SgggggD"),
				new Books(3,"C++", "BGS"));
	}
	
	@GetMapping("/Books/all")
	public ResponseEntity<Map<Integer,Books>> listAllBooks()
	{
		
	Map<Integer,Books> book = br.findAllBooks();
		return new ResponseEntity<Map<Integer,Books>>(book, HttpStatus.OK);
	}
	
        /*if (book.isEmpty()) {
            return new ResponseEntity<?>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Books>>(book, HttpStatus.OK);
		
	}*/
	
	@PostMapping(path="/Books")
	public ResponseEntity<Object> createBook(@RequestBody Books book)
	{
		
		br.addNewBook(book);
		return new ResponseEntity<>("Book Added",HttpStatus.CREATED);
	}
}
