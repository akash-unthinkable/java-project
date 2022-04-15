package com.example.demo.api.dao;

import com.example.demo.api.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.net.URL;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public Book findById(int id);
}
