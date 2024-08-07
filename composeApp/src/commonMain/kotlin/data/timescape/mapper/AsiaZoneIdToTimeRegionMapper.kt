package data.timescape.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class AsiaZoneIdToTimeRegionMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Asia
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            // South East Asia
            "$region/Brunei" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Brunei", "BN", "\uD83C\uDDE7\uD83C\uDDF3"),
                states = listOf("Brunei-Muara", "Belait", "Tutong", "Temburong"),
                cities = listOf("Bandar Seri Begawan", "Kuala Belait", "Seria", "Tutong", "Bangar")
            )
            "$region/Phnom_Penh" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Cambodia", "KH", "\uD83C\uDDF0\uD83C\uDDED"),
                states = listOf("Phnom Penh", "Battambang", "Siem Reap", "Sihanoukville", "Kampong Cham", "Krong Preah Sihanouk", "Krong Ta Khmau", "Kampong Speu"),
                cities = listOf("Phnom Penh", "Battambang", "Siem Reap", "Sihanoukville", "Kampong Cham", "Krong Preah Sihanouk", "Krong Ta Khmau", "Kampong Speu")
            )
            "$region/Jakarta",
            "$region/Makassar",
            "$region/Jayapura" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Indonesia", "ID", "\uD83C\uDDEE\uD83C\uDDE9"),
                states = listOf("Jakarta", "East Java", "West Java", "Central Java", "Banten", "West Kalimantan", "Central Kalimantan", "East Kalimantan", "South Kalimantan", "North Sumatra", "South Sumatra"),
                cities = listOf("Jakarta", "Surabaya", "Bandung", "Medan", "Semarang", "Bekasi", "Tangerang", "Makassar", "Depok", "Palembang")
            )
            "$region/Vientiane" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Laos", "LA", "\uD83C\uDDF1\uD83C\uDDE6"),
                states = listOf("Vientiane", "Savannakhet", "Luang Prabang", "Champasak", "Bokeo", "Houaphanh", "Khammouane", "Attapeu", "Xiangkhouang"),
                cities = listOf("Vientiane", "Pakse", "Luang Prabang", "Savannakhet", "Thakhek", "Muang Xay")
            )
            "$region/Kuala_Lumpur",
            "$region/Kuching" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Malaysia", "MY", "\uD83C\uDDF2\uD83C\uDDFE"),
                states = listOf("Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Perak", "Perlis", "Penang", "Sabah", "Sarawak", "Selangor", "Terengganu", "Labuan"),
                cities = listOf("Kuala Lumpur", "George Town", "Ipoh", "Johor Bahru", "Kuching", "Kota Kinabalu", "Shah Alam", "Malacca", "Alor Setar", "Seremban", "Klang", "Rawang"),
            )
            "$region/Yangon" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Myanmar", "MM", "\uD83C\uDDF2\uD83C\uDDF2"),
                states = listOf("Yangon", "Mandalay", "Ayeyarwady", "Sagaing", "Bago", "Magway", "Tanintharyi", "Kachin", "Kayah", "Kayin", "Chin", "Mon", "Rakhine", "Shan"),
                cities = listOf("Yangon", "Mandalay", "Naypyidaw", "Mawlamyine", "Bago", "Pathein", "Monywa", "Meiktila", "Sittwe", "Myeik", "Pakokku")
            )
            "$region/Manila" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Philippines", "PH", "\uD83C\uDDF5\uD83C\uDDED"),
                states = listOf("Metro Manila", "Calabarzon", "Central Luzon", "Central Visayas", "Western Visayas", "Davao Region", "Northern Mindanao", "Soccsksargen", "Cagayan Valley", "Caraga", "Cordillera Administrative Region", "Ilocos Region", "Mimaropa", "Eastern Visayas", "Zamboanga Peninsula", "Autonomous Region in Muslim Mindanao"),
                cities = listOf("Manila", "Quezon City", "Davao City", "Cebu City", "Zamboanga City", "Taguig", "Antipolo", "Cagayan de Oro", "Cainta", "Pasig")
            )
            "$region/Singapore" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Singapore", "SG", "\uD83C\uDDF8\uD83C\uDDEC"),
                states = listOf("Singapore"),
                cities = listOf("Singapore")
            )
            "$region/Bangkok" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Thailand", "TH", "\uD83C\uDDF9\uD83C\uDDED"),
                states = listOf("Bangkok", "Chiang Mai", "Nakhon Ratchasima", "Khon Kaen", "Udon Thani", "Nakhon Sawan", "Chonburi", "Surat Thani", "Phuket", "Ayutthaya"),
                cities = listOf("Bangkok", "Nonthaburi", "Nakhon Ratchasima", "Chiang Mai", "Hat Yai", "Udon Thani", "Pak Kret", "Khon Kaen", "Nakhon Pathom", "Chiang Rai", "Nakhon Sawan", "Ubon Ratchathani", "Korat", "Surat Thani", "Phuket", "Samut Prakan", "Rayong", "Nakhon Si Thammarat", "Ayutthaya", "Chonburi")
            )
            "$region/Dili" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Timor-Leste", "TL", "\uD83C\uDDF9\uD83C\uDDF1"),
                states = listOf("Dili", "Baucau", "Lautém", "Viqueque", "Manufahi", "Manatuto", "Aileu", "Bobonaro", "Ermera"),
                cities = listOf("Dili", "Baucau", "Lautém", "Viqueque", "Suai", "Manatuto")
            )
            "$region/Ho_Chi_Minh",
            "$region/Saigon" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Vietnam", "VN", "\uD83C\uDDFB\uD83C\uDDF3"),
                states = listOf("Ho Chi Minh City", "Hanoi", "Hai Phong", "Can Tho", "Da Nang", "Bien Hoa", "Vung Tau", "Nha Trang", "Hue", "Buon Ma Thuot", "Pleiku", "My Tho"),
                cities = listOf("Ho Chi Minh City", "Hanoi", "Hai Phong", "Can Tho", "Da Nang", "Bien Hoa", "Vung Tau", "Nha Trang", "Hue", "Buon Ma Thuot", "Pleiku", "My Tho")
            )
            // East Asia
            "$region/Shanghai",
            "$region/Urumqi" -> TimeRegion(
                zoneIdString, region,
                country = listOf("China", "CN", "\uD83C\uDDE8\uD83C\uDDF3"),
                states = listOf("Shanghai", "Beijing", "Guangdong", "Zhejiang"),
                cities = listOf("Shanghai", "Beijing", "Guangzhou", "Shenzhen")
            )
            "$region/Tokyo" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Japan", "JP", "\uD83C\uDDEF\uD83C\uDDF5"),
                states = listOf("Tokyo", "Osaka", "Kyoto", "Kanagawa"),
                cities = listOf("Tokyo", "Osaka", "Kyoto", "Yokohama")
            )
            "$region/Seoul" -> TimeRegion(
                zoneIdString, region,
                country = listOf("South Korea", "KR", "\uD83C\uDDF0\uD83C\uDDF7"),
                states = listOf("Seoul", "Busan", "Gyeonggi", "Incheon"),
                cities = listOf("Seoul", "Busan", "Incheon", "Daegu")
            )
            "$region/Pyongyang" -> TimeRegion(
                zoneIdString, region,
                country = listOf("North Korea", "KP", "\uD83C\uDDF0\uD83C\uDDF5"),
                states = listOf("Pyongyang", "South Pyongan", "North Hwanghae", "South Hamgyong"),
                cities = listOf("Pyongyang", "Hamhung", "Nampo", "Wonsan")
            )
            "$region/Ulaanbaatar" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Mongolia", "MN", "\uD83C\uDDF2\uD83C\uDDF3"),
                states = listOf("Ulaanbaatar", "Darkhan-Uul", "Orkhon", "Selenge"),
                cities = listOf("Ulaanbaatar", "Erdenet", "Darkhan", "Choibalsan")
            )
            "$region/Taipei" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Taiwan", "TW", "\uD83C\uDDF9\uD83C\uDDFC"),
                states = listOf("Taipei", "New Taipei", "Kaohsiung", "Taichung"),
                cities = listOf("Taipei", "New Taipei", "Kaohsiung", "Taichung")
            )
            // South Asia
            "$region/Kabul" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Afghanistan", "AF", "\uD83C\uDDE6\uD83C\uDDEB"),
                states = emptyList(),
                cities = listOf("Kabul", "Kandahar", "Herat", "Mazar-i-Sharif", "Jalalabad", "Kunduz", "Lashkar Gah", "Taloqan", "Puli Khumri", "Ghazni"),
            )
            "$region/Dhaka" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Bangladesh", "BD", "\uD83C\uDDE7\uD83C\uDDE9"),
                states = emptyList(),
                cities = listOf("Dhaka", "Chittagong", "Khulna", "Rajshahi", "Sylhet", "Barisal", "Rangpur", "Comilla", "Narayanganj", "Gazipur"),
            )
            "$region/Thimphu" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Bhutan", "BT", "\uD83C\uDDE7\uD83C\uDDF9"),
                states = emptyList(),
                cities = listOf("Thimphu", "Phuntsholing", "Paro", "Punakha", "Wangdue Phodrang", "Trashigang", "Mongar", "Trongsa", "Gelephu", "Samdrup Jongkhar"),
            )
            "$region/Kolkata" -> TimeRegion(
                zoneIdString, region,
                country = listOf("India", "IN", "\uD83C\uDDEE\uD83C\uDDF3"),
                states = emptyList(),
                cities = listOf("Mumbai", "New Delhi", "Bengaluru", "Kolkata", "Chennai", "Hyderabad", "Pune", "Ahmedabad", "Surat", "Jaipur"),
            )
            "$region/Kathmandu" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Nepal", "NP", "\uD83C\uDDF3\uD83C\uDDF5"),
                states = emptyList(),
                cities = listOf("Kathmandu", "Pokhara", "Lalitpur", "Biratnagar", "Birgunj", "Dharan", "Bharatpur", "Bhaktapur", "Butwal", "Dhangadhi"),
            )
            "$region/Karachi" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Pakistan", "PK", "\uD83C\uDDF5\uD83C\uDDF0"),
                states = emptyList(),
                cities = listOf("Karachi", "Lahore", "Faisalabad", "Rawalpindi", "Gujranwala", "Peshawar", "Multan", "Hyderabad", "Islamabad", "Quetta"),
            )
            "$region/Colombo" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Sri Lanka", "LK", "\uD83C\uDDF1\uD83C\uDDF0"),
                states = emptyList(),
                cities = listOf("Colombo", "Dehiwala-Mount Lavinia", "Moratuwa", "Jaffna", "Negombo", "Pita Kotte", "Kotte", "Kandy", "Trincomalee", "Kalmunai")
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}