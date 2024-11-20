package ma.srmanager.coreapi.mail

data class MailSendDTO(
    var to: String = "",
    var subject: String = "",
    var text: String = "",
)
