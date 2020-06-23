package dev.hyuwah.moviedbexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie

@Dao
interface FavoritesDao {

    @Insert
    suspend fun insert(movie: FavoriteMovie)

    // TODO Get Favorites (flow / paging 3.0)

    @Query("SELECT * FROM favorite_movie_table WHERE movie_id = :id")
    fun getFavoriteById(id: Int): FavoriteMovie?

    @Query("DELETE FROM favorite_movie_table WHERE movie_id = :id")
    suspend fun delete(id: Int)

}