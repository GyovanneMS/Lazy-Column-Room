package br.senai.sp.jandira.lazycolumnroom.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.lazycolumnroom.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDb: RoomDatabase() {


    abstract fun productDao(): ProductDao

    companion object{
        //Criar antes do banco
        private lateinit var instance: ProductDb

        fun getDatabase(context: Context): ProductDb{

            //Verificar se a instância já foi criada
            if(!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        ProductDb::class.java,
                        "db_product",
                        ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance
        }
    }

}