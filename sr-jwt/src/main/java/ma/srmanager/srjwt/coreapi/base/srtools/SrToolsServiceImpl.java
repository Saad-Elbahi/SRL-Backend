package ma.srmanager.srjwt.coreapi.base.srtools;
//import com.ibm.icu.math.BigDecimal;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import ma.srmanager.srjwt.coreapi.jwt.JWTUtil;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
@Slf4j
@Transactional
public class SrToolsServiceImpl implements SrToolsService {

    @Override
    public String doubleToLetterFr(double nombre) {
        //double net = nombre;
        nombre = Math.round(nombre * 100.0) / 100.0;

        int partieEntiere = 0, partieDecimale = 0;
        String resultat1, resultat2;
        //partie entière
        partieEntiere = (int) nombre;
        //partie décimale
        //partieDecimale = (int) ((nombre - partieEntiere) * 100);
        partieDecimale = (int) (BigDecimal.valueOf(nombre).divideAndRemainder(BigDecimal.ONE)[1].floatValue() * 100);

        //System.out.println(partieDecimale);

        resultat1 = convertirFr(partieEntiere);
        resultat2 = convertirFr(partieDecimale);
        return resultat1;

    }

    /*@Override
    public String convertAmountToLetterFr(double nombre) {

        log.info("convertAmountToLetterFr");

        //double net = nombre;
        nombre = Math.round(nombre * 100.0) / 100.0;

        log.info(String.valueOf(nombre));

        int partieEntiere = 0, partieDecimale = 0;
        String resultat1, resultat2;
        //partie entière
        partieEntiere = (int) nombre;
        //partie décimale
        //partieDecimale = (int) ((nombre - partieEntiere) * 100);
        partieDecimale = (int) (BigDecimal.valueOf(nombre).divideAndRemainder(BigDecimal.ONE)[1].floatValue() * 100);

        log.info("Nombre         =>" + String.valueOf(nombre));
        log.info("partieEntiere  =>" + String.valueOf(partieEntiere));
        log.info("partieDecimale =>" + String.valueOf(partieDecimale));

        resultat1 = convertirFr(partieEntiere);
        resultat2 = convertirFr(partieDecimale);

        log.info("*************");
        log.info("partieEntiere txt  =>" + resultat1);
        log.info("partieDecimale txt =>" + resultat2);

        //System.out.println(resultat2);

        if ((resultat2 != null && !resultat2.isEmpty() && resultat1 != null && !resultat1.isEmpty())) {
            return resultat1 + "Dhs " + resultat2 + " Cts";
        } else if (resultat2 != null && !resultat2.isEmpty()) {
            return resultat1 + " Dhs ";
        } else
            return "";
    }
*/
    @Override
    public String convertirFr(int chiffre) {
        int milier, centaine, dizaine, unite, reste, y;
        boolean dix = false;
        String lettre = "";
        //strcpy(lettre, "");

        reste = chiffre;

        for (int i = 1000000000; i >= 1; i /= 1000) //1 000 000 000 -1 000 000 - 1 000 - 1 (7 685 576.5)
        //reste=reste-y*i
        {
            y = reste / i;
            if (y != 0) {
                milier = y / 1000;
                centaine = y / 100;
                dizaine = (y - centaine * 100) / 10;
                unite = y - (centaine * 100) - (dizaine * 10);

                //System.out.println("centaine" + centaine);
                //System.out.println("dizaine" + dizaine);
                //System.out.println("unite" + unite);

                switch (centaine) {
                    case 0:
                        break;
                    case 1:
                        lettre += "Cent ";
                        break;
                    case 2:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Deux Cents ";
                        else
                            lettre += "Deux Cent ";
                        break;
                    case 3:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Trois cents ";
                        else
                            lettre += "Trois Cent ";
                        break;
                    case 4:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Quatre cents ";
                        else
                            lettre += "Quatre Cent ";
                        break;
                    case 5:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Cinq cents ";
                        else
                            lettre += "Cinq Cent ";
                        break;
                    case 6:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Six cents ";
                        else
                            lettre += "Six Cent ";
                        break;
                    case 7:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Sept cents ";
                        else
                            lettre += "Sept Cent ";
                        break;
                    case 8:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Huit cents ";
                        else
                            lettre += "Huit Cent ";
                        break;
                    case 9:
                        if ((dizaine == 0) && (unite == 0))
                            lettre += "Neuf cents ";
                        else
                            lettre += "Neuf Cent ";
                        break;
                }// endSwitch(centaine)

                switch (dizaine) {
                    case 0:
                        break;
                    case 1:
                        dix = true;
                        break;
                    case 2:
                        lettre += "Vingt ";
                        break;
                    case 3:
                        lettre += "Trente ";
                        break;
                    case 4:
                        lettre += "Quarante ";
                        break;
                    case 5:
                        lettre += "Cinquante ";
                        break;
                    case 6:
                        lettre += "Soixante ";
                        break;
                    case 7:
                        dix = true;
                        lettre += "Soixante ";
                        break;
                    case 8:
                        lettre += "Quatre-vingt ";
                        break;
                    case 9:
                        dix = true;
                        lettre += "Quatre-vingt ";
                        break;
                } // endSwitch(dizaine)

                switch (unite) {
                    case 0:
                        if (dix)
                            lettre += "Dix ";
                        break;
                    case 1:
                        if (dix)
                            lettre += "Onze ";
                            //else if (centaine != 0 || dizaine != 0)
                        else if (i != 1000)
                            lettre += "Un ";
                        break;
                    case 2:
                        if (dix)
                            lettre += "Douze ";
                        else
                            lettre += "Deux ";
                        break;
                    case 3:
                        if (dix)
                            lettre += "Treize ";
                        else
                            lettre += "Trois ";
                        break;
                    case 4:
                        if (dix)
                            lettre += "Quatorze ";
                        else
                            lettre += "Quatre ";
                        break;
                    case 5:
                        if (dix)
                            lettre += "Quinze ";
                        else
                            lettre += "Cinq ";
                        break;
                    case 6:
                        if (dix)
                            lettre += "Seize ";
                        else
                            lettre += "Six ";
                        break;
                    case 7:
                        if (dix)
                            lettre += "Dix-sept ";
                        else
                            lettre += "Sept ";
                        break;
                    case 8:
                        if (dix)
                            lettre += "Dix-huit ";
                        else
                            lettre += "Huit ";
                        break;
                    case 9:
                        if (dix)
                            lettre += "Dix-neuf ";
                        else
                            lettre += "Neuf ";
                        break;
                } // endSwitch(unite)

                switch (i) {
                    case 1000000000:
                        if (y > 1)
                            lettre += "Milliards ";
                        else
                            lettre += "Milliard ";
                        break;
                    case 1000000:
                        if (y > 1)
                            lettre += "Millions ";
                        else
                            lettre += "Million ";
                        break;
                    case 1000:
                        lettre += "Mille ";
                        break;
                }
            } // end if(y!=0)
            reste -= y * i;
            dix = false;
        } // end for
        if (lettre.length() == 0)
            lettre += "Zero";
        if (lettre.equals("Un Mille"))
            lettre = "Mille";
        return lettre;
    }

