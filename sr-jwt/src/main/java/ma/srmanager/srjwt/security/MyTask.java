package ma.srmanager.srjwt.security;

import java.util.Date;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println(new Date() + " Execution de ma tache");

    }
}
