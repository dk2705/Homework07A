package homework;

import java.util.Date;
import java.text.SimpleDateFormat;
        
public class Unloader implements Runnable {

    private String name;
    private int speed;
    private int timeMs;
    private Truck truck;
    private WorkProcess workProcess;
    
    public Unloader(String name, int speed, int timeMs, Truck truck, WorkProcess workProcess) {
        this.name = name;
        this.speed = speed;
        this.timeMs = timeMs;
        this.truck = truck;
        this.workProcess = workProcess;
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();
    }

    public synchronized void unloadMoney(int volume) throws InterruptedException {
        System.out.println("Unloading started: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + ": " + volume + " units will be unloaded");
        Thread.sleep(timeMs);
        truck.unload(volume);
        System.out.println("Unloading finished: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + ": " + volume + " units unloaded");
    }
    
    @Override
    public void run() {
        while(true){ //разгрузчик ничего не знает о состоянии кучи, поэтому цикл бесконечный (завершится как демон)
            if (workProcess.getFlagBlockUnloader() == true){
                synchronized (workProcess) {
                    try {
                        workProcess.wait(); //ожидание отработки transporter от себя
                    } catch (InterruptedException e) {
                        System.out.println("Unloading Error");
                    }
                }
            }
            if (workProcess.getFlagBlockUnloader() == true){
                synchronized (workProcess) {
                    try {
                        workProcess.wait(); //ожидание отработки loader
                    } catch (InterruptedException e) {
                        System.out.println("Unloading Error");
                    }
                }
            }
            if (workProcess.getFlagBlockUnloader() == true){
                synchronized (workProcess) {
                    try {
                        workProcess.wait(); //ожидание отработки transporter к себе - после нее можно выполнить свою работу
                    } catch (InterruptedException e) {
                        System.out.println("Unloading Error");
                    }
                }
            }
            while (truck.getCurrentVolume() > 0) {
                try {
                    unloadMoney((truck.getCurrentVolume() >= this.speed) ? this.speed : truck.getCurrentVolume());
                } catch (InterruptedException e) {
                    System.out.println("Unloading Error");
                }
            }
            workProcess.setFlagBlockLoader(true);
            workProcess.setFlagBlockTransporter(false);
            workProcess.setFlagBlockUnloader(true);
            workProcess.setLastWorkerName("unloader");
            synchronized (workProcess) {
                workProcess.notifyAll();
            }
            System.out.println("truck was unloaded; pass to transporter");
        }
    }
    
}
