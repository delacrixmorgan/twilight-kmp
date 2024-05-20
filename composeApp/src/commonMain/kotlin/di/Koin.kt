package di

import data.createnewlocation.CreateNewLocationRepository
import data.createnewlocation.CreateNewLocationRepositoryImpl
import data.location.mapper.LocationTypeEntityToModelMapper
import data.timeregion.TimescapeRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import platformModule

// iOS
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule(enableNetworkLogs = enableNetworkLogs),
            mapperModule(),
            platformModule()
        )
    }

fun repositoryModule(enableNetworkLogs: Boolean) = module {
    single<CreateNewLocationRepository> { CreateNewLocationRepositoryImpl() }
    single<TimescapeRepository> { TimescapeRepository() }
}

fun mapperModule() = module {
    single { LocationTypeEntityToModelMapper() }
}