import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.delacrixmorgan.twilight.Database
import data.utils.LocalDataStore
import org.koin.dsl.module

actual fun platformModule() = module {
    single { dataStore(get()) }
    single { AndroidSqliteDriver(Database.Schema, get(), "twilight.db") }
}

fun dataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(LocalDataStore.CreateNewLocation.path()).absolutePath }
)