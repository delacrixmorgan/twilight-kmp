import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
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

actual fun openUrlInBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        data = Uri.parse(url)
    }

    AppContext.get().startActivity(intent)
}

actual object AppContext {
    private lateinit var application: Application

    @Synchronized
    fun init(context: Context) {
        check(!::application.isInitialized) { "Application already initialized" }
        application = context.applicationContext as Application
    }

    fun get(): Context {
        if (::application.isInitialized.not()) throw Exception("Application context isn't initialized")
        return application.applicationContext
    }
}