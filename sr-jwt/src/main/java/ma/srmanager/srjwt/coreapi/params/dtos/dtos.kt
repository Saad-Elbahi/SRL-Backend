package ma.srmanager.coreapi.params.dtos

import ma.srmanager.coreapi.enums.contact.Civilite
import ma.srmanager.coreapi.enums.global.ParamType
import ma.srmanager.coreapi.soustraitance.dtos.paiements.VentilationReglementDTO
import java.time.LocalDate

data class CreateParamRequestDTO(
    val username: String = "",
    var intitule: String = "",
    var symbole: String = "",
    var description: String = "",
    var string1: String? = null,
    var string2: String? = null,
    var string3: String? = null,
    var string4: String? = null,
    var string5: String? = null,
    var defaultValue: Boolean = false,
    var paramType: ParamType = ParamType.ACTIVITY,
)

data class UpdateParamRequestDTO(
    val username: String = "",
    var id: Long = 0,
    var intitule: String = "",
    var symbole: String = "",
    var description: String = "",
    var string1: String? = null,
    var string2: String? = null,
    var string3: String? = null,
    var string4: String? = null,
    var string5: String? = null,
    var paramType: ParamType = ParamType.ACTIVITY,

    )

data class CreateSocieteDTO(
    val raisonSociele: String? = null,
    var sigle: String? = null,
    var pathLogo: String? = null,
    var email: String? = null,
    var adresse1: String? = null,
    var adresse2: String? = null,
    var codePostal: String? = null,
    var villeId: Long? = null,
    var ice: String? = null,
    var indiceFiscal: String? = null,
    var patente: String? = null,
    var rc: String? = null,
    var cnss: String? = null,
    var banques: List<BanqueDto> = listOf<BanqueDto>(),

    var civiliteManager: Civilite = Civilite.M,
    var prenomManager: String? = null,
    var nomManager: String? = null,
    var cinManager: String? = null,
    var gsmManager: String? = null,
    var codeManager: String? = null,
    var avatarManager: String? = null,
    var emailManager: String? = null,
    )


data class UpdateSocieteDTO(
    val id: Long? = null,
    val raisonSociele: String? = null,
    var sigle: String? = null,
    var pathLogo: String? = null,
    var email: String? = null,
    var adresse1: String? = null,
    var adresse2: String? = null,
    var codePostal: String? = null,
    var villeId: Long? = null,
    var ice: String? = null,
    var indiceFiscal: String? = null,
    var patente: String? = null,
    var rc: String? = null,
    var cnss: String? = null,
    var dateCreation: LocalDate = LocalDate.now(),
    var banques: List<BanqueDto> = listOf<BanqueDto>(),

    var civiliteManager: Civilite = Civilite.M,
    var prenomManager: String? = null,
    var nomManager: String? = null,
    var cinManager: String? = null,
    var gsmManager: String? = null,
    var codeManager: String? = null,
    var avatarManager: String? = null,
    var emailManager: String? = null,
    )


data class BanqueDto(
    val intitule: String? = null,
    var sigle: String? = null,
    var agence: String? = null,
    var rib: String? = null,
    var adresse1: String? = null,
)
