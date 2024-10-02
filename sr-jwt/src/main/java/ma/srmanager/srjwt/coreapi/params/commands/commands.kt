package ma.srmanager.coreapi.params

import ma.srmanager.coreapi.base.BaseCommand
import ma.srmanager.coreapi.enums.global.ParamType


data class CreateParamCommand(
    override val id:Long,
    val intitule:String,
    val symbole:String,
    val description:String,
    val defaultValue:Boolean,
    val paramType: ParamType,
    override val username: String,

    ): BaseCommand<Long>(id,username)

data class UpdateParamCommand(
    override val id:Long,
    val intitule:String,
    val symbole:String,
    val description:String,
    val paramType: ParamType,
    override val username: String,

    ):BaseCommand<Long>(id,username)

data class DeleteParamCommand(
    override val id:Long,
    val paramType: ParamType,
    override val username: String,

    ):BaseCommand<Long>(id,username)
