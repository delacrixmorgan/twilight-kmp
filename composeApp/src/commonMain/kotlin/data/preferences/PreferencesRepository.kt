package data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import data.utils.LocalDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

interface PreferencesRepository {
    suspend fun saveTheme(value: ThemePreference)
    suspend fun saveDateFormat(value: DateFormatPreference)
    suspend fun saveLocationFormat(value: LocationFormatPreference)

    fun getTheme(): Flow<ThemePreference>
    fun getDateFormat(): Flow<DateFormatPreference>
    fun getLocationFormat(): Flow<LocationFormatPreference>
    suspend fun clear()
}

internal class PreferencesRepositoryImpl : PreferencesRepository, KoinComponent {
    companion object {
        const val KEY_THEME = "CCYQrgtgsNRFEDuyTpWa"
        const val KEY_DATE_FORMAT = "fqHCanepiXQpFWDhYMBk"
        const val KEY_LOCATION_FORMAT = "gUdTtuUsuBtaxjjxCsdE"
    }

    private val dataStore: DataStore<Preferences> by inject(qualifier = named(LocalDataStore.Preferences.name))

    override suspend fun saveTheme(value: ThemePreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_THEME)] = value.name }
    }

    override suspend fun saveDateFormat(value: DateFormatPreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_DATE_FORMAT)] = value.name }
    }

    override suspend fun saveLocationFormat(value: LocationFormatPreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_LOCATION_FORMAT)] = value.name }
    }

    override fun getTheme(): Flow<ThemePreference> =
        dataStore.data.map {
            ThemePreference.valueOf(it[stringPreferencesKey(KEY_THEME)] ?: ThemePreference.Default.name)
        }

    override fun getDateFormat(): Flow<DateFormatPreference> =
        dataStore.data.map {
            DateFormatPreference.valueOf(it[stringPreferencesKey(KEY_DATE_FORMAT)] ?: DateFormatPreference.Default.name)
        }

    override fun getLocationFormat(): Flow<LocationFormatPreference> =
        dataStore.data.map {
            LocationFormatPreference.valueOf(it[stringPreferencesKey(KEY_LOCATION_FORMAT)] ?: LocationFormatPreference.Default.name)
        }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}