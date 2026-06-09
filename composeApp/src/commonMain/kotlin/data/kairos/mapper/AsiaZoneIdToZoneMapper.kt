package data.kairos.mapper

import data.kairos.model.Region
import data.kairos.model.Zone
import data.utils.Mapper

class AsiaZoneIdToZoneMapper : Mapper<List<String>, List<Zone>> {
    companion object {
        private val region = Region.Asia
    }

    override fun invoke(input: List<String>): List<Zone> = input.map { zoneIdString ->
        when (zoneIdString) {
            // South East Asia
            "$region/Brunei" -> Zone(
                zoneIdString, region,
                country = listOf("Brunei", "BN", "\uD83C\uDDE7\uD83C\uDDF3"),
                keywords = listOf("Brunei-Muara", "Belait", "Tutong", "Temburong", "Bandar Seri Begawan", "Kuala Belait", "Seria", "Tutong", "Bangar"),
            )
            "$region/Phnom_Penh" -> Zone(
                zoneIdString, region,
                country = listOf("Cambodia", "KH", "\uD83C\uDDF0\uD83C\uDDED"),
                keywords = listOf("Phnom Penh", "Battambang", "Siem Reap", "Sihanoukville", "Kampong Cham", "Krong Preah Sihanouk", "Krong Ta Khmau", "Kampong Speu"),
            )
            "$region/Jakarta",
            "$region/Makassar",
            "$region/Jayapura" -> Zone(
                zoneIdString, region,
                country = listOf("Indonesia", "ID", "\uD83C\uDDEE\uD83C\uDDE9"),
                keywords = listOf("Jakarta", "East Java", "West Java", "Central Java", "Banten", "West Kalimantan", "Central Kalimantan", "East Kalimantan", "South Kalimantan", "North Sumatra", "South Sumatra"),
            )
            "$region/Vientiane" -> Zone(
                zoneIdString, region,
                country = listOf("Laos", "LA", "\uD83C\uDDF1\uD83C\uDDE6"),
                keywords = listOf("Vientiane", "Savannakhet", "Luang Prabang", "Champasak", "Bokeo", "Houaphanh", "Khammouane", "Attapeu", "Xiangkhouang"),
            )
            "$region/Kuala_Lumpur",
            "$region/Kuching" -> Zone(
                zoneIdString, region,
                country = listOf("Malaysia", "MY", "\uD83C\uDDF2\uD83C\uDDFE"),
                keywords = listOf("Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Perak", "Perlis", "Penang", "Sabah", "Sarawak", "Selangor", "Terengganu", "Labuan", "Kuala Lumpur", "George Town", "Ipoh", "Johor Bahru", "Kuching", "Kota Kinabalu", "Shah Alam", "Malacca", "Alor Setar", "Seremban", "Klang", "Rawang"),
            )
            "$region/Yangon" -> Zone(
                zoneIdString, region,
                country = listOf("Myanmar", "MM", "\uD83C\uDDF2\uD83C\uDDF2"),
                keywords = listOf("Yangon", "Mandalay", "Ayeyarwady", "Sagaing", "Bago", "Magway", "Tanintharyi", "Kachin", "Kayah", "Kayin", "Chin", "Mon", "Rakhine", "Shan", "Pathein", "Monywa", "Meiktila", "Sittwe", "Myeik", "Pakokku"),
            )
            "$region/Manila" -> Zone(
                zoneIdString, region,
                country = listOf("Philippines", "PH", "\uD83C\uDDF5\uD83C\uDDED"),
                keywords = listOf("Metro Manila", "Calabarzon", "Central Luzon", "Central Visayas", "Western Visayas", "Davao Region", "Northern Mindanao", "Soccsksargen", "Cagayan Valley", "Caraga", "Cordillera Administrative Region", "Ilocos Region", "Mimaropa", "Eastern Visayas", "Zamboanga Peninsula", "Autonomous Region in Muslim Mindanao", "Quezon City", "Davao City", "Cebu City", "Zamboanga City", "Taguig", "Antipolo", "Cagayan de Oro", "Cainta", "Pasig"),
            )
            "$region/Singapore" -> Zone(
                zoneIdString, region,
                country = listOf("Singapore", "SG", "\uD83C\uDDF8\uD83C\uDDEC"),
                keywords = listOf("Singapore"),
            )
            "$region/Bangkok" -> Zone(
                zoneIdString, region,
                country = listOf("Thailand", "TH", "\uD83C\uDDF9\uD83C\uDDED"),
                keywords = listOf("Bangkok", "Chiang Mai", "Nakhon Ratchasima", "Khon Kaen", "Udon Thani", "Nakhon Sawan", "Chonburi", "Surat Thani", "Phuket", "Ayutthaya"),
            )
            "$region/Dili" -> Zone(
                zoneIdString, region,
                country = listOf("Timor-Leste", "TL", "\uD83C\uDDF9\uD83C\uDDF1"),
                keywords = listOf("Dili", "Baucau", "Lautém", "Viqueque", "Manufahi", "Manatuto", "Aileu", "Bobonaro", "Ermera", "Suai"),
            )
            "$region/Ho_Chi_Minh",
            "$region/Saigon" -> Zone(
                zoneIdString, region,
                country = listOf("Vietnam", "VN", "\uD83C\uDDFB\uD83C\uDDF3"),
                keywords = listOf("Ho Chi Minh City", "Hanoi", "Hai Phong", "Can Tho", "Da Nang", "Bien Hoa", "Vung Tau", "Nha Trang", "Hue", "Buon Ma Thuot", "Pleiku", "My Tho"),
            )
            // East Asia
            "$region/Shanghai",
            "$region/Urumqi" -> Zone(
                zoneIdString, region,
                country = listOf("China", "CN", "\uD83C\uDDE8\uD83C\uDDF3"),
                keywords = listOf("Shanghai", "Beijing", "Guangdong", "Zhejiang", "Shenzhen", "Xinjiang", "Wuhan"),
            )
            "$region/Tokyo" -> Zone(
                zoneIdString, region,
                country = listOf("Japan", "JP", "\uD83C\uDDEF\uD83C\uDDF5"),
                keywords = listOf("Tokyo", "Osaka", "Kyoto", "Kanagawa", "Yokohama"),
            )
            "$region/Seoul" -> Zone(
                zoneIdString, region,
                country = listOf("South Korea", "KR", "\uD83C\uDDF0\uD83C\uDDF7"),
                keywords = listOf("Seoul", "Busan", "Gyeonggi", "Incheon", "Incheon", "Daegu"),
            )
            "$region/Pyongyang" -> Zone(
                zoneIdString, region,
                country = listOf("North Korea", "KP", "\uD83C\uDDF0\uD83C\uDDF5"),
                keywords = listOf("Pyongyang", "South Pyongan", "North Hwanghae", "South Hamgyong", "Hamhung", "Nampo", "Wonsan"),
            )
            "$region/Ulaanbaatar" -> Zone(
                zoneIdString, region,
                country = listOf("Mongolia", "MN", "\uD83C\uDDF2\uD83C\uDDF3"),
                keywords = listOf("Ulaanbaatar", "Darkhan-Uul", "Orkhon", "Selenge"),
            )
            "$region/Taipei" -> Zone(
                zoneIdString, region,
                country = listOf("Taiwan", "TW", "\uD83C\uDDF9\uD83C\uDDFC"),
                keywords = listOf("Taipei", "New Taipei", "Kaohsiung", "Taichung"),
            )
            "$region/Hong_Kong" -> Zone(
                zoneIdString, region,
                country = listOf("Hong Kong", "HK", "\uD83C\uDDED\uD83C\uDDF0"),
                keywords = listOf("Hong Kong", "Kowloon", "Victoria", "Tsuen Wan", "Sha Tin", "Tuen Mun", "Yuen Long"),
            )
            "$region/Macau" -> Zone(
                zoneIdString, region,
                country = listOf("Macau", "Macao", "MO", "\uD83C\uDDF2\uD83C\uDDF4"),
                keywords = listOf("Macau", "Taipa", "Cotai", "Coloane", "S\u00E9"),
            )
            // South Asia
            "$region/Kabul" -> Zone(
                zoneIdString, region,
                country = listOf("Afghanistan", "AF", "\uD83C\uDDE6\uD83C\uDDEB"),
                keywords = listOf("Kabul", "Kandahar", "Herat", "Mazar-i-Sharif", "Jalalabad", "Kunduz", "Lashkar Gah", "Taloqan", "Puli Khumri", "Ghazni"),
            )
            "$region/Dhaka" -> Zone(
                zoneIdString, region,
                country = listOf("Bangladesh", "BD", "\uD83C\uDDE7\uD83C\uDDE9"),
                keywords = listOf("Dhaka", "Chittagong", "Khulna", "Rajshahi", "Sylhet", "Barisal", "Rangpur", "Comilla", "Narayanganj", "Gazipur"),
            )
            "$region/Thimphu" -> Zone(
                zoneIdString, region,
                country = listOf("Bhutan", "BT", "\uD83C\uDDE7\uD83C\uDDF9"),
                keywords = listOf("Thimphu", "Phuntsholing", "Paro", "Punakha", "Wangdue Phodrang", "Trashigang", "Mongar", "Trongsa", "Gelephu", "Samdrup Jongkhar"),
            )
            "$region/Kolkata" -> Zone(
                zoneIdString, region,
                country = listOf("India", "IN", "\uD83C\uDDEE\uD83C\uDDF3"),
                keywords = listOf("Mumbai", "New Delhi", "Bengaluru", "Kolkata", "Chennai", "Hyderabad", "Pune", "Ahmedabad", "Surat", "Jaipur"),
            )
            "$region/Kathmandu" -> Zone(
                zoneIdString, region,
                country = listOf("Nepal", "NP", "\uD83C\uDDF3\uD83C\uDDF5"),
                keywords = listOf("Kathmandu", "Pokhara", "Lalitpur", "Biratnagar", "Birgunj", "Dharan", "Bharatpur", "Bhaktapur", "Butwal", "Dhangadhi"),
            )
            "$region/Karachi" -> Zone(
                zoneIdString, region,
                country = listOf("Pakistan", "PK", "\uD83C\uDDF5\uD83C\uDDF0"),
                keywords = listOf("Karachi", "Lahore", "Faisalabad", "Rawalpindi", "Gujranwala", "Peshawar", "Multan", "Hyderabad", "Islamabad", "Quetta"),
            )
            "$region/Colombo" -> Zone(
                zoneIdString, region,
                country = listOf("Sri Lanka", "LK", "\uD83C\uDDF1\uD83C\uDDF0"),
                keywords = listOf("Colombo", "Dehiwala-Mount Lavinia", "Moratuwa", "Jaffna", "Negombo", "Pita Kotte", "Kotte", "Kandy", "Trincomalee", "Kalmunai"),
            )
            // Central Asia
            "$region/Almaty" -> Zone(
                zoneIdString, region,
                country = listOf("Kazakhstan", "KZ", "\uD83C\uDDF0\uD83C\uDDFF"),
                keywords = listOf("Almaty", "Astana", "Shymkent", "Karaganda", "Aktobe", "Taraz", "Pavlodar", "Oskemen"),
            )
            "$region/Tashkent",
            "$region/Samarkand" -> Zone(
                zoneIdString, region,
                country = listOf("Uzbekistan", "UZ", "\uD83C\uDDFA\uD83C\uDDFF"),
                keywords = listOf("Tashkent", "Samarkand", "Namangan", "Andijan", "Bukhara", "Nukus", "Fergana", "Qarshi"),
            )
            "$region/Bishkek" -> Zone(
                zoneIdString, region,
                country = listOf("Kyrgyzstan", "KG", "\uD83C\uDDF0\uD83C\uDDEC"),
                keywords = listOf("Bishkek", "Osh", "Jalal-Abad", "Karakol", "Tokmok", "Naryn"),
            )
            "$region/Dushanbe" -> Zone(
                zoneIdString, region,
                country = listOf("Tajikistan", "TJ", "\uD83C\uDDF9\uD83C\uDDEF"),
                keywords = listOf("Dushanbe", "Khujand", "Bokhtar", "Kulob", "Istaravshan", "Tursunzoda"),
            )
            "$region/Ashgabat" -> Zone(
                zoneIdString, region,
                country = listOf("Turkmenistan", "TM", "\uD83C\uDDF9\uD83C\uDDF2"),
                keywords = listOf("Ashgabat", "T\u00FCrkmenabat", "Da\u015Foguz", "Mary", "Balkanabat", "T\u00FCrkmenba\u015Fy"),
            )
            // Caucasus
            "$region/Baku" -> Zone(
                zoneIdString, region,
                country = listOf("Azerbaijan", "AZ", "\uD83C\uDDE6\uD83C\uDDFF"),
                keywords = listOf("Baku", "Ganja", "Sumqayit", "Mingachevir", "Lankaran", "Shaki", "Nakhchivan"),
            )
            "$region/Yerevan" -> Zone(
                zoneIdString, region,
                country = listOf("Armenia", "AM", "\uD83C\uDDE6\uD83C\uDDF2"),
                keywords = listOf("Yerevan", "Gyumri", "Vanadzor", "Vagharshapat", "Hrazdan", "Abovyan"),
            )
            "$region/Tbilisi" -> Zone(
                zoneIdString, region,
                country = listOf("Georgia", "GE", "\uD83C\uDDEC\uD83C\uDDEA"),
                keywords = listOf("Tbilisi", "Batumi", "Kutaisi", "Rustavi", "Zugdidi", "Gori", "Poti"),
            )
            // West Asia / Middle East
            "$region/Dubai" -> Zone(
                zoneIdString, region,
                country = listOf("United Arab Emirates", "UAE", "AE", "\uD83C\uDDE6\uD83C\uDDEA"),
                keywords = listOf("Dubai", "Abu Dhabi", "Sharjah", "Al Ain", "Ajman", "Ras Al Khaimah", "Fujairah"),
            )
            "$region/Riyadh" -> Zone(
                zoneIdString, region,
                country = listOf("Saudi Arabia", "SA", "\uD83C\uDDF8\uD83C\uDDE6"),
                keywords = listOf("Riyadh", "Jeddah", "Mecca", "Medina", "Dammam", "Taif", "Tabuk", "Khobar"),
            )
            "$region/Qatar" -> Zone(
                zoneIdString, region,
                country = listOf("Qatar", "QA", "\uD83C\uDDF6\uD83C\uDDE6"),
                keywords = listOf("Doha", "Al Rayyan", "Al Wakrah", "Al Khor", "Lusail", "Umm Salal"),
            )
            "$region/Bahrain" -> Zone(
                zoneIdString, region,
                country = listOf("Bahrain", "BH", "\uD83C\uDDE7\uD83C\uDDED"),
                keywords = listOf("Manama", "Riffa", "Muharraq", "Hamad Town", "A'ali", "Isa Town"),
            )
            "$region/Kuwait" -> Zone(
                zoneIdString, region,
                country = listOf("Kuwait", "KW", "\uD83C\uDDF0\uD83C\uDDFC"),
                keywords = listOf("Kuwait City", "Al Ahmadi", "Hawalli", "As Salimiyah", "Sabah as Salim", "Al Farwaniyah"),
            )
            "$region/Muscat" -> Zone(
                zoneIdString, region,
                country = listOf("Oman", "OM", "\uD83C\uDDF4\uD83C\uDDF2"),
                keywords = listOf("Muscat", "Seeb", "Salalah", "Sohar", "Nizwa", "Sur", "Ibri"),
            )
            "$region/Aden" -> Zone(
                zoneIdString, region,
                country = listOf("Yemen", "YE", "\uD83C\uDDFE\uD83C\uDDEA"),
                keywords = listOf("Sana'a", "Aden", "Taiz", "Al Hudaydah", "Mukalla", "Ibb", "Dhamar"),
            )
            "$region/Baghdad" -> Zone(
                zoneIdString, region,
                country = listOf("Iraq", "IQ", "\uD83C\uDDEE\uD83C\uDDF6"),
                keywords = listOf("Baghdad", "Basra", "Mosul", "Erbil", "Najaf", "Karbala", "Kirkuk", "Sulaymaniyah"),
            )
            "$region/Tehran" -> Zone(
                zoneIdString, region,
                country = listOf("Iran", "IR", "\uD83C\uDDEE\uD83C\uDDF7"),
                keywords = listOf("Tehran", "Mashhad", "Isfahan", "Karaj", "Shiraz", "Tabriz", "Qom", "Ahvaz"),
            )
            "$region/Jerusalem" -> Zone(
                zoneIdString, region,
                country = listOf("Israel", "IL", "\uD83C\uDDEE\uD83C\uDDF1"),
                keywords = listOf("Jerusalem", "Tel Aviv", "Haifa", "Rishon LeZion", "Petah Tikva", "Ashdod", "Netanya", "Beersheba"),
            )
            "$region/Gaza",
            "$region/Hebron" -> Zone(
                zoneIdString, region,
                country = listOf("Palestine", "PS", "\uD83C\uDDF5\uD83C\uDDF8"),
                keywords = listOf("Gaza", "Hebron", "Nablus", "Ramallah", "Bethlehem", "Jenin", "Khan Yunis"),
            )
            "$region/Amman" -> Zone(
                zoneIdString, region,
                country = listOf("Jordan", "JO", "\uD83C\uDDEF\uD83C\uDDF4"),
                keywords = listOf("Amman", "Zarqa", "Irbid", "Russeifa", "Aqaba", "Madaba", "As-Salt"),
            )
            "$region/Beirut" -> Zone(
                zoneIdString, region,
                country = listOf("Lebanon", "LB", "\uD83C\uDDF1\uD83C\uDDE7"),
                keywords = listOf("Beirut", "Tripoli", "Sidon", "Tyre", "Jounieh", "Zahl\u00E9", "Baalbek"),
            )
            "$region/Damascus" -> Zone(
                zoneIdString, region,
                country = listOf("Syria", "SY", "\uD83C\uDDF8\uD83C\uDDFE"),
                keywords = listOf("Damascus", "Aleppo", "Homs", "Hama", "Latakia", "Deir ez-Zor", "Raqqa"),
            )
            else -> Zone(zoneIdString, region)
        }
    }
}