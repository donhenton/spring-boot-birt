/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.jpa.util;
import javax.persistence.AttributeConverter;
/**
 * http://www.thoughts-on-java.org/jpa-21-how-to-implement-type-converter/
 * @author dhenton
 */
import javax.persistence.Converter;

@Converter(autoApply = false)
public class TrimConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
         if (attribute != null)
         {
             return attribute.trim()+"yyy";
         }
         return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
         if (dbData != null)
         {
             return dbData.trim()+"yyy";
         }
         return dbData;
    }
    
}
