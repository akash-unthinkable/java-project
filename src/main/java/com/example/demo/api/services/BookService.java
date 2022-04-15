package com.example.demo.api.services;

import com.example.demo.api.dao.BookRepository;
import com.example.demo.api.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    /*public static List<Book> bookList= new ArrayList<>();
    static {
        bookList.add(new Book(12,"python","rajesh"));
        bookList.add(new Book(19,"java script","amit"));
        bookList.add(new Book(12,"node js","reena"));
    }*/
    // get all books
    public List<Book> getAllbooks(){

        return (List<Book>) bookRepository.findAll();
    }
    // get single book


        public Optional<Book> getBookById ( int id) {


                //return bookList.stream().filter(e -> e.getId() == id).findFirst().get();
                Optional<Book> book  = Optional.ofNullable(bookRepository.findById(id));
                return book;
        }

        public  Book addBook(Book book){
            Book bookResult=bookRepository.save(book);
            return  bookResult;
        }
        public void deleteBook(int bid){

            //bookList.remove(bookList.stream().filter(e->e.getId()==bid).findFirst().get());
            bookRepository.deleteById(bid);
        }
        //update book
        public void updateBook( Book bookk, int bookId){

          /*bookList=  bookList.stream().map(b->{
                if(b.getId()==bookId){
                    b.setTitle(bookk.getTitle());
                    b.setAuthor(bookk.getAuthor());
                }
                return  b;
            }).collect(Collectors.toList());*/
            bookk.setId(bookId);
            bookRepository.save(bookk);
        }

       /* public  void findAllSorting(){
            Pageable sortedData= PageRequest.of(0,3, Sort.by("title"));
        }*/
    public  List<Book> findAllSortingDesc(int page){
        Pageable sorteddesc= PageRequest.of(page,3, Sort.by("title").descending());

        List<Book> allbook=  bookRepository.findAll(sorteddesc).getContent();
        return allbook;
    }

}
