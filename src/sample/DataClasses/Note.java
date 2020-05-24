package sample.DataClasses;

import sample.AudioPlayer;

public class Note {
    // variable to store the full name of a Note. e.g Db1
    private String name;
    // audio file of the Note's sound
    private String audioFilePath;

    public static void main(String[] args) {
        new Note("B5").play();
 }

    //default constructor
    //Assigns Note name to "C1"
    public Note(){
        this.name = "C1";
        this.audioFilePath = this.getSoundFilePath(this.name);
    }

    public Note(String name){
        if(!isValidNote(name)){
            System.out.println("Invalid Note Name!!!");
            System.exit(0);
        }
        this.name = name;
        this.audioFilePath = getSoundFilePath(name);
    }
    //may not be needed
    public Note(Note otherNote){
        this.name = otherNote.name;
        this.audioFilePath = getSoundFilePath(otherNote.getName());
    }

    private boolean isValidNote(String name){
        if(!Utilities.NOTE_NAMES.contains(name)) return false;
        return true;
    }

    public String getName() {
        return this.name;
    }

    public String toString(){
        return name;
    }

    public Note sharp(int n){
        int index = Utilities.NOTE_NAMES.indexOf(this.name);
        if(index + n > 87) return null;
        Note result = new Note(Utilities.NOTE_NAMES.get(index + n));
        return result;
    }
    public Note flat(int n){
        int index = Utilities.NOTE_NAMES.indexOf(this.name);
        if(index - n < 0) return null;
        Note result = new Note(Utilities.NOTE_NAMES.get(index - n));
        return result;
    }

    public void play(){
        System.out.println("Playing " + this.name);
        String path = this.audioFilePath;
        new Thread(new Runnable() {
            @Override
            public void run() {
                new AudioPlayer().play(path);
            }
        }).start();
    }

    // retrieve the filePath of the Note's corresponding sound
    private String getSoundFilePath(String noteName){
        // to do
        return "src/Sample sounds/Piano.mf." + noteName + ".aiff";
    }

    public String noteQuality(){
        return this.getName().substring(0,this.getName().length() - 1);
    }
}
