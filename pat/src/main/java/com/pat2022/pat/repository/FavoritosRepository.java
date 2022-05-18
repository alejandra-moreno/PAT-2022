package com.pat2022.pat.repository;

import com.pat2022.pat.model.FavoritosModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritosRepository extends CrudRepository<FavoritosModel,String>{

    @Modifying
    @Query("INSERT INTO FAVOURITE (USER_ID,FAV_ID,TIPO) VALUES ( :userId, :favId, :tipo)")
    public void createFav(String userId, String favId, String tipo);

    
    @Query("SELECT * FROM FAVOURITE WHERE FAVOURITE.USER_ID = : userId AND  FAVOURITE.FAV_ID = : favId")
    public FavoritosModel getFavById(String userId, String favId);

    @Modifying
    @Query("DELETE FROM FAVOURITE WHERE FAVOURITE.USER_ID = : userId AND  FAVOURITE.FAV_ID = : favId")
    public void deleteFav(String userId, String favId);

    
}
