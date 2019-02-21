package com.peng.commonlib.daggerinject.module;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.peng.commonlib.constant.AppConstants;
import com.peng.commonlib.daggerinject.qualifier.DatabaseName;
import com.peng.commonlib.daggerinject.qualifier.DatabaseVersion;
import com.peng.commonlib.database.AppDatabase;
import com.peng.commonlib.database.repository.IUserRepo;
import com.peng.commonlib.database.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 * 1、数据库依赖提供者，由 AppComponent 引用
 */

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    @DatabaseName
    public String provideDatabaseName() {
        return AppConstants.APP_DB_NAME;
    }

    @Singleton
    @Provides
    @DatabaseVersion
    public int provideDatabaseVersion() {
        return AppConstants.APP_DB_VERSION;
    }

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Context context, @DatabaseName String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName)
                .fallbackToDestructiveMigration()
                .addMigrations( //  Migration 类用于迁移数据库,Room 会按照提供版本的顺序顺序执行每个 Migration 的 migrate()方法，将数据库升级到最新的版本
                        new Migration(1, 2) {
                            @Override
                            public void migrate(SupportSQLiteDatabase database) {
                                // 创建数据库的SQL语句
                                database.execSQL("CREATE TABLE Fruit (`id` INTEGER, " + "`name` TEXT, PRIMARY KEY(`id`))");
                            }
                        },
                        new Migration(2, 3) {
                            @Override
                            public void migrate(SupportSQLiteDatabase database) {
                                // 创建数据库的SQL语句
                                database.execSQL("ALTER TABLE Book " + " ADD COLUMN pub_year INTEGER");
                            }
                        })
//                .allowMainThreadQueries() // Room 不允许在主线程中访问数据库，除非在 buid 的时候使用 allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public IUserRepo provideUserRepo(UserRepository userRepository){
        return userRepository;
    }
}
