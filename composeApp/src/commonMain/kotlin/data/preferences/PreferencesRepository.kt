package data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import data.model.TwilightTheme
import data.utils.LocalDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

interface PreferencesRepository {
    suspend fun saveTheme(value: TwilightTheme)
    fun getTheme(): Flow<TwilightTheme>
    suspend fun clear()
}

internal class PreferencesRepositoryImpl : PreferencesRepository, KoinComponent {
    companion object {
        const val KEY_THEME = "Theme"
    }

    private val dataStore: DataStore<Preferences> by inject(qualifier = named(LocalDataStore.Preferences.name))

    override suspend fun saveTheme(value: TwilightTheme) {
        dataStore.edit { it[stringPreferencesKey(KEY_THEME)] = value.name }
    }

    override fun getTheme(): Flow<TwilightTheme> {
        return dataStore.data.map {
            TwilightTheme.valueOf(it[stringPreferencesKey(KEY_THEME)] ?: TwilightTheme.Default.name)
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

}