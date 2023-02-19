package com.bidfrail.android.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.bidfrail.android.R;
import com.bidfrail.android.data.local.room.dao.CartDao;
import com.bidfrail.android.data.local.room.database.MyRoomDatabase;
import com.bidfrail.android.data.local.room.entity.Cart;
import com.bidfrail.android.ui.loginregister.introduction.Intro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import dagger.hilt.android.qualifiers.ApplicationContext;

public class LocalRepository {

    private Context context;
    private CartDao cartDao;
    private MyRoomDatabase myRoomDatabase;

    @Inject
    public LocalRepository(@ApplicationContext Context context) {
        this.context = context;
        myRoomDatabase = MyRoomDatabase.getInstance(context);
        cartDao = myRoomDatabase.getCartDao();
    }

    public ArrayList<Intro> getIntro() {
        ArrayList<Intro> introSliderArrayList = new ArrayList<>();

        introSliderArrayList.add(
                new Intro(
                        R.color.white,
                        R.drawable.intro_one,
                        context.getString(R.string.login_register_title_intro_one),
                        context.getString(R.string.login_register_desc_intro_one))
        );

        introSliderArrayList.add(
                new Intro(
                        R.color.white,
                        R.drawable.intro_two,
                        context.getString(R.string.login_register_title_intro_two),
                        context.getString(R.string.login_register_desc_intro_two))
        );

        introSliderArrayList.add(
                new Intro(
                        R.color.white,
                        R.drawable.intro_three,
                        context.getString(R.string.login_register_title_intro_three),
                        context.getString(R.string.login_register_desc_intro_three))
        );

        return introSliderArrayList;
    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public LiveData<Integer> getCartCount() {
        return cartDao.getCartCount();
    }

    public LiveData<List<Cart>> getListOfCart() {
        return cartDao.getCart();
    }

    public void insertCart(Cart cart) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long insert = cartDao.insertCart(cart);
            }
        }).start();
    }

    public void updateQuantity(int quantity, int totalPrice, Date updatedAt, int cartId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long insert = cartDao.updateCart(quantity, totalPrice, updatedAt, cartId);
            }
        }).start();
    }

    public void clearAllTables() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                myRoomDatabase.clearAllTables();
            }
        }).start();
    }
}
