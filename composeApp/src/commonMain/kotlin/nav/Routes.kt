package nav

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Dashboard : Routes()

    @Serializable
    data object Today : Routes()

    @Serializable
    data object Settings : Routes()

    @Serializable
    data object FormSetupName : Routes()

    @Serializable
    data object FormSelectZone : Routes()

    @Serializable
    data object FormSummary : Routes()

    @Serializable
    data object AppInfo : Routes()
}