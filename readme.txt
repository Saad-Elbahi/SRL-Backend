Demarche deploiement:
les fichiers a modifier:
 1- application.properties (Tous les projets)
 2- ma.srmanager.coreapi.base.SrUtils.java :

     //Dev
            //    public static final String marcheHost="http://localhost:6101";
            //    public static final String paramsHost="http://localhost:6102";
            //    public static final String soustraitanceHost="http://localhost:6103";
            //    public static final String caisseHost="http://localhost:6104";
            //    public static final String rhHost="http://localhost:6105";


     //Prod
                    public static final String marcheHost="http://node96167-sr-marche.jcloud-ver-jpe.ik-server.com:6101";
                    public static final String paramsHost="http://node96154-sr-params.jcloud-ver-jpe.ik-server.com:6102";
                    public static final String soustraitanceHost="http://node96175-sr-sous-straitance.jcloud-ver-jpe.ik-server.com:6103";
                    public static final String caisseHost="http://node102842-sr-caisse.jcloud-ver-jpe.ik-server.com:6104";
                    public static final String rhHost="http://node102851-sr-rh.jcloud-ver-jpe.ik-server.com:6105";