    @Override
    public String convertirAr(int chiffre) {
        int milier, centaine, dizaine, unite;
        //int dix = 0;
        String lettre = "";

        milier = chiffre / 1000;
        centaine = (chiffre - milier * 1000) / 100;
        dizaine = (chiffre - (milier * 1000) - (centaine * 100)) / 10;
        unite = chiffre - (milier * 1000) - (centaine * 100) - (dizaine * 10);
        switch (milier) {
            case 0:
                break;
            case 1:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " الف";
                else
                    lettre += "الف و";
                break;
            case 2:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " الفان";
                else
                    lettre += "الفان و";
                break;
            case 3:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " ثلاثةالاف";
                else
                    lettre += "اربعةالاف و";
                break;
            case 4:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " اربعةالاف";
                else
                    lettre += "اربعةالاف و";
                break;
            case 5:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " خمسةالاف";
                else
                    lettre += "خمسةالاف و";
                break;
            case 6:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " ستةالاف";
                else
                    lettre += "ستةالاف و";
                break;
            case 7:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " سبعةالاف";
                else
                    lettre += "سبعةالاف و";
                break;
            case 8:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " ثمانيةالاف";
                else
                    lettre += "ثمانيةالاف و";
                break;
            case 9:
                if (centaine == 0 && dizaine == 0 && unite == 0)
                    lettre += " تسعةالاف";
                else
                    lettre += "تسعةالاف و";
                break;
        }// endSwitch(milier)
        switch (centaine) {
            case 0:
                break;
            case 1:
                if (dizaine == 0 && unite == 0)
                    lettre += " مائة";
                else
                    lettre += "مائة و";
                break;
            case 2:
                if (dizaine == 0 && unite == 0)
                    lettre += " مائتين";
                else
                    lettre += "مائتين و";
                break;
            case 3:
                if (dizaine == 0 && unite == 0)
                    lettre += " ثلاثمائة";
                else
                    lettre += "ثلاثمائة و";
                break;
            case 4:
                if (dizaine == 0 && unite == 0)
                    lettre += " اربعمائة";
                else
                    lettre += "اربعمائة و";
                break;
            case 5:
                if (dizaine == 0 && unite == 0)
                    lettre += " خمسمائة";
                else
                    lettre += "خمسمائة و";
                break;
            case 6:
                if (dizaine == 0 && unite == 0)
                    lettre += " ستمائة";
                else
                    lettre += "ستمائة و";
                break;
            case 7:
                if (dizaine == 0 && unite == 0)
                    lettre += " سبعمائة";
                else
                    lettre += "سبعمائة و";
                break;
            case 8:
                if (dizaine == 0 && unite == 0)
                    lettre += " ثمانمائة";
                else
                    lettre += "ثمانمائة و";
                break;
            case 9:
                if (dizaine == 0 && unite == 0)
                    lettre += " تسعمائة";
                else
                    lettre += "تسعمائة و";
                break;
        }// endSwitch(centaine)
        switch (unite) {
            case 0:
                break;
            case 1:
                if (dizaine == 0)
                    lettre += "واحد";
                else if (dizaine == 1)
                    lettre += "احدا";
                else
                    lettre += "واحد و";
                break;
            case 2:
                if (dizaine == 0)
                    lettre += "اثنان";
                else if (dizaine == 1)
                    lettre += "اثنا";
                else
                    lettre += " اثنان و";
                break;
            case 3:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " ثلاثة";
                else
                    lettre += "ثلاثة و";
                break;
            case 4:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " اربعة";
                else
                    lettre += "اربعة و";
                break;
            case 5:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " خمسة";
                else
                    lettre += "خمسة و";
                break;
            case 6:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " ستة";
                else
                    lettre += "ستة و";
                break;
            case 7:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " سبعة";
                else
                    lettre += "سبعة و";
                break;
            case 8:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " ثمانية";
                else
                    lettre += "ثمانية و";
                break;
            case 9:
                if (dizaine == 0 || dizaine == 1)
                    lettre += " تسعة";
                else
                    lettre += "تسعة و";
                break;
        } // endSwitch(unite)

        switch (dizaine) {
            case 0:
                break;
            case 1:
                lettre += " عشرة ";
                break;
            case 2:
                lettre += " عشرون ";
                break;
            case 3:
                lettre += " ثلاثون ";
                break;
            case 4:
                lettre += " اربعون ";
                break;
            case 5:
                lettre += " خمسون ";
                break;
            case 6:
                lettre += " ستون ";
                break;
            case 7:
                lettre += " سبعون ";
                break;
            case 8:
                lettre += " ثمانون ";
                break;
            case 9:
                lettre += " تسعون ";
                break;
        } // endSwitch(dizaine)


        if (lettre.length() == 0)
            lettre += " صفر ";

        return lettre;
    }

