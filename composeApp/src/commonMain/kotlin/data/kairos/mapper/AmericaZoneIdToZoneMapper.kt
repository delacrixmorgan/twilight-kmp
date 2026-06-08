package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AmericaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.America
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/New_York" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Ohio", "Cincinnati"),
            )
            "$region/Los_Angeles" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("California", "San Francisco", "Cupertino", "Mountain View"),
            )
            "$region/Chicago" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Central Time", "Illinois", "Texas", "Chicago", "Houston", "Dallas", "San Antonio", "Austin", "Nashville", "New Orleans", "Memphis", "Milwaukee"),
            )
            "$region/Denver" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Mountain Time", "Colorado", "Denver", "Colorado Springs", "Aurora", "Albuquerque", "Salt Lake City", "Boise", "El Paso"),
            )
            "$region/Phoenix" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Arizona", "Phoenix", "Tucson", "Mesa", "Chandler", "Scottsdale", "Gilbert", "Glendale", "Tempe"),
            )
            "$region/Anchorage" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Alaska", "Anchorage", "Fairbanks", "Juneau", "Wasilla", "Sitka", "Ketchikan"),
            )
            "$region/Detroit" -> Zone(
                zoneIdString, region,
                country = listOf("United States", "US", "\uD83C\uDDFA\uD83C\uDDF8"),
                keywords = listOf("Eastern Time", "Michigan", "Detroit", "Grand Rapids", "Ann Arbor", "Lansing", "Flint"),
            )
            "$region/Toronto" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),
                keywords = listOf("Ontario", "Quebec", "Toronto", "Montreal", "Ottawa", "Mississauga", "Hamilton", "Quebec City", "Markham", "Brampton"),
            )
            "$region/Vancouver" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),
                keywords = listOf("British Columbia", "Vancouver", "Surrey", "Burnaby", "Victoria", "Richmond", "Kelowna", "Abbotsford"),
            )
            "$region/Edmonton" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),
                keywords = listOf("Alberta", "Calgary", "Edmonton", "Red Deer", "Lethbridge", "Fort McMurray"),
            )
            "$region/Winnipeg" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),
                keywords = listOf("Manitoba", "Winnipeg", "Brandon", "Steinbach", "Thompson"),
            )
            "$region/Halifax" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),
                keywords = listOf("Atlantic Time", "Nova Scotia", "Halifax", "Moncton", "Saint John", "Fredericton", "Charlottetown"),
            )
            "$region/St_Johns" -> Zone(
                zoneIdString, region,
                country = listOf("Canada", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),
                keywords = listOf("Newfoundland", "St. John's", "Mount Pearl", "Corner Brook", "Conception Bay South"),
            )
            "$region/Mexico_City" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "\uD83C\uDDF2\uD83C\uDDFD"),
                keywords = listOf("Mexico City", "Guadalajara", "Puebla", "Ecatepec", "Le\u00F3n", "Toluca", "Quer\u00E9taro", "M\u00E9rida", "Ciudad Ju\u00E1rez"),
            )
            "$region/Tijuana" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "\uD83C\uDDF2\uD83C\uDDFD"),
                keywords = listOf("Baja California", "Tijuana", "Mexicali", "Ensenada", "Rosarito"),
            )
            "$region/Cancun" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "\uD83C\uDDF2\uD83C\uDDFD"),
                keywords = listOf("Quintana Roo", "Canc\u00FAn", "Playa del Carmen", "Chetumal", "Cozumel", "Tulum"),
            )
            "$region/Monterrey" -> Zone(
                zoneIdString, region,
                country = listOf("Mexico", "MX", "\uD83C\uDDF2\uD83C\uDDFD"),
                keywords = listOf("Nuevo Le\u00F3n", "Monterrey", "Guadalupe", "San Nicol\u00E1s de los Garza", "Apodaca"),
            )
            "$region/Panama" -> Zone(
                zoneIdString, region,
                country = listOf("Panama", "PA", "\uD83C\uDDF5\uD83C\uDDE6"),
                keywords = listOf("Panama City", "San Miguelito", "Tocumen", "David", "Col\u00F3n"),
            )
            "$region/Costa_Rica" -> Zone(
                zoneIdString, region,
                country = listOf("Costa Rica", "CR", "\uD83C\uDDE8\uD83C\uDDF7"),
                keywords = listOf("San Jos\u00E9", "Lim\u00F3n", "Alajuela", "Heredia", "Cartago", "Puntarenas"),
            )
            "$region/Guatemala" -> Zone(
                zoneIdString, region,
                country = listOf("Guatemala", "GT", "\uD83C\uDDEC\uD83C\uDDF9"),
                keywords = listOf("Guatemala City", "Mixco", "Villa Nueva", "Quetzaltenango", "Escuintla"),
            )
            "$region/El_Salvador" -> Zone(
                zoneIdString, region,
                country = listOf("El Salvador", "SV", "\uD83C\uDDF8\uD83C\uDDFB"),
                keywords = listOf("San Salvador", "Santa Ana", "San Miguel", "Soyapango", "Mejicanos"),
            )
            "$region/Tegucigalpa" -> Zone(
                zoneIdString, region,
                country = listOf("Honduras", "HN", "\uD83C\uDDED\uD83C\uDDF3"),
                keywords = listOf("Tegucigalpa", "San Pedro Sula", "Choloma", "La Ceiba", "El Progreso"),
            )
            "$region/Managua" -> Zone(
                zoneIdString, region,
                country = listOf("Nicaragua", "NI", "\uD83C\uDDF3\uD83C\uDDEE"),
                keywords = listOf("Managua", "Le\u00F3n", "Masaya", "Chinandega", "Matagalpa", "Granada"),
            )
            "$region/Havana" -> Zone(
                zoneIdString, region,
                country = listOf("Cuba", "CU", "\uD83C\uDDE8\uD83C\uDDFA"),
                keywords = listOf("Havana", "Santiago de Cuba", "Camag\u00FCey", "Holgu\u00EDn", "Santa Clara", "Bayamo"),
            )
            "$region/Santo_Domingo" -> Zone(
                zoneIdString, region,
                country = listOf("Dominican Republic", "DO", "\uD83C\uDDE9\uD83C\uDDF4"),
                keywords = listOf("Santo Domingo", "Santiago", "La Romana", "San Pedro de Macor\u00EDs", "Puerto Plata"),
            )
            "$region/Jamaica" -> Zone(
                zoneIdString, region,
                country = listOf("Jamaica", "JM", "\uD83C\uDDEF\uD83C\uDDF2"),
                keywords = listOf("Kingston", "Montego Bay", "Spanish Town", "Portmore", "Mandeville"),
            )
            "$region/Puerto_Rico" -> Zone(
                zoneIdString, region,
                country = listOf("Puerto Rico", "PR", "\uD83C\uDDF5\uD83C\uDDF7"),
                keywords = listOf("San Juan", "Bayam\u00F3n", "Carolina", "Ponce", "Caguas", "Mayag\u00FCez"),
            )
            "$region/Port-au-Prince" -> Zone(
                zoneIdString, region,
                country = listOf("Haiti", "HT", "\uD83C\uDDED\uD83C\uDDF9"),
                keywords = listOf("Port-au-Prince", "Carrefour", "Delmas", "P\u00E9tion-Ville", "Cap-Ha\u00EFtien"),
            )
            "$region/Sao_Paulo" -> Zone(
                zoneIdString, region,
                country = listOf("Brazil", "BR", "\uD83C\uDDE7\uD83C\uDDF7"),
                keywords = listOf("S\u00E3o Paulo", "Rio de Janeiro", "Bras\u00EDlia", "Salvador", "Fortaleza", "Belo Horizonte", "Curitiba", "Recife", "Porto Alegre"),
            )
            "$region/Argentina/Buenos_Aires" -> Zone(
                zoneIdString, region,
                country = listOf("Argentina", "AR", "\uD83C\uDDE6\uD83C\uDDF7"),
                keywords = listOf("Buenos Aires", "C\u00F3rdoba", "Rosario", "Mendoza", "La Plata", "Mar del Plata", "Salta", "San Miguel de Tucum\u00E1n"),
            )
            "$region/Santiago" -> Zone(
                zoneIdString, region,
                country = listOf("Chile", "CL", "\uD83C\uDDE8\uD83C\uDDF1"),
                keywords = listOf("Santiago", "Valpara\u00EDso", "Concepci\u00F3n", "Antofagasta", "Vi\u00F1a del Mar", "Temuco", "La Serena"),
            )
            "$region/Bogota" -> Zone(
                zoneIdString, region,
                country = listOf("Colombia", "CO", "\uD83C\uDDE8\uD83C\uDDF4"),
                keywords = listOf("Bogot\u00E1", "Medell\u00EDn", "Cali", "Barranquilla", "Cartagena", "C\u00FAcuta", "Bucaramanga"),
            )
            "$region/Lima" -> Zone(
                zoneIdString, region,
                country = listOf("Peru", "PE", "\uD83C\uDDF5\uD83C\uDDEA"),
                keywords = listOf("Lima", "Arequipa", "Trujillo", "Chiclayo", "Piura", "Cusco", "Iquitos"),
            )
            "$region/Caracas" -> Zone(
                zoneIdString, region,
                country = listOf("Venezuela", "VE", "\uD83C\uDDFB\uD83C\uDDEA"),
                keywords = listOf("Caracas", "Maracaibo", "Valencia", "Barquisimeto", "Maracay", "Ciudad Guayana"),
            )
            "$region/Guayaquil" -> Zone(
                zoneIdString, region,
                country = listOf("Ecuador", "EC", "\uD83C\uDDEA\uD83C\uDDE8"),
                keywords = listOf("Guayaquil", "Quito", "Cuenca", "Santo Domingo", "Machala", "Manta"),
            )
            "$region/La_Paz" -> Zone(
                zoneIdString, region,
                country = listOf("Bolivia", "BO", "\uD83C\uDDE7\uD83C\uDDF4"),
                keywords = listOf("La Paz", "Santa Cruz", "Cochabamba", "Sucre", "El Alto", "Oruro"),
            )
            "$region/Montevideo" -> Zone(
                zoneIdString, region,
                country = listOf("Uruguay", "UY", "\uD83C\uDDFA\uD83C\uDDFE"),
                keywords = listOf("Montevideo", "Salto", "Paysand\u00FA", "Las Piedras", "Rivera", "Maldonado"),
            )
            "$region/Asuncion" -> Zone(
                zoneIdString, region,
                country = listOf("Paraguay", "PY", "\uD83C\uDDF5\uD83C\uDDFE"),
                keywords = listOf("Asunci\u00F3n", "Ciudad del Este", "San Lorenzo", "Luque", "Capiat\u00E1", "Lambar\u00E9"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}