import java.io.*;

public class config {


    public config(){

    }

    public void saveConfig(Panel gp) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("JavaAdventure/config.txt"));
            if(gp.fullScreen){
                bw.write("On");
            } else{
                bw.write("Off");
            }
            bw.newLine();
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("JavaAdventure/config.txt"));
            String s = br.readLine();

            // FULL SCREEN
            if(s.equals("On")){
                Main.fs = true;
            } else if(s.equals("Off")){
                Main.fs = false;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
