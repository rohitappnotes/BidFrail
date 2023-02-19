package com.bidfrail.android.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.bidfrail.android.data.local.room.entity.Cart;
import java.util.Date;
import java.util.List;

@Dao
public interface CartDao {

    /*============================================ Insert ========================================*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCart(Cart cart);

    /*============================================ Update ========================================*/
    @Query("UPDATE cart SET quantity=:quantity, total_price= :totalPrice, updated_at= :updatedAt WHERE id = :subServiceId")
    public int updateCart(int quantity, int totalPrice, Date updatedAt, int subServiceId);

    /*============================================= Read =========================================*/
    @Query("SELECT * FROM cart")
    public LiveData<List<Cart>> getCart();

    @Query("SELECT COUNT(*) FROM cart")
    public LiveData<Integer> getCartCount();
    /*============================================ Delete ========================================*/
    @Query("DELETE FROM cart WHERE id = :subServiceId")
    public int deleteCart(int subServiceId);
}