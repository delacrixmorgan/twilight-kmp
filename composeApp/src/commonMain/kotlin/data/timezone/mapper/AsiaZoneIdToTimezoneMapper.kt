package data.timezone.mapper

import data.model.Region
import data.model.TimeRegion
import data.utils.Mapper

class AsiaZoneIdToTimezoneMapper : Mapper<List<String>, List<TimeRegion>> {
    companion object {
        private val region = Region.Asia
    }

    override fun invoke(input: List<String>): List<TimeRegion> = input.map { zoneIdString ->
        when (zoneIdString) {
            "$region/Bangkok" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Thailand", "TH", "\uD83C\uDDF9\uD83C\uDDED"),
                states = listOf("Bangkok", "Chiang Mai", "Nakhon Ratchasima", "Khon Kaen", "Udon Thani", "Nakhon Sawan", "Chonburi", "Surat Thani", "Phuket", "Ayutthaya"),
                cities = listOf("Bangkok", "Nonthaburi", "Nakhon Ratchasima", "Chiang Mai", "Hat Yai", "Udon Thani", "Pak Kret", "Khon Kaen", "Nakhon Pathom", "Chiang Rai", "Nakhon Sawan", "Ubon Ratchathani", "Korat", "Surat Thani", "Phuket", "Samut Prakan", "Rayong", "Nakhon Si Thammarat", "Ayutthaya", "Chonburi")
            )
            "$region/Kuala_Lumpur",
            "$region/Kuching" -> TimeRegion(
                zoneIdString, region,
                country = listOf("Malaysia", "MY", "\uD83C\uDDF2\uD83C\uDDFE"),
                states = listOf("Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Perak", "Perlis", "Penang", "Sabah", "Sarawak", "Selangor", "Terengganu", "Labuan"),
                cities = listOf("Kuala Lumpur", "George Town", "Ipoh", "Johor Bahru", "Kuching", "Kota Kinabalu", "Shah Alam", "Malacca", "Alor Setar", "Seremban", "Klang", "Rawang"),
            )
            else -> TimeRegion(zoneIdString, region)
        }
    }
}