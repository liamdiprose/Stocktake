package com.liamdiprose.myitems

import androidx.room.*

@Entity(tableName = "categories")
public class Category (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "parent") var parentId: Long?
)

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE parent = :parent")
    fun getCategories(parent: Long?): List<Category>

    @Insert
    fun insertCategory(category: Category)
}
