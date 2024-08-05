import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.delacrixmorgan.twilight.TwilightDatabase
import data.utils.LocalDataStore
import di.TwilightDatabaseWrapper
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.UUID

actual fun platformModule() = module {
    single(named(LocalDataStore.Preferences.name)) { dataStore(get(), LocalDataStore.Preferences.path()) }
    single(named(LocalDataStore.CreateNewLocation.name)) { dataStore(get(), LocalDataStore.CreateNewLocation.path()) }
    single {
        val driver = AndroidSqliteDriver(TwilightDatabase.Schema, get(), "twilight.db")
        TwilightDatabaseWrapper(TwilightDatabase(driver))
    }
}

fun dataStore(context: Context, path: String): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(path).absolutePath }
)

actual fun randomUUID(): String = UUID.randomUUID().toString()