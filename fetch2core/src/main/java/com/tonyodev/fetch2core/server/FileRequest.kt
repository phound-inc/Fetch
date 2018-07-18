package com.tonyodev.fetch2core.server

import android.os.Parcel
import android.os.Parcelable
import java.lang.StringBuilder

/**
 * Request object sent to the Fetch File Server as JSON.
 **/
data class FileRequest(val type: Int = TYPE_INVALID,
                       val fileResourceId: String = CATALOG_ID.toString(),
                       val rangeStart: Long = 0L,
                       val rangeEnd: Long = -1L,
                       val authorization: String = "",
                       val client: String = "",
                       val customData: String = "",
                       val page: Int = 0,
                       val size: Int = 0,
                       val persistConnection: Boolean = true) : Parcelable {

    val toJsonString: String
        get() {
            val builder = StringBuilder()
                    .append('{')
                    .append("\"Type\":").append(type).append(',')
                    .append("\"FileResourceId\":").append("\"$fileResourceId\"").append(',')
                    .append("\"RangeStart\":").append(rangeStart).append(',')
                    .append("\"RangeEnd\":").append(rangeEnd).append(',')
                    .append("\"Authorization\":").append("\"$authorization\"").append(',')
                    .append("\"Client\":").append("\"$client\"").append(',')
                    .append("\"CustomData\":").append("\"$customData\"").append(',')
                    .append("\"Page\":").append(page).append(',')
                    .append("\"Size\":").append(size).append(',')
                    .append("\"PersistConnection\":").append(persistConnection)
                    .append('}')
            return builder.toString()
        }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(type)
        dest.writeString(fileResourceId)
        dest.writeLong(rangeStart)
        dest.writeLong(rangeEnd)
        dest.writeString(authorization)
        dest.writeString(client)
        dest.writeString(customData)
        dest.writeInt(page)
        dest.writeInt(size)
        dest.writeInt(if (persistConnection) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FileRequest> {

        const val TYPE_INVALID = -1
        const val TYPE_PING = 0
        const val TYPE_FILE = 1
        const val TYPE_CATALOG = 2
        const val CATALOG_ID = -1L
        const val FIELD_TYPE = "Type"
        const val FIELD_FILE_RESOURCE_ID = "FileResourceId"
        const val FIELD_RANGE_START = "RangeStart"
        const val FIELD_RANGE_END = "RangeEnd"
        const val FIELD_AUTHORIZATION = "Authorization"
        const val FIELD_CLIENT = "Client"
        const val FIELD_CUSTOM_DATA = "CustomData"
        const val FIELD_PAGE = "Page"
        const val FIELD_SIZE = "Size"
        const val FIELD_PERSIST_CONNECTION = "PersistConnection"


        override fun createFromParcel(source: Parcel): FileRequest {
            return FileRequest(
                    type = source.readInt(),
                    fileResourceId = source.readString(),
                    rangeStart = source.readLong(),
                    rangeEnd = source.readLong(),
                    authorization = source.readString(),
                    client = source.readString(),
                    customData = source.readString(),
                    page = source.readInt(),
                    size = source.readInt(),
                    persistConnection = source.readInt() == 1)
        }

        override fun newArray(size: Int): Array<FileRequest?> {
            return arrayOfNulls(size)
        }

    }

}