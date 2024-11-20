package ma.srmanager.coreapi.params

import ma.srmanager.coreapi.base.BaseEvent
import ma.srmanager.srjwt.coreapi.enums.global.ParamType


data class ParamCreatedEvent(
        override val id:Long,
        val intitule:String,
        val symbole:String,
        val description:String,
        val defaultValue:Boolean,
        val paramType: ParamType,
        override val username: String,

        ):BaseEvent<Long>(id,username)

data class ParamUpdatedEvent(
    override val id:Long,
    val intitule:String,
    val symbole:String,
    val description:String,
    val paramType: ParamType,
    override val username: String,

    ):BaseEvent<Long>(id,username)

data class ParamDeletedEvent(
    override val id:Long,
    val paramType: ParamType,
    override val username: String,

    ):BaseEvent<Long>(id,username)
