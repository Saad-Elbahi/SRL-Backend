package ma.srmanager.srmail.services;

import ma.srmanager.coreapi.base.SrResponseMessage;


public interface MailCmdService {

    SrResponseMessage sendNewMail(String to, String subject, String body);

/*
    void sendSimpleMail(String setFromAdress,String setFromPersonnel,
                        String setToAdress,String setToPersonnel,
                        String subject,String text);
    void sendMultipartMail();
*/

    SrResponseMessage send(String to, String subject, String text);

    /*void sendWithAttach(String from, String to, String subject,
                        String text, String attachName,
                        InputStreamSource inputStream) throws MessagingException;*/
}
