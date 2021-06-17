package homework;

public class WorkProcess {

    private boolean flagBlockLoader;
    private boolean flagBlockTransporter;
    private boolean flagBlockUnloader;
    private String lastWorkerName;
    
    public WorkProcess (boolean flagBlockLoader, boolean flagBlockTransporter, boolean flagBlockUnloader,
                        String lastWorkerName){
        this.flagBlockLoader = flagBlockLoader;
        this.flagBlockTransporter = flagBlockTransporter;
        this.flagBlockUnloader = flagBlockUnloader;
        this.lastWorkerName = lastWorkerName;
    }
    
    public boolean getFlagBlockLoader(){
        return flagBlockLoader;
    }

    public void setFlagBlockLoader(boolean flagBlockLoader){
        this.flagBlockLoader = flagBlockLoader;
    }

    public boolean getFlagBlockTransporter(){
        return flagBlockTransporter;
    }

    public void setFlagBlockTransporter(boolean flagBlockTransporter){
        this.flagBlockTransporter = flagBlockTransporter;
    }

    public boolean getFlagBlockUnloader(){
        return flagBlockUnloader;
    }

    public void setFlagBlockUnloader(boolean flagBlockUnloader){
        this.flagBlockUnloader = flagBlockUnloader;
    }

    public String getLastWorkerName(){
        return lastWorkerName;
    }

    public void setLastWorkerName(String lastWorkerName){
        this.lastWorkerName = lastWorkerName;
    }
    
}
