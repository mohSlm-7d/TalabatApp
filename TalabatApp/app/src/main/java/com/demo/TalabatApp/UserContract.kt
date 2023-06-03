import android.net.Uri
import android.provider.BaseColumns

object UserContract {
    const val AUTHORITY = "com/demo/foodorderanddeliveryappkotlin.user"
    const val PATH = "users"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

    object UserEntry : BaseColumns {
        const val TABLE_NAME = "users"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_PHONE_NO = "phone_no"
    }
}
