package com.hohuyhoangg.salesmanager18110284.interfaces;

import java.util.ArrayList;

/**
 * Provide method to word with table in database
 * @param <DtoType> Data type of DTO object
 * @param <IdType> Data type of identity column
 */
public interface IDataGet<IdType, DtoType> {
   /**
    * Select from database and convert to ArrayList&#60;DTOObject&#62;
    *
    * @return either (1) ArrayList&#60;DTOObject&#62; or (2) null for getting failed
    */
   ArrayList<DtoType> gets();

   /**
    * Insert new record to table
    *
    * @param id identity of Object
    * @return either (1) DTOObject get from first record in table or (2) null for not record match
    */
   DtoType getById(IdType id);
}
