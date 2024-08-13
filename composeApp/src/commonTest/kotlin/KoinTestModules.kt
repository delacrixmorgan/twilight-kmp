import data.timescape.TimescapeRepository
import org.koin.dsl.module

val repositoryModules = module { single { TimescapeRepository() } }