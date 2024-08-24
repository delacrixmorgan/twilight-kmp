import data.kairos.KairosRepository
import org.koin.dsl.module

val repositoryModules = module { single { KairosRepository() } }