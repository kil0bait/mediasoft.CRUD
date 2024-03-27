package com.rasul.crud.CRUD.handler;


import com.rasul.crud.CRUD.handler.exceptions.EmptyProductStorageException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByArticleException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByIdException;
import com.rasul.crud.CRUD.handler.exceptions.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductAdvice {
    @ExceptionHandler(EmptyProductStorageException.class)
    public ResponseEntity<Response> handleEmptyProductStorageException(EmptyProductStorageException e){
        Response response = new Response("Product Storage is empty");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<Response> handleNotFoundByIdException(NotFoundByIdException e){
        Response response = new Response("Product with such UUID not found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundByArticleException.class)
    public ResponseEntity<Response> handleNotFoundByArticleException(NotFoundByArticleException e){
        Response response = new Response("Product with such article not found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Response> handleSQLException(SQLException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
