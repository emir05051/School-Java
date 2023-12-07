Theorie zum Thema: 
Threads:

- gemeinsame Daten und Threads nutzen
- Schutzkonzepte gegen inkonstiente Daten

Theorie zu Threads, gemeinsame Daten und Schutzkonzepte gegen inkonsistente Daten:

1. Threads und Gemeinsame Daten:
Threads sind leichtgewichtige, ausführbare Einheiten innerhalb eines Prozesses, die gemeinsam mit anderen Threads denselben Adressraum und Ressourcen teilen. Dies ermöglicht es Threads, Informationen einfach miteinander zu teilen. Allerdings bringt dies auch Herausforderungen mit sich, insbesondere im Umgang mit gemeinsam genutzten Daten:
Race Conditions: Wenn mehrere Threads gleichzeitig auf gemeinsame Daten zugreifen und mindestens einer davon schreibend, kann es zu Wettlaufbedingungen (Race Conditions) kommen. Dies führt zu unvorhersehbaren Ergebnissen, da die Reihenfolge der Thread-Ausführung nicht vorhersehbar ist.
Dateninkonsistenz: Wenn ein Thread Daten ändert, während ein anderer darauf zugreift, kann es zu inkonsistenten Datenzuständen kommen. Das kann zu Fehlfunktionen führen, wenn Threads auf unvorhersehbare oder ungültige Daten zugreifen.

2. Schutzkonzepte gegen inkonsistente Daten:

Um inkonsistente Daten in einem Mehr-Thread-Umfeld zu verhindern, müssen Schutzmechanismen implementiert werden:
Mutex (Mutex Semaphore): Ein Mutex ist eine Sperre, die sicherstellt, dass nur ein Thread gleichzeitig auf bestimmte Ressourcen zugreifen kann. Dies verhindert Race Conditions und sorgt für die nötige Synchronisation zwischen Threads.
Semaphoren: Semaphoren können verwendet werden, um den Zugriff auf eine bestimmte Anzahl von Ressourcen zu steuern. Dies ermöglicht eine flexiblere Steuerung des Zugriffs auf gemeinsam genutzte Daten.
Kritische Abschnitte: Durch das Einführen von kritischen Abschnitten im Code, die von einem Thread exklusiv ausgeführt werden können, wird der Zugriff auf gemeinsame Daten koordiniert.
Atomic Operations: Atomare Operationen sind nicht teilbare Operationen, die als Ganzes ausgeführt werden. Das bedeutet, dass keine anderen Threads in der Mitte einer atomaren Operation eingreifen können, was Race Conditions verhindert.
Thread-sichere Datenstrukturen: Verwendung von Datenstrukturen, die für den gleichzeitigen Zugriff durch mehrere Threads ausgelegt sind, wie z.B. thread-sichere Listen oder Queues.
Lock-Free und Wait-Free Algorithmen: Fortgeschrittene Techniken, die darauf abzielen, die Abhängigkeit von Sperren zu reduzieren oder zu eliminieren, um die Effizienz in stark parallelisierten Systemen zu verbessern.

Theorie zum Thema: 
Client/Server-Systeme: Multi-threaded System

- Was ist Client, Server, Socket, Port, Host
- Was ist Multi-threaded System
- How to make single-threaded system to a multi-threaded system

Theorie zu Client/Server-Systemen und Multi-threaded Systemen:

1. Begriffserklärungen:
Client: Ein Client ist ein Programm oder eine Anwendung, die Dienste oder Ressourcen von einem Server anfordert. Ein Client kann auf einem entfernten oder lokalen Rechner laufen und stellt Anfragen an den Server.
Server: Ein Server ist ein Programm oder eine Maschine, die Dienste oder Ressourcen bereitstellt und auf Anfragen von Clients antwortet. Der Server verarbeitet Anfragen und stellt die angeforderten Informationen oder Dienste bereit.
Socket: Ein Socket ist eine Endpunkt-Instanz in einem Computernetzwerk, die für die Kommunikation zwischen einem Client und einem Server verwendet wird. Sockets ermöglichen die Datenübertragung zwischen verschiedenen Geräten über ein Netzwerk.
Port: Ein Port ist eine Nummer, die einer bestimmten Anwendung auf einem Rechner zugeordnet ist. Ports dienen dazu, eingehende Datenpakete der richtigen Anwendung oder Dienstleistung zuzuweisen.
Host: Ein Host ist ein Rechner oder ein Gerät, das mit einem Netzwerk verbunden ist und Dienste bereitstellen kann. Hosts können Clients oder Server sein, abhängig von ihrer Funktion in einem bestimmten Kontext.

2. Multi-threaded System:
Ein Multi-threaded System ist ein System, das die Verwendung von mehreren Threads innerhalb eines Prozesses ermöglicht. In einem Client/Server-System bedeutet dies, dass der Server mehrere Threads gleichzeitig ausführen kann, um gleichzeitige Anfragen von verschiedenen Clients zu bearbeiten. Das verbessert die Leistung und Skalierbarkeit des Systems.

