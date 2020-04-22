package com.example.nonoshow

import android.app.Application

class MyApplication : Application() { /*하나의 인스턴스를 가지는 클래스*/
    companion object { /*static at Kotlin*/
        var ID = "default" /*일시적 사용 : 보안 취약 * 제거될코드?*/
        var PW = "default"
        var isLogined = false
        var loginToken = "" /*서버에서 암호화해서 보내준 녀석을 저장<나중에 업데이트>*/
    }
}