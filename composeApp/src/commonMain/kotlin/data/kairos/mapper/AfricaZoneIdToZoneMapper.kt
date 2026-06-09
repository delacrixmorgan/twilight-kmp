package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AfricaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Africa
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Abidjan" -> Zone(
                zoneIdString, region,
                country = listOf("Côte d'Ivoire", "Ivory Coast", "CI", "🇨🇮"),
                keywords = listOf("Abidjan", "Yamoussoukro", "Bouaké", "Daloa", "San-Pédro", "Korhogo"),
            )
            "$region/Accra" -> Zone(
                zoneIdString, region,
                country = listOf("Ghana", "GH", "🇬🇭"),
                keywords = listOf("Accra", "Kumasi", "Tamale", "Takoradi", "Cape Coast", "Tema"),
            )
            "$region/Addis_Ababa" -> Zone(
                zoneIdString, region,
                country = listOf("Ethiopia", "ET", "🇪🇹"),
                keywords = listOf("Addis Ababa", "Dire Dawa", "Mekelle", "Gondar", "Adama", "Hawassa"),
            )
            "$region/Algiers" -> Zone(
                zoneIdString, region,
                country = listOf("Algeria", "DZ", "🇩🇿"),
                keywords = listOf("Algiers", "Oran", "Constantine", "Annaba", "Blida", "Batna"),
            )
            "$region/Asmara",
            "$region/Asmera" -> Zone(
                zoneIdString, region,
                country = listOf("Eritrea", "ER", "🇪🇷"),
                keywords = listOf("Asmara", "Keren", "Massawa", "Assab", "Mendefera", "Barentu"),
            )
            "$region/Bamako",
            "$region/Timbuktu" -> Zone(
                zoneIdString, region,
                country = listOf("Mali", "ML", "🇲🇱"),
                keywords = listOf("Bamako", "Sikasso", "Mopti", "Timbuktu", "Ségou", "Kayes"),
            )
            "$region/Bangui" -> Zone(
                zoneIdString, region,
                country = listOf("Central African Republic", "CF", "🇨🇫"),
                keywords = listOf("Bangui", "Bimbo", "Berbérati", "Carnot", "Bambari", "Bouar"),
            )
            "$region/Banjul" -> Zone(
                zoneIdString, region,
                country = listOf("Gambia", "GM", "🇬🇲"),
                keywords = listOf("Banjul", "Serekunda", "Brikama", "Bakau", "Farafenni"),
            )
            "$region/Bissau" -> Zone(
                zoneIdString, region,
                country = listOf("Guinea-Bissau", "GW", "🇬🇼"),
                keywords = listOf("Bissau", "Bafatá", "Gabú", "Bissorã", "Bolama", "Cacheu"),
            )
            "$region/Blantyre" -> Zone(
                zoneIdString, region,
                country = listOf("Malawi", "MW", "🇲🇼"),
                keywords = listOf("Lilongwe", "Blantyre", "Mzuzu", "Zomba", "Kasungu", "Mangochi"),
            )
            "$region/Brazzaville" -> Zone(
                zoneIdString, region,
                country = listOf("Republic of the Congo", "Congo", "CG", "🇨🇬"),
                keywords = listOf("Brazzaville", "Pointe-Noire", "Dolisie", "Nkayi", "Owando"),
            )
            "$region/Bujumbura" -> Zone(
                zoneIdString, region,
                country = listOf("Burundi", "BI", "🇧🇮"),
                keywords = listOf("Bujumbura", "Gitega", "Muyinga", "Ruyigi", "Ngozi", "Rumonge"),
            )
            "$region/Cairo" -> Zone(
                zoneIdString, region,
                country = listOf("Egypt", "EG", "🇪🇬"),
                keywords = listOf("Cairo", "Alexandria", "Giza", "Shubra El Kheima", "Port Said", "Suez", "Luxor"),
            )
            "$region/Casablanca" -> Zone(
                zoneIdString, region,
                country = listOf("Morocco", "MA", "🇲🇦"),
                keywords = listOf("Casablanca", "Rabat", "Fès", "Marrakesh", "Tangier", "Agadir", "Meknes"),
            )
            "$region/Ceuta" -> Zone(
                zoneIdString, region,
                country = listOf("Spain", "ES", "🇪🇸"),
                keywords = listOf("Ceuta", "Melilla"),
            )
            "$region/Conakry" -> Zone(
                zoneIdString, region,
                country = listOf("Guinea", "GN", "🇬🇳"),
                keywords = listOf("Conakry", "Nzérékoré", "Kankan", "Kindia", "Labé", "Kissidougou"),
            )
            "$region/Dakar" -> Zone(
                zoneIdString, region,
                country = listOf("Senegal", "SN", "🇸🇳"),
                keywords = listOf("Dakar", "Touba", "Thiès", "Rufisque", "Kaolack", "Saint-Louis"),
            )
            "$region/Dar_es_Salaam" -> Zone(
                zoneIdString, region,
                country = listOf("Tanzania", "TZ", "🇹🇿"),
                keywords = listOf("Dar es Salaam", "Dodoma", "Mwanza", "Arusha", "Zanzibar", "Mbeya"),
            )
            "$region/Djibouti" -> Zone(
                zoneIdString, region,
                country = listOf("Djibouti", "DJ", "🇩🇯"),
                keywords = listOf("Djibouti", "Ali Sabieh", "Tadjourah", "Obock", "Dikhil", "Arta"),
            )
            "$region/Douala" -> Zone(
                zoneIdString, region,
                country = listOf("Cameroon", "CM", "🇨🇲"),
                keywords = listOf("Douala", "Yaoundé", "Garoua", "Bamenda", "Maroua", "Bafoussam"),
            )
            "$region/El_Aaiun" -> Zone(
                zoneIdString, region,
                country = listOf("Western Sahara", "EH", "🇪🇭"),
                keywords = listOf("El Aaiún", "Dakhla", "Laâyoune", "Smara", "Boujdour"),
            )
            "$region/Freetown" -> Zone(
                zoneIdString, region,
                country = listOf("Sierra Leone", "SL", "🇸🇱"),
                keywords = listOf("Freetown", "Bo", "Kenema", "Makeni", "Koidu", "Waterloo"),
            )
            "$region/Gaborone" -> Zone(
                zoneIdString, region,
                country = listOf("Botswana", "BW", "🇧🇼"),
                keywords = listOf("Gaborone", "Francistown", "Molepolole", "Maun", "Serowe", "Selibe Phikwe"),
            )
            "$region/Harare" -> Zone(
                zoneIdString, region,
                country = listOf("Zimbabwe", "ZW", "🇿🇼"),
                keywords = listOf("Harare", "Bulawayo", "Chitungwiza", "Mutare", "Gweru", "Kwekwe"),
            )
            "$region/Johannesburg" -> Zone(
                zoneIdString, region,
                country = listOf("South Africa", "ZA", "🇿🇦"),
                keywords = listOf("Johannesburg", "Cape Town", "Durban", "Pretoria", "Port Elizabeth", "Soweto", "Bloemfontein"),
            )
            "$region/Juba" -> Zone(
                zoneIdString, region,
                country = listOf("South Sudan", "SS", "🇸🇸"),
                keywords = listOf("Juba", "Malakal", "Wau", "Yei", "Aweil", "Bor"),
            )
            "$region/Kampala" -> Zone(
                zoneIdString, region,
                country = listOf("Uganda", "UG", "🇺🇬"),
                keywords = listOf("Kampala", "Nansana", "Kira", "Mbarara", "Mukono", "Gulu"),
            )
            "$region/Khartoum" -> Zone(
                zoneIdString, region,
                country = listOf("Sudan", "SD", "🇸🇩"),
                keywords = listOf("Khartoum", "Omdurman", "Nyala", "Port Sudan", "Kassala", "El Obeid"),
            )
            "$region/Kigali" -> Zone(
                zoneIdString, region,
                country = listOf("Rwanda", "RW", "🇷🇼"),
                keywords = listOf("Kigali", "Butare", "Gitarama", "Ruhengeri", "Gisenyi", "Byumba"),
            )
            "$region/Kinshasa",
            "$region/Lubumbashi" -> Zone(
                zoneIdString, region,
                country = listOf("DR Congo", "Democratic Republic of the Congo", "CD", "🇨🇩"),
                keywords = listOf("Kinshasa", "Lubumbashi", "Mbuji-Mayi", "Kananga", "Kisangani", "Bukavu", "Goma"),
            )
            "$region/Lagos" -> Zone(
                zoneIdString, region,
                country = listOf("Nigeria", "NG", "🇳🇬"),
                keywords = listOf("Lagos", "Abuja", "Kano", "Ibadan", "Port Harcourt", "Benin City", "Kaduna"),
            )
            "$region/Libreville" -> Zone(
                zoneIdString, region,
                country = listOf("Gabon", "GA", "🇬🇦"),
                keywords = listOf("Libreville", "Port-Gentil", "Franceville", "Oyem", "Moanda", "Lambaréné"),
            )
            "$region/Lome" -> Zone(
                zoneIdString, region,
                country = listOf("Togo", "TG", "🇹🇬"),
                keywords = listOf("Lomé", "Sokodé", "Kara", "Kpalimé", "Atakpamé", "Dapaong"),
            )
            "$region/Luanda" -> Zone(
                zoneIdString, region,
                country = listOf("Angola", "AO", "🇦🇴"),
                keywords = listOf("Luanda", "Huambo", "Lobito", "Benguela", "Lubango", "Cabinda"),
            )
            "$region/Lusaka" -> Zone(
                zoneIdString, region,
                country = listOf("Zambia", "ZM", "🇿🇲"),
                keywords = listOf("Lusaka", "Kitwe", "Ndola", "Kabwe", "Chingola", "Livingstone"),
            )
            "$region/Malabo" -> Zone(
                zoneIdString, region,
                country = listOf("Equatorial Guinea", "GQ", "🇬🇶"),
                keywords = listOf("Malabo", "Bata", "Ebebiyín", "Aconibe", "Luba", "Mongomo"),
            )
            "$region/Maputo" -> Zone(
                zoneIdString, region,
                country = listOf("Mozambique", "MZ", "🇲🇿"),
                keywords = listOf("Maputo", "Matola", "Nampula", "Beira", "Chimoio", "Nacala"),
            )
            "$region/Maseru" -> Zone(
                zoneIdString, region,
                country = listOf("Lesotho", "LS", "🇱🇸"),
                keywords = listOf("Maseru", "Teyateyaneng", "Mafeteng", "Hlotse", "Mohale's Hoek", "Maputsoe"),
            )
            "$region/Mbabane" -> Zone(
                zoneIdString, region,
                country = listOf("Eswatini", "Swaziland", "SZ", "🇸🇿"),
                keywords = listOf("Mbabane", "Manzini", "Lobamba", "Siteki", "Nhlangano", "Hluti"),
            )
            "$region/Mogadishu" -> Zone(
                zoneIdString, region,
                country = listOf("Somalia", "SO", "🇸🇴"),
                keywords = listOf("Mogadishu", "Hargeisa", "Bosaso", "Kismayo", "Marka", "Baidoa"),
            )
            "$region/Monrovia" -> Zone(
                zoneIdString, region,
                country = listOf("Liberia", "LR", "🇱🇷"),
                keywords = listOf("Monrovia", "Gbarnga", "Kakata", "Buchanan", "Ganta", "Zwedru"),
            )
            "$region/Nairobi" -> Zone(
                zoneIdString, region,
                country = listOf("Kenya", "KE", "🇰🇪"),
                keywords = listOf("Nairobi", "Mombasa", "Nakuru", "Eldoret", "Kisumu", "Thika"),
            )
            "$region/Ndjamena" -> Zone(
                zoneIdString, region,
                country = listOf("Chad", "TD", "🇹🇩"),
                keywords = listOf("N'Djamena", "Moundou", "Sarh", "Abéché", "Kelo", "Koumra"),
            )
            "$region/Niamey" -> Zone(
                zoneIdString, region,
                country = listOf("Niger", "NE", "🇳🇪"),
                keywords = listOf("Niamey", "Zinder", "Maradi", "Agadez", "Tahoua", "Dosso"),
            )
            "$region/Nouakchott" -> Zone(
                zoneIdString, region,
                country = listOf("Mauritania", "MR", "🇲🇷"),
                keywords = listOf("Nouakchott", "Nouadhibou", "Néma", "Kaédi", "Rosso", "Zouérat"),
            )
            "$region/Ouagadougou" -> Zone(
                zoneIdString, region,
                country = listOf("Burkina Faso", "BF", "🇧🇫"),
                keywords = listOf("Ouagadougou", "Bobo-Dioulasso", "Koudougou", "Banfora", "Ouahigouya", "Pouytenga"),
            )
            "$region/Porto-Novo" -> Zone(
                zoneIdString, region,
                country = listOf("Benin", "BJ", "🇧🇯"),
                keywords = listOf("Porto-Novo", "Cotonou", "Parakou", "Djougou", "Bohicon", "Abomey"),
            )
            "$region/Sao_Tome" -> Zone(
                zoneIdString, region,
                country = listOf("São Tomé and Príncipe", "ST", "🇸🇹"),
                keywords = listOf("São Tomé", "Santo António", "Neves", "Santana", "Trindade"),
            )
            "$region/Tripoli" -> Zone(
                zoneIdString, region,
                country = listOf("Libya", "LY", "🇱🇾"),
                keywords = listOf("Tripoli", "Benghazi", "Misrata", "Tarhuna", "Al Khums", "Zawiya"),
            )
            "$region/Tunis" -> Zone(
                zoneIdString, region,
                country = listOf("Tunisia", "TN", "🇹🇳"),
                keywords = listOf("Tunis", "Sfax", "Sousse", "Kairouan", "Bizerte", "Gabès"),
            )
            "$region/Windhoek" -> Zone(
                zoneIdString, region,
                country = listOf("Namibia", "NA", "🇳🇦"),
                keywords = listOf("Windhoek", "Rundu", "Walvis Bay", "Oshakati", "Swakopmund", "Katima Mulilo"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}
