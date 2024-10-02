package ma.srmanager.coreapi.params.queries

import ma.srmanager.coreapi.enums.global.ParamType

data class GetParamsByTypeQuery(
    var paramType: ParamType
)

data class GetParamByIdQuery(
    var id:Long
)

data class GetParamByIntituleQuery(
    var intitule:String
)
