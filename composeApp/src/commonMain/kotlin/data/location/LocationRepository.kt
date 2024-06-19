package data.location

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import data.model.Location
import di.TwilightDatabaseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LocationRepository {
    fun getLocation(location: Location): Flow<Location?>
    fun getLocations(): Flow<List<Location>>
    suspend fun addLocation(location: Location)
    suspend fun updateLocation(location: Location)
    suspend fun deleteLocation(id: String)
    suspend fun deleteLocations()
}

internal class LocationRepositoryImpl : LocationRepository, KoinComponent {
    private val twilightDatabase: TwilightDatabaseWrapper by inject()
    private val queries get() = twilightDatabase.instance?.locationEntityQueries

    override fun getLocation(location: Location): Flow<Location?> =
        queries?.selectById(
            id = location.id,
            mapper = { id, label, regionName, zoneIdString ->
                Location(id, label, regionName, zoneIdString)
            }
        )?.asFlow()?.mapToOne(Dispatchers.Default) ?: flowOf(null)

    override fun getLocations(): Flow<List<Location>> =
        queries?.selectAll(
            mapper = { id, label, regionName, zoneIdString ->
                Location(id, label, regionName, zoneIdString)
            }
        )?.asFlow()?.mapToList(Dispatchers.Default) ?: flowOf(emptyList())

    override suspend fun addLocation(location: Location) {
        queries?.insertItem(
            id = location.id,
            label = location.label,
            regionName = location.regionName,
            zoneIdString = location.zoneId
        )
    }

    override suspend fun updateLocation(location: Location) {
        queries?.update(
            label = location.label,
            regionName = location.regionName,
            zoneIdString = location.zoneId,
            id = location.id
        )
    }

    override suspend fun deleteLocation(id: String) {
        queries?.deleteById(id)
    }

    override suspend fun deleteLocations() {
        queries?.deleteAll()
    }
}