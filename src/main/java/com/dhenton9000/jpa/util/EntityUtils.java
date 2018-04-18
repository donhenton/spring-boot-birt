/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.jpa.util;

import java.util.ArrayList;
import java.util.Collection;

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
    
    public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
    ArrayList<E> list = new ArrayList<E>();
    for (E item : iter) {
        list.add(item);
    }
    return list;
}
    
}