    @Override
    public String convertNumberToLetterFr(int nombre) {
        String resultat1;
        resultat1 = convertirFr(nombre);
        if ((resultat1 != null && !resultat1.isEmpty()))
            return resultat1;
        else
            return "";
    }

    @Override
    public String convertAmountToLetterAr(double nombre) {
        int partieEntiere = 0, partieDecimale = 0;
        String resultat1, resultat2;
        //partie entière
        partieEntiere = (int) nombre;
        //partie décimale
        partieDecimale = (int) ((nombre - partieEntiere) * 100);
        resultat1 = convertirAr(partieEntiere);
        resultat2 = convertirAr(partieDecimale);
        if ((resultat1 != null && !resultat1.isEmpty()) && (resultat2 != null && !resultat2.isEmpty())) {
            return resultat1 + " Dhs " + resultat2 + " Cts";
        } else if (resultat1 != null && !resultat1.isEmpty()) {
            return resultat1 + " Dhs ";
        } else
            return "";
    }

    @Override
    public String convertNumberToLetterAr(int nombre) {
        String resultat1;
        resultat1 = convertirAr(nombre);
        if (resultat1 != null && !resultat1.isEmpty())
            return resultat1;
        else
            return "";
    }

    @Override
    public double mathRound(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public int getYear(LocalDate localDate) {
        return localDate.getYear();
    }

    @Override
    public int getDay(LocalDate localDate) {
        return localDate.getDayOfMonth();
    }

    @Override
    public int getMonth(LocalDate localDate) {
        return localDate.getMonthValue();
    }

    @Override
    public int getTrimester(int mois) {
        if (mois >= 1 && mois <= 3) {
            return 1;
        } else if (mois >= 4 && mois <= 6) {
            return 2;
        } else if (mois >= 7 && mois <= 9) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public int getSemester(int mois) {
        if ((mois >= 1 && mois <= 6)) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String getUsername(String token) {
        Algorithm algo1 = Algorithm.HMAC256(JWTUtil.JWT_SECRET);
        JWTVerifier jwtVerifier = JWT.require(algo1).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token.substring(JWTUtil.JWT_HEADER_PREFIX.length()));
        String username = decodedJWT.getSubject();
        return username;
    }


   /* private static final DecimalFormat DFORMAT = new DecimalFormat("###0.00");
    private static final NumberFormat FORMATTER = new RuleBasedNumberFormat(ULocale.FRANCE,RuleBasedNumberFormat.SPELLOUT);

    @Override
    public   String bigDecimaltoLetter(BigDecimal num) {
        //log.info("bigDecimaltoLetter");
        String result;
        String[] s = DFORMAT.format(num).split(Pattern.quote(String.valueOf(DFORMAT.getDecimalFormatSymbols().getDecimalSeparator())));
        BigInteger intPart = new BigInteger(s[0]);
        if ( s.length==1 ) {
            result= FORMATTER.format(intPart);
        }
        else {
            BigInteger decPart = new BigInteger(s[1]);
            result= FORMATTER.format(intPart)
                    // pour les parties fixes il faudrait faire un resourcebundle
                    + " Dh"
                    + (intPart.intValue()>1?"s":"")
                    + " et "
                    + FORMATTER.format(decPart)
                    + " Ct"
                    + (decPart.intValue()>1?"s":"");
        }
        //log.info(result);
        return result;
    }
*/
}
