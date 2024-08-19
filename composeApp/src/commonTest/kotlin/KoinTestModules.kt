import data.kalika.KairosRepository
import org.koin.dsl.module

val repositoryModules = module { single { KairosRepository() } }