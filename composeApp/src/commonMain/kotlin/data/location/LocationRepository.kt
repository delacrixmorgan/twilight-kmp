package data.location

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import data.location.mapper.LocationTypeEntityToModelMapper
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
    private val locationTypeEntityToModelMapper: LocationTypeEntityToModelMapper by inject()
    private val twilightDatabase: TwilightDatabaseWrapper by inject()
    private val queries get() = twilightDatabase.instance?.locationEntityQueries

    override fun getLocation(location: Location): Flow<Location?> =
        queries?.selectById(
            id = location.id,
            mapper = { id, label, customRegionName, type, zoneIdString ->
                Location(id, label, customRegionName, locationTypeEntityToModelMapper(type), zoneIdString)
            }
        )?.asFlow()?.mapToOne(Dispatchers.Default) ?: flowOf(null)

    override fun getLocations(): Flow<List<Location>> =
        queries?.selectAll(
            mapper = { id, label, customRegionName, type, zoneIdString ->
                Location(id, label, customRegionName, locationTypeEntityToModelMapper(type), zoneIdString)
            }
        )?.asFlow()?.mapToList(Dispatchers.Default) ?: flowOf(emptyList())

    override suspend fun addLocation(location: Location) {
        queries?.insertItem(
            id = location.id,
            label = location.label,
            regionName = location.regionName,
            type = location.type.name,
            zoneIdString = location.zoneId
        )
    }

    override suspend fun updateLocation(location: Location) {
        queries?.update(
            label = location.label,
            regionName = location.regionName,
            type = location.type.name,
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