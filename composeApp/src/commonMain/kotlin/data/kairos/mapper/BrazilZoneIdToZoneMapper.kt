package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class BrazilZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Brazil
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Acre" -> Zone(
                zoneIdString, region,
                country = listOf("Brazil", "BR", "🇧🇷"),
                keywords = listOf("Acre", "Rio Branco", "Cruzeiro do Sul"),
            )
            "$region/DeNoronha" -> Zone(
                zoneIdString, region,
                country = listOf("Brazil", "BR", "🇧🇷"),
                keywords = listOf("Fernando de Noronha"),
            )
            "$region/East" -> Zone(
                zoneIdString, region,
                country = listOf("Brazil", "BR", "🇧🇷"),
                keywords = listOf("Brasília", "São Paulo", "Rio de Janeiro", "Salvador", "Fortaleza", "Belo Horizonte"),
            )
            "$region/West" -> Zone(
                zoneIdString, region,
                country = listOf("Brazil", "BR", "🇧🇷"),
                keywords = listOf("Manaus", "Amazonas", "Cuiabá", "Mato Grosso", "Porto Velho", "Boa Vista"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
