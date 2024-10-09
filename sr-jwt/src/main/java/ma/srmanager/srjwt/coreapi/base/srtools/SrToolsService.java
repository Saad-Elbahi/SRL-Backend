package ma.srmanager.srjwt.coreapi.base.srtools;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SrToolsService {

  //  String bigDecimaltoLetter(BigDecimal num);

    String doubleToLetterFr(double nombre);

    //String convertAmountToLetterFr(double nombre);

    String convertirFr(int chiffre);

    String convertirAr(int chiffre);

    String convertNumberToLetterFr(int nombre);

    String convertAmountToLetterAr(double nombre);

    String convertNumberToLetterAr(int nombre);

    double mathRound(double value);

    int getYear(LocalDate localDate);

    int getDay(LocalDate localDate);

    int getMonth(LocalDate localDate);

    int getTrimester(int mois);

    int getSemester(int mois);

    String getUsername(String token);
}
