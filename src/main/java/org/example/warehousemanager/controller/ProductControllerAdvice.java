package org.example.warehousemanager.controller;


import org.example.warehousemanager.model.dto.ErrorResponse;
import org.example.warehousemanager.model.exception.EmptyProductStorageException;
import org.example.warehousemanager.model.exception.NotFoundByArticleException;
import org.example.warehousemanager.model.exception.NotFoundByIdException;
import org.example.warehousemanager.model.exception.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(EmptyProductStorageException.class)
    public ResponseEntity<ErrorResponse> handleEmptyProductStorageException(EmptyProductStorageException e){
        ErrorResponse errorResponse = new ErrorResponse("Product Storage is empty");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundByIdException(NotFoundByIdException e){
        ErrorResponse errorResponse = new ErrorResponse("Product with such UUID not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundByArticleException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundByArticleException(NotFoundByArticleException e){
        ErrorResponse errorResponse = new ErrorResponse("Product with such article not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
