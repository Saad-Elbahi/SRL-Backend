package ma.srmanager.srmail.services;

import lombok.extern.slf4j.Slf4j;
import ma.srmanager.coreapi.base.SrResponseMessage;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@Transactional
public class MailCmdServiceImpl implements MailCmdService {

    private final JavaMailSender mailSender;

    public MailCmdServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public SrResponseMessage sendNewMail(String to, String subject, String body) {
        log.info("sendNewMail => Start");
        /*try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setCc("support@srmanager.ma");
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            return new SrResponseMessage(true, "Sended");

        } catch (Exception e) {
            log.info("Send Mail ==> Error");
            log.info(e.getMessage());
        }
*/

        String result = null;
        MimeMessage message = mailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            String htmlMsg = "<body style='border:2px solid black'>"
                    +"<h1> Bonjour Mr Abousalih </h1"
                    +"<h2>"+ body+"</h2>"
                    + " </body>";

            message.setContent(htmlMsg, "text/html");
            helper.setTo(to);
            helper.setCc("support@srmanager.ma");
            helper.setSubject(subject);
            result = "success";
            mailSender.send(message);
        } catch (MessagingException e) {
            log.info("Send Mail ==> Error");
            throw new MailParseException(e);
        } finally {
            if (result != "success") {
                log.info("Send Mail ==> Error");
                result = "fail";
            }
        }


        return new SrResponseMessage(false, "Not Sended");

    }

   /* @Override
    public void sendSimpleMail(String setFromAdress,String setFromPersonnel,
                               String setToAdress,String setToPersonnel,
                               String subject,String text) {
// [START simple_example]
        try {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(setFromAdress, setFromPersonnel));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(setToAdress, setToPersonnel));
            msg.setSubject(subject);
            msg.setText(text);
            Transport.send(msg);
        } catch (Exception e) {
            log.info(e.getMessage());
        } *//*catch (UnsupportedEncodingException e) {
            log.info(e.getMessage());
        } catch (MessagingException e) {
            log.info(e.getMessage());
        }*//*
        // [END simple_example]
    }
*/


  /*
    @Override
    public void sendMultipartMail() {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "...";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("user@example.com", "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText(msgBody);

            // [START multipart_example]
            String htmlBody = "";          // ...
            byte[] attachmentData = null;  // ...
            Multipart mp = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);

            MimeBodyPart attachment = new MimeBodyPart();
            InputStream attachmentDataStream = new ByteArrayInputStream(attachmentData);
            attachment.setFileName("manual.pdf");
            attachment.setContent(attachmentDataStream, "application/pdf");
            mp.addBodyPart(attachment);

            msg.setContent(mp);
            // [END multipart_example]

            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        } catch (UnsupportedEncodingException e) {
            // ...
        }
    }
*/


    @Override
    public SrResponseMessage send(String to, String subject, String text) {

        String from = "support@srmanager.ma";
        String cc = "support@srmanager.ma";

        /*try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setCc(cc);
            message.setSubject(subject);
            message.setText(text);
            log.info("Send Mail ==> START");

            mailSender.send(message);

            return new SrResponseMessage(true, "Sended");
        } catch (Exception e) {
            log.info("Send Mail ==> Error");
            log.info(e.getMessage());
        }*/

        String result = null;
        MimeMessage message = mailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

            message.setContent(text, "text/html");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            result = "success";
            mailSender.send(message);
        } catch (MessagingException e) {
            log.info("Send Mail ==> Error");
            throw new MailParseException(e);
        } finally {
            if (result != "success") {
                log.info("Send Mail ==> Error");
                result = "fail";
            }
        }

        return new SrResponseMessage(false, "Not Sended");

    }

    /*@Override
    public void sendWithAttach(String from, String to, String subject,
                               String text, String attachName,
                               InputStreamSource inputStream) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.addAttachment(attachName, inputStream);
            mailSender.send(message);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }*/
}
