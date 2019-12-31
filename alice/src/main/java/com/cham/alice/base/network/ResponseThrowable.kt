package com.cham.alice.base.network

import com.cham.alice.base.network.ERROR

class ResponseThrowable : Exception {
    var code: Int
    var errMsg: String

    constructor(error: ERROR) {
        code = error.getKey()
        errMsg = error.getValue()
    }

    constructor(code: Int, msg: String) {
        this.code = code
        this.errMsg = msg
    }
}

