package com.bidfrail.android.data.local.room.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.bidfrail.android.AppConstants;
import com.bidfrail.android.data.local.room.converter.DateConverter;
import com.bidfrail.android.data.local.room.dao.CartDao;
import com.bidfrail.android.data.local.room.entity.Cart;

@Database(entities = {Cart.class}, version = 1, exportSchema = true)
@TypeConverters({DateConverter.class})
public abstract class MyRoomDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();

    private static volatile MyRoomDatabase INSTANCE;

    public static MyRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null)
        {
            synchronized (LOCK)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, AppConstants.Database.ROOM_DATABASE_FILE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
    public abstract CartDao getCartDao();
}
