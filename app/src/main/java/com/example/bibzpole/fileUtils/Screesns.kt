package com.example.bibzpole.fileUtils



sealed class Screenes(val route:String){
        data object loginScreen : Screenes("loginScreen")
        data object homeScreen : Screenes("homeScreen")
        data object Signup : Screenes("Signup")
        data object Favlist : Screenes("Favlist")
        data object Details : Screenes("Details")
        data object GetStarted : Screenes("GetStarted")
        data object Splash : Screenes("Splash")

        fun withArguments(vararg args :String):String{
                return buildString {
                        append(route)
                        args.forEach {
                                append("/$it")
                        }
                }
        }

}