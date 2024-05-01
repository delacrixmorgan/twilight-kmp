package nav

enum class Screens(val route: String) {
    Dashboard("dashboard"),
    FormSelectLocationType("form/select-location-type"),
    FormSetupName("form/setup-name"),
    FormSelectTimeRegion("form/select-time-region"),
    FormSummary("form/summary"),
}