import di.mapperModule
import di.repositoryModule
import di.serviceModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            platformModule(),
            serviceModule(),
            repositoryModule(enableNetworkLogs = false),
            mapperModule()
        )
    }
}