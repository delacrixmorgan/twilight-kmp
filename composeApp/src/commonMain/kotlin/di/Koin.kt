package di

import com.delacrixmorgan.twilight.TwilightDatabase
import data.createnewlocation.CreateNewLocationRepository
import data.createnewlocation.CreateNewLocationRepositoryImpl
import data.location.LocationRepository
import data.location.LocationRepositoryImpl
import data.location.mapper.LocationTypeEntityToModelMapper
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
    single<CreateNewLocationRepository> { CreateNewLocationRepositoryImpl() }
    single<LocationRepository> { LocationRepositoryImpl() }
    single<TimescapeRepository> { TimescapeRepository() }
}

fun mapperModule() = module {
    single { LocationTypeEntityToModelMapper() }
}