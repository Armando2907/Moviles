package ni.edu.uca.moviles2.arboretocarmelopalma.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName =  "trees")
data class TreeEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "family")
    var family: String,
    @ColumnInfo(name = "scientific_name")
    var scientificName: String,
    @ColumnInfo(name = "common_name")
    var commonName: String,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "url")
    var url: String
): Parcelable