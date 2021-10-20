**MobilPog** 

A MobilPog egy Space Invaders Clone android alkalmazásként megvalósítva 
azzal a csavarral hogy a telefonunk döntésével irányíthatjuk űrhajónk.

A MobilPog a Mobilprogramozás tantárgyra készített komplex beadandónak készült.
A projekthez tartozó bemutató videó megtalálható az alábbi linken (és a repon is):
https://drive.google.com/file/d/18WHzdLpv4srjLZDs0fp5jvggnZSyaAiM/view?usp=drivesdk


**Főbb funkciók:**

**Játékmenet**:
  A játékmenet az eredetihez hasonlóan zajlik űrlény csészeajakat kell kilőnünk rombolható fedezékek mögé bújva.
  Ahogy fogynak az ellenségek úgy egyre gyorsabban közelednek. Az égen időnként elhalad egy csészeaj, amit kiemelten magas pontokért leölhetünk.
  A játék addig megy amíg az összes ellenséget ki nem lőttük nem fogy az életünk vagy az elenség "landol" eléri a barikádot.
  
Leader board 
A játékok végén a felhasználó nevét megadva elmentheti a Pontjait és idejét egy helyi adatbázisba.
  
**Beállítások:** 
 - Változtatható nehézség
	 - A nehézségek 6 dolgon változtatnak:
		 - A játékos szempontjából:
			 - Az ellenség lelövésért kapott pontok szorzója
			 - Lövés gyorsaság
		 - Az ellenség szempontjából:
			 - Egyszerre hányan tudnak lőni
			 -  Lövés gyorsaság
	 - Három fajta nehézség választható (a változtatásaik fenti sorrend szerint):
		 - Easy/Könnyű :1x,5,5,1mp,3,1mp
         - Medium/Közép:3x,3,4,2mp,4,1/2mp
         - Hard:5x,1,3,3mp,5,1/4mp
            
 - A fő menübe menő zenét ki és bekapcsolhatjuk, valamint a játék
   hangjait szabályozhatjuk. 

Minden beállítás egy json fájlba mentődig

