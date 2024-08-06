package di

import com.delacrixmorgan.twilight.TwilightDatabase
import data.location.LocationRepository
import data.location.LocationRepositoryImpl
import data.locationform.LocationFormRepository
import data.locationform.LocationFormRepositoryImpl
import data.preferences.PreferencesRepository
import data.preferences.PreferencesRepositoryImpl
import data.timescape.TimescapeRepository
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import platformModule
import ui.dashboard.settings.SettingsViewModel
import ui.dashboard.settings.appinfo.AppInfoViewModel
import ui.dashboard.today.TodayViewModel
import ui.form.name.SetupNameViewModel
import ui.form.summary.SummaryViewModel
import ui.form.timeregion.SelectTimeRegionViewModel

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            viewModelModule(),
            serviceModule(),
            repositoryModule(),
            mapperModule()
        )
    }

fun viewModelModule() = module {
    // Dashboard
    viewModel { TodayViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { AppInfoViewModel() }
    // Form
    viewModel { SetupNameViewModel() }
    viewModel { SelectTimeRegionViewModel() }
    viewModel { SummaryViewModel() }
}

fun serviceModule() = module {
    single<TwilightDatabase> { TwilightDatabase(get()) }
}

fun repositoryModule() = module {
    single<TimescapeRepository> { TimescapeRepository() }
    single<PreferencesRepository> { PreferencesRepositoryImpl() }
    single<LocationRepository> { LocationRepositoryImpl() }
    single<LocationFormRepository> { LocationFormRepositoryImpl() }
}

fun mapperModule() = module {
}