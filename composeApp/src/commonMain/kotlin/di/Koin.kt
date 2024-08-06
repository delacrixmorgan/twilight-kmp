package di

import com.delacrixmorgan.twilight.TwilightDatabase
import data.locationform.LocationFormRepository
import data.locationform.LocationFormRepositoryImpl
import data.location.LocationRepository
import data.location.LocationRepositoryImpl
import data.preferences.PreferencesRepository
import data.preferences.PreferencesRepositoryImpl
import data.timescape.TimescapeRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import platformModule

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            serviceModule(),
            repositoryModule(enableNetworkLogs = enableNetworkLogs),
            mapperModule()
        )
    }

fun serviceModule() = module {
    single<TwilightDatabase> { TwilightDatabase(get()) }
}

fun repositoryModule(enableNetworkLogs: Boolean) = module {
    single<TimescapeRepository> { TimescapeRepository() }
    single<PreferencesRepository> { PreferencesRepositoryImpl() }
    single<LocationRepository> { LocationRepositoryImpl() }
    single<LocationFormRepository> { LocationFormRepositoryImpl() }
}

fun mapperModule() = module {
}