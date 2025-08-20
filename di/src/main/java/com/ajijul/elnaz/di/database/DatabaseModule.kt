package com.ajijul.elnaz.di.database

import android.app.Application
import androidx.room.Room
import com.ajijul.elnaz.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "elnaz_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase) = database.categoryDao()

    @Provides
    @Singleton
    fun provideCustomerDao(database: AppDatabase) = database.customerDao()

    @Provides
    @Singleton
    fun provideDiscountAssignmentDao(database: AppDatabase) = database.discountAssignmentDao()

    @Provides
    @Singleton
    fun provideDiscountDao(database: AppDatabase) = database.discountDao()

    @Provides
    @Singleton
    fun provideInventoryDao(database: AppDatabase) = database.inventoryDao()

    @Provides
    @Singleton
    fun provideOrderDao(database: AppDatabase) = database.orderDao()

    @Provides
    @Singleton
    fun provideOrderItemDao(database: AppDatabase) = database.orderItemDao()

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Provides
    @Singleton
    fun provideSupplierDao(database: AppDatabase) = database.supplierDao()

    @Provides
    @Singleton
    fun provideWarehouseDao(database: AppDatabase) = database.warehouseDao()

    @Provides
    @Singleton
    fun provideWarehousePricingDao(database: AppDatabase) = database.warehousePricingDao()


}