import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.utils.LocalDataStore
import org.koin.dsl.module

actual fun platformModule() = module {
    single { dataStore(get()) }
}

fun dataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(LocalDataStore.CreateNewLocation.path()).absolutePath }
)