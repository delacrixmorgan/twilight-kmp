import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.delacrixmorgan.twilight.TwilightDatabase
import data.utils.LocalDataStore
import di.TwilightDatabaseWrapper
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSBundle
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask

actual fun platformModule() = module {
    single(named(LocalDataStore.Preferences.name)) { dataStore(LocalDataStore.Preferences.path()) }
    single(named(LocalDataStore.CreateNewLocation.name)) { dataStore(LocalDataStore.CreateNewLocation.path()) }
    single {
        val driver = NativeSqliteDriver(TwilightDatabase.Schema, "twilight.db")
        TwilightDatabaseWrapper(TwilightDatabase(driver))
    }
}

@OptIn(ExperimentalForeignApi::class)
fun dataStore(path: String): DataStore<Preferences> = createDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$path"
    }
)

actual fun randomUUID(): String = NSUUID().UUIDString()

actual fun getVersionCode(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as? String ?: "Unknown"
}

actual fun getVersionName(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: "Unknown"
}