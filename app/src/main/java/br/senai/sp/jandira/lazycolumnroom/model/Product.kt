package br.senai.sp.jandira.lazycolumnroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_product")
data class Product(


    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "Nome")
    var productName: String = "Product's name",

    @ColumnInfo(name = "Descrição")
    var productDescription: String = "Description's product",

    @ColumnInfo(name = "Preço")
    var productPrice: Double = 0.0
)

//ORM