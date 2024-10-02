package ma.srmanager.coreapi.base


abstract class BaseCommand<T>
    ( open val id:T,
      open val  username:String
    )

abstract class BaseEvent<T>(
    open val id:T,
    open val  username:String
    )

data class SrRequestMessage(
    var message:String?=null,
)

data class SrResponseMessage(
    var value:Boolean?=false,
    var message:String?=null,
)

data class BioTimeResponseMessage(
    var code:Int,
    var msg:String?=null,
)

