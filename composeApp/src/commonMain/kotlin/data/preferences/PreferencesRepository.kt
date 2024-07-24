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
    suspend fun saveLocationType(value: LocationTypePreference)

    fun getTheme(): Flow<ThemePreference>
    fun getDateFormat(): Flow<DateFormatPreference>
    fun getLocationType(): Flow<LocationTypePreference>
    suspend fun clear()
}

internal class PreferencesRepositoryImpl : PreferencesRepository, KoinComponent {
    companion object {
        const val KEY_THEME = "Theme"
        const val KEY_DATE_FORMAT = "DateFormat"
        const val KEY_LOCATION_TYPE = "LocationType"
    }

    private val dataStore: DataStore<Preferences> by inject(qualifier = named(LocalDataStore.Preferences.name))

    override suspend fun saveTheme(value: ThemePreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_THEME)] = value.name }
    }

    override suspend fun saveDateFormat(value: DateFormatPreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_DATE_FORMAT)] = value.name }
    }

    override suspend fun saveLocationType(value: LocationTypePreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_LOCATION_TYPE)] = value.name }
    }

    override fun getTheme(): Flow<ThemePreference> =
        dataStore.data.map {
            ThemePreference.valueOf(it[stringPreferencesKey(KEY_THEME)] ?: ThemePreference.Default.name)
        }

    override fun getDateFormat(): Flow<DateFormatPreference> =
        dataStore.data.map {
            DateFormatPreference.valueOf(it[stringPreferencesKey(KEY_DATE_FORMAT)] ?: DateFormatPreference.Default.name)
        }

    override fun getLocationType(): Flow<LocationTypePreference> =
        dataStore.data.map {
            LocationTypePreference.valueOf(it[stringPreferencesKey(KEY_LOCATION_TYPE)] ?: LocationTypePreference.Default.name)
        }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}