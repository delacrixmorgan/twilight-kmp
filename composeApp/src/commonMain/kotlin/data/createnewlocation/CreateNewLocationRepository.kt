package data.createnewlocation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import data.model.LocationType
import data.model.NewLocationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface CreateNewLocationRepository {
    suspend fun saveName(value: String)
    suspend fun saveLocationType(value: LocationType)
    suspend fun saveZoneId(value: String)

    fun getName(): Flow<String?>
    fun getLocationType(): Flow<LocationType?>
    fun getZoneId(): Flow<String?>

    fun observeLocation(): Flow<NewLocationData>
    suspend fun clear()
}

internal class CreateNewLocationRepositoryImpl : CreateNewLocationRepository, KoinComponent {

    companion object {
        const val KEY_NAME = "Name"
        const val KEY_LOCATION_TYPE = "LocationType"
        const val KEY_ZONE_ID = "ZoneId"
    }

    private val dataStore: DataStore<Preferences> by inject()

    override suspend fun saveName(value: String) {
        dataStore.edit { it[stringPreferencesKey(KEY_NAME)] = value }
    }

    override suspend fun saveLocationType(value: LocationType) {
        dataStore.edit { it[stringPreferencesKey(KEY_LOCATION_TYPE)] = value.name }
    }

    override suspend fun saveZoneId(value: String) {
        dataStore.edit { it[stringPreferencesKey(KEY_ZONE_ID)] = value }
    }

    override fun getName(): Flow<String?> =
        dataStore.data.map { it[stringPreferencesKey(KEY_NAME)] }

    override fun getLocationType(): Flow<LocationType?> =
        dataStore.data.map { preferences ->
            LocationType.entries.firstOrNull { it.name == preferences[stringPreferencesKey(KEY_LOCATION_TYPE)] }
        }

    override fun getZoneId(): Flow<String?> =
        dataStore.data.map { it[stringPreferencesKey(KEY_ZONE_ID)] }

    override fun observeLocation(): Flow<NewLocationData> {
        return combine(getName(), getLocationType(), getZoneId()) { name, locationType, zoneId ->
            NewLocationData(name, locationType, zoneId)
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}