3. Umwandlung eines Single-threaded Systems in ein Multi-threaded System:
Die Umwandlung eines Single-threaded Systems in ein Multi-threaded System erfordert sorgfältige Planung und Implementierung. Hier sind einige Schritte, um dies zu erreichen:
Identifizierung von Aufgaben: Analysieren Sie das System, um Aufgaben zu identifizieren, die parallel ausgeführt werden können. Dies können separate Anfragen von verschiedenen Clients oder verschiedene Verarbeitungsschritte sein.
Thread-Implementierung: Erstellen Sie für jede identifizierte parallel ausführbare Aufgabe einen eigenen Thread. Dies kann durch die Verwendung von Thread-Bibliotheken oder APIs erfolgen, die von der Programmiersprache oder Plattform unterstützt werden.
Datenzugriff synchronisieren: Implementieren Sie Mechanismen wie Mutexe, Semaphoren oder kritische Abschnitte, um den synchronen Zugriff auf gemeinsam genutzte Daten zu steuern und Dateninkonsistenzen zu vermeiden.
Thread-Pool verwenden: Statt für jede Anfrage einen neuen Thread zu erstellen, kann ein Thread-Pool verwendet werden. Ein Thread-Pool verwaltet eine Gruppe von Threads, die für verschiedene Anfragen wiederverwendet werden können, was die Overhead-Kosten reduziert.
Testen und Optimieren: Überprüfen Sie das Multi-threaded System sorgfältig auf mögliche Race Conditions, Deadlocks oder andere Probleme. Optimieren Sie die Thread-Anzahl und die Ressourcenzuweisung, um eine effiziente Ausführung zu gewährleisten.

Theorie zum Thema: 
Serielle Schnittstelle

- Fragen zur Technik
- Protokolle 
- Abfrage von Sendermodulen mit Threads (Sendermodule sind an den PC angeschlossene Geräte wie z.B. Barcodescanner, Sensormodule (die Daten wie z.B. Temperatur liefern), Steuerungseinheit KFZ, Zentraleinheit eines Parkplatzes, u.v.m.)
- Datenverarbeitung / Einlesen von Daten(strukturen) nach Protokollen

Theorie zur Seriellen Schnittstelle und deren Anwendung in Sendermodulen mit Threads:

1. Serielle Schnittstelle:
Die serielle Schnittstelle (RS-232, RS-485, USB, etc.) ist eine Kommunikationsschnittstelle, die Daten bitweise überträgt. Sie wird häufig für den Datenaustausch zwischen Computern und externen Geräten verwendet. Hier sind einige technische Aspekte:
Übertragungsrate (Baudrate): Die Baudrate gibt an, wie viele Signalwechsel pro Sekunde übertragen werden. Eine höhere Baudrate bedeutet eine schnellere Datenübertragung.
Datenbits, Stopbits, Paritätsbits: Diese Parameter bestimmen die Struktur eines Datenpakets. Datenbits geben die Anzahl der Datenbits an, Stopbits geben an, wie viele Bits verwendet werden, um das Ende eines Datenpakets zu kennzeichnen, und Paritätsbits dienen zur Fehlererkennung.

2. Protokolle:
ASCII-Protokoll: Häufig für den Textdatenaustausch verwendet, wobei jeder Buchstabe durch eine eindeutige Zahl repräsentiert wird.
Modbus: Ein weit verbreitetes Protokoll für die Kommunikation in der Industrieautomation.
UART-Protokoll: Basierend auf der Übertragung von Bits mit Start- und Stopbits über eine serielle Schnittstelle.

3. Abfrage von Sendermodulen mit Threads:
Sendermodule wie Barcodescanner, Sensormodule oder Steuerungseinheiten können in Threads organisiert werden, um gleichzeitig auf verschiedene Geräte oder Sensoren zuzugreifen. Hier sind einige Punkte zu berücksichtigen:
Thread pro Gerät: Jedes Sendermodul kann in einem eigenen Thread arbeiten, um unabhängig voneinander Daten abzufragen, ohne die Ausführung des Hauptprogramms zu blockieren.
Synchronisation: Verwenden Sie Synchronisationsmechanismen wie Mutexe, um sicherzustellen, dass Threads sicher auf gemeinsam genutzte Ressourcen zugreifen können, ohne Dateninkonsistenzen zu verursachen.
Event-Driven Programming: Implementieren Sie eine ereignisgesteuerte Programmierung, bei der Threads auf Ereignisse reagieren und auf Anfragen von Sendermodulen warten.

4. Datenverarbeitung / Einlesen von Daten nach Protokollen:
Serial Port Libraries: Nutzen Sie Bibliotheken oder APIs, die serielle Schnittstellen unterstützen, um das Einlesen von Daten zu erleichtern. Beispiele sind pySerial in Python oder SerialPort in C#.
Protokollanalyse: Implementieren Sie Mechanismen zur Analyse der empfangenen Daten gemäß dem spezifischen Protokoll. Trennen Sie Datenfelder und interpretieren Sie sie entsprechend.
Fehlerbehandlung: Implementieren Sie Mechanismen zur Fehlererkennung und -behebung, um sicherzustellen, dass falsch übertragene Daten erkannt und korrigiert werden können.

