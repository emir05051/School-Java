  public void schreibeInteger2(int zahl){
  
    System.out.println();
    System.out.println("*****************************************");
    System.out.println();
    System.out.println("Aktuelle Zahl zum Schreiben: " + zahl);
    System.out.println();  
    
    String z = "" + zahl;
    
    // Weg: Schreibe jede Ziffer nacheinander auf die serielle SSt
    
    for ( int i = 0; i < z.length(); i++){
      
      try{
        
        this.com.write(z.charAt(i));
      }
      catch(Exception e){
        System.out.println("Fehler beim Schreiben auf Port: " + e);
        e.printStackTrace();
      }      
    }   
  }




public int leseInteger2(){
  
    int zahl = 0;
    int empfang = -1;
    
    try{  
      
      while (com.dataAvailable() <= 0){ 
        // warte auf Dateneingang
        Thread.sleep(10);
      } 
      
      String zahlString ="";
      
      while (com.dataAvailable() > 0){ 
        
        empfang = com.read(); 
        if(empfang != 0){
          zahlString = zahlString + (char)empfang;
        }        
        
      } 
 
      zahl = Integer.parseInt(zahlString);
      
      System.out.println("Zahl in Methode: " + zahl);
      
    }
    catch(Exception e){
    }     

    return zahl;
