package data.locationform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import data.locationform.model.NewLocationData
import data.utils.LocalDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

interface LocationFormRepository {
    suspend fun saveID(value: String)
    suspend fun saveName(value: String)
    suspend fun saveRegionName(value: String)
    suspend fun saveZoneId(value: String)

    fun getID(): Flow<String?>
    fun getName(): Flow<String?>
    fun getRegionName(): Flow<String?>
    fun getZoneId(): Flow<String?>

    fun observeLocation(): Flow<NewLocationData>

    suspend fun clear()
}

internal class LocationFormRepositoryImpl : LocationFormRepository, KoinComponent {

    companion object {
        const val KEY_ID = "vrYeumMjGctxQbwKqapb"
        const val KEY_NAME = "jpdDoCQTqeJrGsnaQcBW"
        const val KEY_REGION_NAME = "VzDMLXFKRseRmWRBEFuX"
        const val KEY_ZONE_ID = "XPXkEZAfgQPLvDsvaHAZ"
    }

    private val dataStore: DataStore<Preferences> by inject(qualifier = named(LocalDataStore.CreateNewLocation.name))

    override suspend fun saveID(value: String) {
        dataStore.edit { it[stringPreferencesKey(KEY_ID)] = value }
    }

    override suspend fun saveName(value: String) {
        dataStore.edit { it[stringPreferencesKey(KEY_NAME)] = value }
    }

    override suspend fun saveRegionName(value: String) {
        dataStore.edit { it[stringPreferencesKey(KEY_REGION_NAME)] = value }
    }

    override suspend fun saveZoneId(value: String) {
        dataStore.edit { it[stringPreferencesKey(KEY_ZONE_ID)] = value }
    }

    override fun getID(): Flow<String?> =
        dataStore.data.map { it[stringPreferencesKey(KEY_ID)] }

    override fun getName(): Flow<String?> =
        dataStore.data.map { it[stringPreferencesKey(KEY_NAME)] }

    override fun getRegionName(): Flow<String?> =
        dataStore.data.map { it[stringPreferencesKey(KEY_REGION_NAME)] }

    override fun getZoneId(): Flow<String?> =
        dataStore.data.map { it[stringPreferencesKey(KEY_ZONE_ID)] }

    override fun observeLocation(): Flow<NewLocationData> {
        return combine(getID(), getName(), getRegionName(), getZoneId()) { id, name, regionName, zoneId ->
            NewLocationData(id, name, regionName, zoneId)
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}