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
            else -> Zone(zoneIdString, region)
        }
    }
}