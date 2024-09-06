package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class EuropeZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Europe
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Lisbon" -> Zone(
                zoneIdString, region,
                country = listOf("Portugal", "PT", "\uD83C\uDDF5\uD83C\uDDF9"),
                keywords = listOf("Lisbon", "Porto", "Vila Nova de Gaia", "Amadora", "Braga", "Setúbal", "Coimbra", "Funchal", "Almada", "Aveiro"),
            )
            "$region/Madrid" -> Zone(
                zoneIdString, region,
                country = listOf("Spain", "ES", "\uD83C\uDDEA\uD83C\uDDF8"),
                keywords = listOf("Madrid", "Barcelona", "Valencia", "Seville", "Zaragoza", "Málaga", "Murcia", "Palma", "Las Palmas", "Bilbao"),
            )
            "$region/Paris" -> Zone(
                zoneIdString, region,
                country = listOf("France", "FR", "\uD83C\uDDEB\uD83C\uDDF7"),
                keywords = listOf("Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg", "Montpellier", "Bordeaux", "Lille"),
            )
            "$region/Andorra" -> Zone(
                zoneIdString, region,
                country = listOf("Andorra", "AD", "\uD83C\uDDE6\uD83C\uDDE9"),
                keywords = listOf("Andorra la Vella", "Escaldes-Engordany", "Encamp", "Sant Julià de Lòria", "La Massana", "Santa Coloma", "Ordino", "Canillo")
            )
            "$region/Monaco" -> Zone(
                zoneIdString, region,
                country = listOf("Monaco", "MC", "\uD83C\uDDF2\uD83C\uDDE8"),
                keywords = listOf("Monaco", "Monte Carlo", "La Condamine", "Fontvieille")
            )
            "$region/Luxembourg" -> Zone(
                zoneIdString, region,
                country = listOf("Luxembourg", "LU", "\uD83C\uDDF1\uD83C\uDDFA"),
                keywords = listOf("Luxembourg City", "Esch-sur-Alzette", "Differdange", "Dudelange", "Ettelbruck")
            )
            "$region/Brussels" -> Zone(
                zoneIdString, region,
                country = listOf("Belgium", "BE", "\uD83C\uDDE7\uD83C\uDDEA"),
                keywords = listOf("Brussels", "Antwerp", "Ghent", "Charleroi", "Liège", "Bruges", "Namur", "Leuven", "Mechelen", "Aalst")
            )
            "$region/Amsterdam" -> Zone(
                zoneIdString, region,
                country = listOf("Netherlands", "NL", "\uD83C\uDDF3\uD83C\uDDF1"),
                keywords = listOf("Drenthe", "Flevoland", "Friesland", "Gelderland", "Groningen", "Limburg", "North Brabant", "North Holland", "Overijssel", "Utrecht", "Zeeland", "South Holland", "Amsterdam", "Rotterdam", "The Hague", "Utrecht", "Eindhoven", "Tilburg", "Groningen", "Almere", "Breda", "Nijmegen", "Enschede", "Haarlem", "Arnhem", "Zaanstad", "Amersfoort", "Apeldoorn", "Zwolle", "Maastricht", "Dordrecht", "Leiden"),
            )
            "$region/Berlin" -> Zone(
                zoneIdString, region,
                country = listOf("Germany", "DE", "\uD83C\uDDE9\uD83C\uDDEA"),
                keywords = listOf("Brandenburg", "Berlin", "Bavaria", "Saxony Anhalt", "Mecklenburg Vorpommern", "Hamburg", "Saxony", "North Rhine Westphalia", "Schleswig Holstein", "Bremen", "Baden Württemberg", "Hesse", "Lower Saxony", "Rhineland Palatinate", "Saarland", "Thuringia", "Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt", "Stuttgart", "Düsseldorf", "Dortmund", "Essen", "Leipzig"),
            )
            "$region/Prague" -> Zone(
                zoneIdString, region,
                country = listOf("Czech Republic", "Czechia", "CZ", "\uD83C\uDDE8\uD83C\uDDFF"),
                keywords = listOf("Prague", "Brno", "Ostrava", "Plzeň", "Liberec", "Olomouc", "České Budějovice", "Ústí nad Labem", "Hradec Králové", "Pardubice")
            )
            "$region/Zurich" -> Zone(
                zoneIdString, region,
                country = listOf("Switzerland", "CH", "\uD83C\uDDE8\uD83C\uDDED"),
                keywords = listOf("Zurich", "Geneva", "Basel", "Lausanne", "Bern", "Winterthur", "Lucerne", "St. Gallen", "Lugano", "Biel/Bienne")
            )
            "$region/Vaduz" -> Zone(
                zoneIdString, region,
                country = listOf("Liechtenstein", "LI", "\uD83C\uDDF1\uD83C\uDDEE"),
                keywords = listOf("Vaduz", "Schaan", "Triesen", "Balzers", "Eschen", "Mauren", "Ruggell", "Gamprin", "Schellenberg", "Planken")
            )
            "$region/Vienna" -> Zone(
                zoneIdString, region,
                country = listOf("Austria", "AT", "\uD83C\uDDE6\uD83C\uDDF9"),
                keywords = listOf("Vienna", "Graz", "Linz", "Salzburg", "Innsbruck", "Klagenfurt", "Villach", "Wels", "Sankt Pölten", "Dornbirn")
            )
            "$region/London",
            "$region/Isle_of_Man",
            "$region/Jersey" -> Zone(
                zoneIdString, region,
                country = listOf("United Kingdom", "UK", "\uD83C\uDDEC\uD83C\uDDE7"),
                keywords = listOf("London", "Bristol", "Liverpool")
            )
            "$region/Dublin" -> Zone(
                zoneIdString, region,
                country = listOf("Ireland", "IE", "\uD83C\uDDEE\uD83C\uDDEA"),
                keywords = listOf("Dublin", "Cork", "Galway", "Limerick", "Waterford", "Drogheda", "Dundalk", "Sligo", "Bray", "Navan")
            )
            "$region/Copenhagen" -> Zone(
                zoneIdString, region,
                country = listOf("Denmark", "DK", "\uD83C\uDDE9\uD83C\uDDF0"),
                keywords = listOf("Copenhagen"),
            )
            "$region/Oslo" -> Zone(
                zoneIdString, region,
                country = listOf("Norway", "NO", "\uD83C\uDDF3\uD83C\uDDF4"),
                keywords = listOf("Oslo", "Bergen", "Trondheim", "Stavanger", "Drammen", "Fredrikstad", "Kristiansand", "Sandnes", "Tromsø", "Sarpsborg")
            )
            "$region/Stockholm" -> Zone(
                zoneIdString, region,
                country = listOf("Sweden", "SE", "\uD83C\uDDF8\uD83C\uDDEA"),
                keywords = listOf("Stockholm", "Gothenburg", "Malmö", "Uppsala", "Sollentuna", "Västerås", "Örebro", "Linköping", "Helsingborg", "Jönköping")
            )
            "$region/Helsinki" -> Zone(
                zoneIdString, region,
                country = listOf("Finland", "FI", "\uD83C\uDDEB\uD83C\uDDEE"),
                keywords = listOf("Helsinki", "Espoo", "Tampere", "Vantaa", "Oulu", "Turku", "Jyväskylä", "Kuopio", "Lahti", "Pori")
            )
            "$region/Malta" -> Zone(
                zoneIdString, region,
                country = listOf("Malta", "MT", "\uD83C\uDDF2\uD83C\uDDF9"),
                keywords = listOf("Valletta", "Birkirkara", "Mosta", "Qormi", "Żabbar", "San Ġwann", "Żejtun", "Sliema", "Luqa", "Gżira")
            )
            "$region/Rome" -> Zone(
                zoneIdString, region,
                country = listOf("Italy", "IT", "\uD83C\uDDEE\uD83C\uDDF9"),
                keywords = listOf("Rome", "Milan", "Naples", "Turin", "Palermo", "Genoa", "Bologna", "Florence", "Bari", "Catania")
            )
            "$region/San_Marino" -> Zone(
                zoneIdString, region,
                country = listOf("San Marino", "SM", "\uD83C\uDDF8\uD83C\uDDF2"),
                keywords = listOf("San Marino", "Serravalle", "Borgo Maggiore", "Domagnano", "Fiorentino", "Acquaviva", "Chiesanuova", "Montegiardino", "San Leo")
            )
            "$region/Vatican" -> Zone(
                zoneIdString, region,
                country = listOf("Vatican City", "VA", "\uD83C\uDDFB\uD83C\uDDE6"),
                keywords = listOf("Vatican City")
            )
            "$region/Ljubljana" -> Zone(
                zoneIdString, region,
                country = listOf("Slovenia", "SI", "\uD83C\uDDF8\uD83C\uDDEE"),
                keywords = listOf("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Ptuj", "Trbovlje", "Kamnik")
            )
            "$region/Zagreb" -> Zone(
                zoneIdString, region,
                country = listOf("Croatia", "HR", "\uD83C\uDDED\uD83C\uDDF7"),
                keywords = listOf("Zagreb", "Split", "Rijeka", "Osijek", "Zadar", "Slavonski Brod", "Pula", "Karlovac", "Sisak", "Varaždin")
            )
            "$region/Sarajevo" -> Zone(
                zoneIdString, region,
                country = listOf("Bosnia and Herzegovina", "BA", "\uD83C\uDDE7\uD83C\uDDE6"),
                keywords = listOf("Sarajevo", "Banja Luka", "Tuzla", "Zenica", "Mostar", "Prijedor", "Brčko", "Bihać", "Bugojno", "Trebinje")
            )
            "$region/Podgorica" -> Zone(
                zoneIdString, region,
                country = listOf("Montenegro", "ME", "\uD83C\uDDF2\uD83C\uDDEA"),
                keywords = listOf("Podgorica", "Nikšić", "Herceg Novi", "Bar", "Budva", "Kotor", "Cetinje", "Berane", "Pljevlja", "Ulcinj")
            )
            "$region/Tirane" -> Zone(
                zoneIdString, region,
                country = listOf("Albania", "AL", "\uD83C\uDDE6\uD83C\uDDF1"),
                keywords = listOf("Tirana", "Durrës", "Vlorë", "Shkodër", "Fier", "Kamëz", "Korçë", "Fier-Çifçi", "Berat", "Lushnjë")
            )
            "$region/Belgrade" -> Zone(
                zoneIdString, region,
                country = listOf("Serbia", "Kosovo", "RS", "XK"),
                keywords = listOf("Belgrade", "Novi Sad", "Niš", "Kragujevac", "Subotica", "Zrenjanin", "Pančevo", "Čačak", "Novi Pazar", "Kraljevo", "Pristina", "Prizren", "Peć", "Gjakova", "Mitrovica", "Ferizaj", "Gjilan", "Kosovska Mitrovica", "Podujevo", "Vučitrn")
            )
            "$region/Skopje" -> Zone(
                zoneIdString, region,
                country = listOf("North Macedonia", "MK", "\uD83C\uDDF2\uD83C\uDDF0"),
                keywords = listOf("Skopje", "Bitola", "Kumanovo", "Prilep", "Tetovo", "Veles", "Ohrid", "Gostivar", "Štip", "Strumica")
            )
            "$region/Athens" -> Zone(
                zoneIdString, region,
                country = listOf("Greece", "GR", "\uD83C\uDDEC\uD83C\uDDF7"),
                keywords = listOf("Athens", "Thessaloniki", "Patras", "Heraklion", "Larissa", "Volos", "Ioannina", "Chania", "Chalcis", "Serres")
            )
            "$region/Sofia" -> Zone(
                zoneIdString, region,
                country = listOf("Bulgaria", "BG", "\uD83C\uDDE7\uD83C\uDDEC"),
                keywords = listOf("Sofia", "Plovdiv", "Varna", "Burgas", "Ruse", "Stara Zagora", "Pleven", "Sliven", "Dobrich", "Shumen")
            )
            "$region/Bucharest" -> Zone(
                zoneIdString, region,
                country = listOf("Romania", "RO", "\uD83C\uDDF7\uD83C\uDDF4"),
                keywords = listOf("Bucharest", "Cluj-Napoca", "Timișoara", "Iași", "Craiova", "Constanța", "Galați", "Brașov", "Ploiești", "Oradea")
            )
            "$region/Chisinau" -> Zone(
                zoneIdString, region,
                country = listOf("Moldova", "MD", "\uD83C\uDDF2\uD83C\uDDE9"),
                keywords = listOf("Chișinău", "Tiraspol", "Bălți", "Bender", "Rîbnița", "Cahul", "Ungheni", "Soroca", "Orhei", "Dubăsari")
            )
            "$region/Kiev" -> Zone(
                zoneIdString, region,
                country = listOf("Ukraine", "UA", "\uD83C\uDDFA\uD83C\uDDE6"),
                keywords = listOf("Kyiv", "Kharkiv", "Odesa", "Dnipro", "Donetsk", "Zaporizhzhia", "Lviv", "Kryvyi Rih", "Mykolaiv", "Mariupol")
            )
            "$region/Minsk" -> Zone(
                zoneIdString, region,
                country = listOf("Belarus", "BY", "\uD83C\uDDE7\uD83C\uDDFE"),
                keywords = listOf("Minsk", "Gomel", "Mogilev", "Vitebsk", "Hrodna", "Brest", "Babruysk", "Baranovichi", "Borisov", "Pinsk")
            )
            "$region/Warsaw" -> Zone(
                zoneIdString, region,
                country = listOf("Poland", "PL", "\uD83C\uDDF5\uD83C\uDDF1"),
                keywords = listOf("Warsaw", "Kraków", "Łódź", "Wrocław", "Poznań", "Gdańsk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice")
            )
            "$region/Vilnius" -> Zone(
                zoneIdString, region,
                country = listOf("Lithuania", "LT", "\uD83C\uDDF1\uD83C\uDDF9"),
                keywords = listOf("Vilnius", "Kaunas", "Klaipėda", "Šiauliai", "Panevėžys", "Alytus", "Marijampolė", "Mažeikiai", "Jonava", "Utena")
            )
            "$region/Riga" -> Zone(
                zoneIdString, region,
                country = listOf("Latvia", "LV", "\uD83C\uDDF1\uD83C\uDDFB"),
                keywords = listOf("Riga", "Daugavpils", "Liepāja", "Jelgava", "Jūrmala", "Ventspils", "Rēzekne", "Valmiera", "Jēkabpils", "Ogre")
            )
            "$region/Tallinn" -> Zone(
                zoneIdString, region,
                country = listOf("Estonia", "EE", "\uD83C\uDDEA\uD83C\uDDEA"),
                keywords = listOf("Tallinn", "Tartu", "Narva", "Pärnu", "Kohtla-Järve", "Viljandi", "Rakvere", "Maardu", "Sillamäe", "Kuressaare")
            )
            "$region/Bratislava" -> Zone(
                zoneIdString, region,
                country = listOf("Slovakia", "SK", "\uD83C\uDDF8\uD83C\uDDF0"),
                keywords = listOf("Bratislava", "Košice", "Prešov", "Žilina", "Nitra", "Banská Bystrica", "Trnava", "Martin", "Trenčín", "Poprad")
            )
            "$region/Ljubljana" -> Zone(
                zoneIdString, region,
                country = listOf("Slovenia", "SI", "\uD83C\uDDF8\uD83C\uDDEE"),
                keywords = listOf("Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Ptuj", "Trbovlje", "Kamnik")
            )
            "$region/Moscow" -> Zone(
                zoneIdString, region,
                country = listOf("Russia", "RU", "\uD83C\uDDF7\uD83C\uDDFA"),
                keywords = listOf("Moscow", "Saint Petersburg", "Novosibirsk", "Yekaterinburg", "Nizhny Novgorod", "Kazan", "Chelyabinsk", "Omsk", "Samara", "Rostov-on-Don")
            )
            else -> Zone(zoneIdString, region)
        }
    }
}