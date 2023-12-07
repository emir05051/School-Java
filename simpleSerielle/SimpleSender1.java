import java.util.*;
/**
* SimpleSender1
* 
* implementiert ein einfaches Demoprogamm zur prinziellen 
* Funktionsueberprüfung der Hardware und Software.
*
* SimpleEmpfaenger1 VOR dem SimpleSender1 starten!!!
*
* @version 3.0 vom 13.11.2023
* @author J. Mang
*/
public class SimpleSender1{
  
  private Serial com;
  private boolean istGeoeffnet;
  
  public SimpleSender1(int portnr){                     
    
    try{
      
      this.com = new Serial("COM" + portnr, 9600, 8, 1, 0);       
      
      this.istGeoeffnet = this.com.open();
      
      System.out.println();
      System.out.println("Port konnte geoeffnet werden :-)");
      System.out.println();
      System.out.println("Status : " + this.istGeoeffnet);
      System.out.println();
    }
    catch(Exception e){
      System.out.println("Port konnte nicht geoeffnet werden.");
      System.out.println();
      System.out.println("Status : " + this.istGeoeffnet);
      System.out.println();
    }       
    
  }
  
  public boolean getIstGeoeffnet(){
    return this.istGeoeffnet; 
  }
  
  public static void main(String[] args) {
    
    int portnummer = 5;  // Portnummer des aktuellen IT-Systems
                         // Kann im Geraetemanager nachgeschaut werden
    
    System.out.println("SimpleSender 1 auf COM-Port " + portnummer + " gestartet.\n");
    
    try{
      
      SimpleSender1 s = new SimpleSender1(portnummer);
      
      if(s.getIstGeoeffnet() == true){
      
        String text = "HEMS BG Q3 PI - Datenkommunikation ";
        
        for (int i = 0; i < 5; i++) {
         
          s.com.write(text + i + "\n");
          
          Thread.sleep(1000); // warte eine Sekunde
          System.out.println("Geschrieben: " + text + i);          
        }
        
        s.com.write("Die Sendefunktion wurde beendet...\n");
        
        System.out.println("\nGeschrieben: Die Sendefunktion wurde beendet...\n");
        
        System.out.println("\nCOM-Port wird geschlossen...\n");
        s.com.close();
        System.out.println("ENDE\n\n");
      }
    
      else{
        System.out.println();
        System.out.println("Fehler: Überprüfen Sie die SSt-Verbindungen!");
        System.out.println();
        System.out.println("Das Programm wird beendet...");
        System.out.println();
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }    
  }
}