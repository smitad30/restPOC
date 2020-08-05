package com.example.bootbase;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController

public class BookController {
	
	@Autowired
	BooksRepository br;
	
	@GetMapping("/Books")
	public List<Books> AllBooks() {
		createBook();
		
		
	return Arrays.asList(
				new Books(1,"Mastering Spring 5.2", "Ranga Karanam"),
				new Books(2,"JAVA Spring 5.2", "SgggggD"),
				new Books(3,"C++", "BGS"));
	}
	
	private static void createBook()
	{
	    final String uri = "http://localhost:8080/person/self";
	 
	    Books newBook = new Books(1, "ADC", "Gilly");
	 
	    RestTemplate restTemplate = new RestTemplate();
	    Books result = restTemplate.postForObject( uri, newBook, Books.class);
	 
	    System.out.println("POST called \n" + result);
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
	
	@SuppressWarnings("unchecked")
	@PostMapping(path="/Books")
	public String createBook(@RequestBody Books book) throws JsonProcessingException
	{
		//br.addNewBook(book);
		ObjectMapper om =new ObjectMapper();
		String json = om.writeValueAsString(book);
		Map<String,Object> ps =om.readValue(json,Map.class );
		System.out.println(ps);
		
		return "Added";
	}
}
