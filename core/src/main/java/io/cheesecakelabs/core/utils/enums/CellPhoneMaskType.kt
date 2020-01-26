package io.cheesecakelabs.core.utils.enums

enum class CellPhoneMaskType(val mask: String) {

    BR_WITH_CODE_AREA("(##) #####-####"),
    BR_WITH_COUNTRY_CODE("+## (##) #####-####"),
    US_WITH_CODE_AREA("(###) ###-####"),
    US_WITH_COUNTRY_CODE("+# (###) ###-####");
}