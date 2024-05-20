package data.location

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.delacrixmorgan.twilight.TwilightDatabase
import data.location.mapper.LocationTypeEntityToModelMapper
import data.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface LocationRepository {
    fun getLocation(id: String): Flow<Location?>
    fun getLocations(): Flow<List<Location>>
    suspend fun addLocation(location: Location)
    suspend fun updateLocation(location: Location)
    suspend fun deleteLocation(id: String)
}

// https://github.com/joreilly/PeopleInSpace/blob/main/common/src/commonMain/kotlin/com/surrus/common/repository/PeopleInSpaceRepository.kt
internal class LocationRepositoryImpl : LocationRepository, KoinComponent {
    private val sqlDriver: SqlDriver by inject()
    private val locationTypeEntityToModelMapper: LocationTypeEntityToModelMapper by inject()

    override fun getLocation(id: String): Flow<Location?> {
        TODO("Not yet implemented")
    }

    override fun getLocations(): Flow<List<Location>> {
        return TwilightDatabase(sqlDriver).locationEntityQueries.selectAll(
            mapper = { id, label, customRegionName, type, zoneIdString ->
                Location(id, label, customRegionName, locationTypeEntityToModelMapper(type), zoneIdString)
            }
        ).asFlow().mapToList(Dispatchers.Default)
    }

    override suspend fun addLocation(location: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocation(location: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLocation(id: String) {
        TODO("Not yet implemented")
    }
}