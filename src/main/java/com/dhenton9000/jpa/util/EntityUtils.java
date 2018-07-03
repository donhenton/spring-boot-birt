/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.jpa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 * @author dhenton
 */
public class EntityUtils {
 
    public static String trimField(String f)
    {
        if (f != null) {
            return f.trim();
        } else {
            return null;
        }
    }
    
    public static <E> List<E> makeCollection(Iterable<E> iter) {
        
    
      return  StreamSupport.stream(iter.spliterator(),false)
               .collect(Collectors.toList());
        
}
    
}
