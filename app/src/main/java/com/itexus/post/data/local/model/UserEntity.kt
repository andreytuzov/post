package com.itexus.post.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @Embedded(prefix = "address_")
    val address: AddressEntity,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "website")
    val website: String,
    @Embedded(prefix = "company_")
    val company: CompanyEntity
)

data class AddressEntity(
    @ColumnInfo(name = "street")
    val street: String,
    @ColumnInfo(name = "suite")
    val suite: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "zipcode")
    val zipcode: String,
    @Embedded(prefix = "location_")
    val location: LocationEntity
)

data class LocationEntity(
    @ColumnInfo(name = "latitude")
    val latitude: String,
    @ColumnInfo(name = "longitude")
    val longitude: String
)

data class CompanyEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "catch_phrase")
    val catchPhrase: String,
    @ColumnInfo(name = "bs")
    val bs: String
)