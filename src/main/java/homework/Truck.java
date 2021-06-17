package homework;

public class Truck {

    private int maxVolume;
    private int currentVolume;

    public Truck (int maxVolume, int currentVolume){
        this.maxVolume = maxVolume;
        this.currentVolume = currentVolume;
    }
    
    public synchronized int getMaxVolume() {
        return this.maxVolume;
    }

    public synchronized int getCurrentVolume() {
        return this.currentVolume;
    }

    public synchronized void load(int volume) {
        this.currentVolume += volume;
    }    

    public synchronized void unload(int volume) {
        this.currentVolume -= volume;
    }

}