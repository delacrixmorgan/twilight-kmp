package data.timezone.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class EuropeZoneIdToTimezoneMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Europe
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Lisbon" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Portugal", "PT", "\uD83C\uDDF5\uD83C\uDDF9"),
                states = emptyList(),
                cities = listOf("Lisbon", "Porto", "Vila Nova de Gaia", "Amadora", "Braga", "Setúbal", "Coimbra", "Funchal", "Almada", "Aveiro")
            )
            "$region/Madrid" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Spain", "ES", "\uD83C\uDDEA\uD83C\uDDF8"),
                states = emptyList(),
                cities = listOf("Madrid", "Barcelona", "Valencia", "Seville", "Zaragoza", "Málaga", "Murcia", "Palma", "Las Palmas", "Bilbao")
            )
            "$region/Paris" -> TimeRegion(
                zoneIdString, region,
                country = listOf("France", "FR", "\uD83C\uDDEB\uD83C\uDDF7"),
                states = emptyList(),
                cities = listOf("Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille")
            )
            "$region/Andorra" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Andorra", "AD", "\uD83C\uDDE6\uD83C\uDDE9"),
                states = emptyList(),
                cities = listOf("Andorra la Vella", "Escaldes-Engordany", "Encamp", "Sant Julià de Lòria", "La Massana", "Santa Coloma", "Ordino", "Canillo")
            )
            "$region/Monaco" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Monaco", "MC", "\uD83C\uDDF2\uD83C\uDDE8"),
                states = emptyList(),
                cities = listOf("Monaco", "Monte Carlo", "La Condamine", "Fontvieille")
            )
            "$region/Luxembourg" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Luxembourg", "LU", "\uD83C\uDDF1\uD83C\uDDFA"),
                states = emptyList(),
                cities = listOf("Luxembourg City", "Esch-sur-Alzette", "Differdange", "Dudelange", "Ettelbruck")
            )
            "$region/Brussels" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Belgium", "BE", "\uD83C\uDDE7\uD83C\uDDEA"),
                states = emptyList(),
                cities = listOf("Brussels", "Antwerp", "Ghent", "Charleroi", "Liège", "Bruges", "Namur", "Leuven", "Mechelen", "Aalst")
            )
            "$region/Amsterdam" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Netherlands", "NL", "\uD83C\uDDF3\uD83C\uDDF1"),
                states = listOf("Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen", "Limburg", "North Brabant", "North Holland", "Overijssel", "Utrecht", "Zeeland", "South Holland"),
                cities = listOf("Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Tilburg", "Groningen", "Almere", "Breda", "Nijmegen", "Enschede", "Haarlem", "Arnhem", "Zaanstad", "Amersfoort", "Apeldoorn", "Zwolle", "Maastricht", "Dordrecht", "Leiden")
            )
            "$region/Berlin" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Germany", "DE", "\uD83C\uDDE9\uD83C\uDDEA"),
                states = listOf("Brandenburg", "Berlin", "Bavaria", "Saxony Anhalt", "Mecklenburg Vorpommern", "Hamburg", "Saxony", "North Rhine Westphalia", "Schleswig Holstein", "Bremen", "Baden Württemberg", "Hesse", "Lower Saxony", "Rhineland Palatinate", "Saarland", "Thuringia"),
                cities = listOf("Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt", "Stuttgart", "Düsseldorf", "Dortmund", "Essen", "Leipzig")
            )
            "$region/Prague" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Czech Republic", "Czechia", "CZ", "\uD83C\uDDE8\uD83C\uDDFF"),
                states = emptyList(),
                cities = listOf("Prague", "Brno", "Ostrava", "Plzeň", "Liberec", "Olomouc", "České Budějovice", "Ústí nad Labem", "Hradec Králové", "Pardubice")
            )
            "$region/Zurich" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Switzerland", "CH", "\uD83C\uDDE8\uD83C\uDDED"),
                states = emptyList(),
                cities = listOf("Zurich", "Geneva", "Basel", "Lausanne", "Bern", "Winterthur", "Lucerne", "St. Gallen", "Lugano", "Biel/Bienne")
            )
            "$region/Vaduz" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Liechtenstein", "LI", "\uD83C\uDDF1\uD83C\uDDEE"),
                states = emptyList(),
                cities = listOf("Vaduz", "Schaan", "Triesen", "Balzers", "Eschen", "Mauren", "Ruggell", "Gamprin", "Schellenberg", "Planken")
            )
            "$region/Vienna" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Austria", "AT", "\uD83C\uDDE6\uD83C\uDDF9"),
                states = emptyList(),
                cities = listOf("Vienna", "Graz", "Linz", "Salzburg", "Innsbruck", "Klagenfurt", "Villach", "Wels", "Sankt Pölten", "Dornbirn")
            )
            "$region/London",
            "$region/Isle_of_Man",
            "$region/Jersey" -> TimeRegion(
                zoneIdString, region,
                country = listOf("United Kingdom", "UK", "\uD83C\uDDEC\uD83C\uDDE7"),
                states = emptyList(),
                cities = emptyList()
            )
            "$region/Dublin" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Ireland", "IE", "\uD83C\uDDEE\uD83C\uDDEA"),
                states = emptyList(),
                cities = listOf("Dublin", "Cork", "Galway", "Limerick", "Waterford", "Drogheda", "Dundalk", "Sligo", "Bray", "Navan")
            )
            "$region/Copenhagen" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Denmark", "DK", "\uD83C\uDDE9\uD83C\uDDF0"),
                states = emptyList(),
                cities = emptyList()
            )
            "$region/Oslo" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Norway", "NO", "\uD83C\uDDF3\uD83C\uDDF4"),
                states = emptyList(),
                cities = listOf("Oslo", "Bergen", "Trondheim", "Stavanger", "Drammen", "Fredrikstad", "Kristiansand", "Sandnes", "Tromsø", "Sarpsborg")
            )
            "$region/Stockholm" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Sweden", "SE", "\uD83C\uDDF8\uD83C\uDDEA"),
                states = emptyList(),
                cities = listOf("Stockholm", "Gothenburg", "Malmö", "Uppsala", "Sollentuna", "Västerås", "Örebro", "Linköping", "Helsingborg", "Jönköping")
            )
            "$region/Helsinki" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Finland", "FI", "\uD83C\uDDEB\uD83C\uDDEE"),
                states = emptyList(),
                cities = listOf("Helsinki", "Espoo", "Tampere", "Vantaa", "Oulu", "Turku", "Jyväskylä", "Kuopio", "Lahti", "Pori")
            )
            "$region/Malta" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Malta", "MT", "\uD83C\uDDF2\uD83C\uDDF9"),
                states = emptyList(),
                cities = listOf("Valletta", "Birkirkara", "Mosta", "Qormi", "Żabbar", "San Ġwann", "Żejtun", "Sliema", "Luqa", "Gżira")
            )
            "$region/Rome" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Italy", "IT", "\uD83C\uDDEE\uD83C\uDDF9"),
                states = emptyList(),
                cities = listOf("Rome", "Milan", "Naples", "Turin", "Palermo", "Genoa", "Bologna", "Florence", "Bari", "Catania")
            )
            "$region/San_Marino" -> TimeRegion(
                zoneIdString, region,
                country = listOf("San Marino", "SM", "\uD83C\uDDF8\uD83C\uDDF2"),
                states = emptyList(),
                cities = listOf("San Marino", "Serravalle", "Borgo Maggiore", "Domagnano", "Fiorentino", "Acquaviva", "Chiesanuova", "Montegiardino", "San Leo")
            )
            "$region/Vatican" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Vatican City", "VA", "\uD83C\uDDFB\uD83C\uDDE6"),
                states = emptyList(),
                cities = listOf("Vatican City")
            )
            "$region/Ljubljana" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Slovenia", "SI", "\uD83C\uDDF8\uD83C\uDDEE"),
                states = emptyList(),
                cities = listOf("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Ptuj", "Trbovlje", "Kamnik")
            )
            "$region/Zagreb" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Croatia", "HR", "\uD83C\uDDED\uD83C\uDDF7"),
                states = emptyList(),
                cities = listOf("Zagreb", "Split", "Rijeka", "Osijek", "Zadar", "Slavonski Brod", "Pula", "Karlovac", "Sisak", "Varaždin")
            )
            "$region/Sarajevo" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Bosnia and Herzegovina", "BA", "\uD83C\uDDE7\uD83C\uDDE6"),
                states = emptyList(),
                cities = listOf("Sarajevo", "Banja Luka", "Tuzla", "Zenica", "Mostar", "Prijedor", "Brčko", "Bihać", "Bugojno", "Trebinje")
            )
            "$region/Podgorica" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Montenegro", "ME", "\uD83C\uDDF2\uD83C\uDDEA"),
                states = emptyList(),
                cities = listOf("Podgorica", "Nikšić", "Herceg Novi", "Bar", "Budva", "Kotor", "Cetinje", "Berane", "Pljevlja", "Ulcinj")
            )
            "$region/Tirane" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Albania", "AL", "\uD83C\uDDE6\uD83C\uDDF1"),
                states = emptyList(),
                cities = listOf("Tirana", "Durrës", "Vlorë", "Shkodër", "Fier", "Kamëz", "Korçë", "Fier-Çifçi", "Berat", "Lushnjë")
            )
            "$region/Belgrade" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Serbia", "Kosovo", "RS", "XK"),
                states = emptyList(),
                cities = listOf("Belgrade", "Novi Sad", "Niš", "Kragujevac", "Subotica", "Zrenjanin", "Pančevo", "Čačak", "Novi Pazar", "Kraljevo", "Pristina", "Prizren", "Peć", "Gjakova", "Mitrovica", "Ferizaj", "Gjilan", "Kosovska Mitrovica", "Podujevo", "Vučitrn")
            )
            "$region/Skopje" -> TimeRegion(
                zoneIdString, region,
                country = listOf("North Macedonia", "MK", "\uD83C\uDDF2\uD83C\uDDF0"),
                states = emptyList(),
                cities = listOf("Skopje", "Bitola", "Kumanovo", "Prilep", "Tetovo", "Veles", "Ohrid", "Gostivar", "Štip", "Strumica")
            )
            "$region/Athens" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Greece", "GR", "\uD83C\uDDEC\uD83C\uDDF7"),
                states = emptyList(),
                cities = listOf("Athens", "Thessaloniki", "Patras", "Heraklion", "Larissa", "Volos", "Ioannina", "Chania", "Chalcis", "Serres")
            )
            "$region/Sofia" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Bulgaria", "BG", "\uD83C\uDDE7\uD83C\uDDEC"),
                states = emptyList(),
                cities = listOf("Sofia", "Plovdiv", "Varna", "Burgas", "Ruse", "Stara Zagora", "Pleven", "Sliven", "Dobrich", "Shumen")
            )
            "$region/Bucharest" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Romania", "RO", "\uD83C\uDDF7\uD83C\uDDF4"),
                states = emptyList(),
                cities = listOf("Bucharest", "Cluj-Napoca", "Timișoara", "Iași", "Craiova", "Constanța", "Galați", "Brașov", "Ploiești", "Oradea")
            )
            "$region/Chisinau" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Moldova", "MD", "\uD83C\uDDF2\uD83C\uDDE9"),
                states = emptyList(),
                cities = listOf("Chișinău", "Tiraspol", "Bălți", "Bender", "Rîbnița", "Cahul", "Ungheni", "Soroca", "Orhei", "Dubăsari")
            )
            "$region/Kiev" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Ukraine", "UA", "\uD83C\uDDFA\uD83C\uDDE6"),
                states = emptyList(),
                cities = listOf("Kyiv", "Kharkiv", "Odesa", "Dnipro", "Donetsk", "Zaporizhzhia", "Lviv", "Kryvyi Rih", "Mykolaiv", "Mariupol")
            )
            "$region/Minsk" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Belarus", "BY", "\uD83C\uDDE7\uD83C\uDDFE"),
                states = emptyList(),
                cities = listOf("Minsk", "Gomel", "Mogilev", "Vitebsk", "Hrodna", "Brest", "Babruysk", "Baranovichi", "Borisov", "Pinsk")
            )
            "$region/Warsaw" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Poland", "PL", "\uD83C\uDDF5\uD83C\uDDF1"),
                states = emptyList(),
                cities = listOf("Warsaw", "Kraków", "Łódź", "Wrocław", "Poznań", "Gdańsk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice")
            )
            "$region/Vilnius" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Lithuania", "LT", "\uD83C\uDDF1\uD83C\uDDF9"),
                states = emptyList(),
                cities = listOf("Vilnius", "Kaunas", "Klaipėda", "Šiauliai", "Panevėžys", "Alytus", "Marijampolė", "Mažeikiai", "Jonava", "Utena")
            )
            "$region/Riga" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Latvia", "LV", "\uD83C\uDDF1\uD83C\uDDFB"),
                states = emptyList(),
                cities = listOf("Riga", "Daugavpils", "Liepāja", "Jelgava", "Jūrmala", "Ventspils", "Rēzekne", "Valmiera", "Jēkabpils", "Ogre")
            )
            "$region/Tallinn" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Estonia", "EE", "\uD83C\uDDEA\uD83C\uDDEA"),
                states = emptyList(),
                cities = listOf("Tallinn", "Tartu", "Narva", "Pärnu", "Kohtla-Järve", "Viljandi", "Rakvere", "Maardu", "Sillamäe", "Kuressaare")
            )
            "$region/Bratislava" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Slovakia", "SK", "\uD83C\uDDF8\uD83C\uDDF0"),
                states = emptyList(),
                cities = listOf("Bratislava", "Košice", "Prešov", "Žilina", "Nitra", "Banská Bystrica", "Trnava", "Martin", "Trenčín", "Poprad")
            )
            "$region/Ljubljana" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Slovenia", "SI", "\uD83C\uDDF8\uD83C\uDDEE"),
                states = emptyList(),
                cities = listOf("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Ptuj", "Trbovlje", "Kamnik")
            )
            "$region/Moscow" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Russia", "RU", "\uD83C\uDDF7\uD83C\uDDFA"),
                states = emptyList(),
                cities = listOf("Moscow", "Saint Petersburg", "Novosibirsk", "Yekaterinburg", "Nizhny Novgorod", "Kazan", "Chelyabinsk", "Omsk", "Samara", "Rostov-on-Don")
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}