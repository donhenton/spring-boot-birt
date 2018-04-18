/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers.support;

import javax.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
 

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Exception> handleNoResultException(
            NoResultException nre) {

        return new ResponseEntity<Exception>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericError> handleException(Exception e) {

        GenericError ge = new GenericError(e);
        return new ResponseEntity<GenericError>(ge,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class GenericError {

        private final String message;
        private final String className;
        private final boolean error = true;
        public GenericError()
        {
           this.message = null;
           this.className = null;
        }
        public GenericError(Exception e) {
            this.message = e.getMessage();
            this.className = e.getClass().getName();
        }

        /**
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        

        /**
         * @return the className
         */
        public String getClassName() {
            return className;
        }

        /**
         * @return the error
         */
        public boolean isError() {
            return error;
        }

         
        
        

         
    }

    
}
