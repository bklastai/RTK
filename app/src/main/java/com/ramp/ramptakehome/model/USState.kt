package com.ramp.ramptakehome.model

enum class USState {
    AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL, GA, HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN,
    MS, MO, MT, NE, NV, NH, NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, SD, TN, TX, UT, VT, VA, VI,
    WA, WV, WI, WY;

    companion object {
        fun fromString(input: String): USState? {
            for (state in entries) {
                if (input == state.name) return state
            }
            return null
        }
    }
}