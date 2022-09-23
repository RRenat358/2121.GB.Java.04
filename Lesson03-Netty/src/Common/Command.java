package Common;


import java.io.File;

public class Command {

    private String command;
    private File file;
    private byte[] data;

    public Command() {
    }

    public Command(String command, File file, byte[] data) {
        this.command = command;
        this.file = file;
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }










}
