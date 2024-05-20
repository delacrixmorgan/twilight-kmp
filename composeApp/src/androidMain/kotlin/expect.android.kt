import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.delacrixmorgan.twilight.TwilightDatabase
import data.utils.LocalDataStore
import di.TwilightDatabaseWrapper
import org.koin.dsl.module
import java.util.UUID

actual fun platformModule() = module {
    single { dataStore(get()) }
    single {
        val driver = AndroidSqliteDriver(TwilightDatabase.Schema, get(), "twilight.db")
        TwilightDatabaseWrapper(TwilightDatabase(driver))
    }
}

fun dataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(LocalDataStore.CreateNewLocation.path()).absolutePath }
)

actual fun randomUUID(): String = UUID.randomUUID().toString()