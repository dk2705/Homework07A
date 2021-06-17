package homework;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Transporter implements Runnable {

    private String name;
    private int timeMs;
    private WorkProcess workProcess;

    public Transporter(String name, int timeMs, WorkProcess workProcess) {
        this.name = name;
        this.timeMs = timeMs;
        this.workProcess = workProcess;
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

    public synchronized void move() throws InterruptedException {
        System.out.println("Moving started: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        Thread.sleep(timeMs);
        System.out.println("Moving finished: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
    }

    @Override
    public void run() {
        while(true){ //перевозчик ничего не знает о состоянии кучи, поэтому цикл бесконечный (завершится как демон)
            if (workProcess.getFlagBlockTransporter() == true) {
                synchronized (workProcess) {
                    try {
                        workProcess.wait(); //ожидание отработки loader/unloader
                    } catch (InterruptedException e) {
                        System.out.println("Transporting Error");
                    }
                }
            }
            try {
                move();
            } catch (InterruptedException e) {
                System.out.println("Transporting Error");
            }
            if("loader".equals(workProcess.getLastWorkerName())){
                //получили тележку от loader - заблокировали loader, разблокировали unloader
                workProcess.setFlagBlockLoader(true);
                workProcess.setFlagBlockUnloader(false);
                System.out.println("truck was moved; pass to unloader");
            }
            workProcess.setFlagBlockTransporter(true);
            if("unloader".equals(workProcess.getLastWorkerName())){
                //получили тележку от unloader - разблокировали loader, заблокировали unloader
                workProcess.setFlagBlockUnloader(false);
                workProcess.setFlagBlockUnloader(true);
                System.out.println("truck was moved; pass to loader");
            }
            workProcess.setLastWorkerName("transporter");
            synchronized (workProcess) {
                workProcess.notifyAll();
            }
        }
    }    

}