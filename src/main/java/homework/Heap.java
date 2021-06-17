package homework;

public class Heap {
    
    private int initVolume;
    private int currentVolume;

    public Heap (int initVolume){
        this.initVolume = initVolume;
        this.currentVolume = this.initVolume;
    }
    
    public synchronized int getInitVolume() {
        return this.initVolume;
    }

    public synchronized int getCurrentVolume() {
        return this.currentVolume;
    }

    public synchronized void decrease(int volume) {
        this.currentVolume -= volume;
    }
    
}