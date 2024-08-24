import di.mapperModule
import di.repositoryModule
import di.serviceModule
import di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            platformModule(),
            viewModelModule(),
            serviceModule(),
            repositoryModule(),
            mapperModule()
        )
    }
}
