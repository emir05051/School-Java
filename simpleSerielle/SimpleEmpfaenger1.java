/**
* SimpleEmpfaenger1
* 
* implementiert ein einfaches Demoprogamm zur prinziellen 
* Funktionsueberprüfung der Hardware und Software.
*
* SimpleEmpfaenger1 ist VOR dem SimpleSender1 starten!!!
*
* @version 3.0 vom 13.11.2023
* @author J. Mang
*/
public class SimpleEmpfaenger1{
  
  private Serial com;
  private boolean istGeoeffnet;
  
  public SimpleEmpfaenger1(int portnr){                     
    
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
    
    int portnummer = 6;  // Portnummer des aktuellen IT-Systems
                         // Kann im Geraetemanager nachgeschaut werden  
    
    System.out.println("SimpleEmpfaenger 1 auf COM-Port " + portnummer + " gestartet.\n");
    
    try{
      
      SimpleEmpfaenger1 em = new SimpleEmpfaenger1(portnummer);
      
      if(em.getIstGeoeffnet() == true){
        System.out.println("Wenn der Empfaenger gestartet wurde...");
        System.out.println();
        System.out.println("... dann kann der Sender JETZT Textnachrichten schicken.");
        System.out.println();
        
        boolean status = true;
        
        while(status == true){
          
          
          while(em.com.dataAvailable() == 0){
            // warte
          }
          // Empfangen
          while(em.com.dataAvailable() > 0){
            
            String empfang = em.com.readLine();
            
            if (empfang.equals("Die Sendefunktion wurde beendet...")){
              status = false;
            }
            System.out.println("Empfangen: " + empfang);
          }      
        }
        
        System.out.println("\nDie Empfaengerfunktion wurde beendet...\n");
        
        System.out.println("\nCOM-Port wird geschlossen...\n");
        
        em.com.close();
        
        System.out.println("ENDE\n\n");
      }
      else {
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