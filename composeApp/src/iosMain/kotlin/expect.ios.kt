import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.delacrixmorgan.twilight.Database
import data.utils.LocalDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun platformModule() = module {
    single { dataStore() }
    single { NativeSqliteDriver(Database.Schema, "twilight.db") }
}

@OptIn(ExperimentalForeignApi::class)
fun dataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/${LocalDataStore.CreateNewLocation.path()}"
    }
)