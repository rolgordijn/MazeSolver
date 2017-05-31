Het doolhof bestaat uit een 2D Array van MazeParts. 

Aan de rand hiervan wordt een entry gezocht. Naast dit veld wordt ook een vrij veld gezocht. 
In eerste proberen we naar links te gaan. Is dit veld reeds gepasseerd of is dit een muur van het doolhof dan proberen we naar rechts. 
Is nog steeds geen vrij veld gezocht dan proberen we resp. boven en onder. We gaan ervan uit dat het gevonden pad het juiste is. 

Elke keer een stap vooruit wordt gezet in het doolhof wordt er gekeken of het huidige veld geen splitsing is. Zoja, dan worden de coordinaten van dit veld bijgehouden op een stack. 
Lopen we vast in het doolhof, dan keren we terug naar het laatst bewaarde kruispunt en proberen een andere richting uit.

Het proces gaat verder tot de uitgang gevonden is.

Mijn ambitie was om ook nog de gevolgde wegen te invalideren zodanig deze in een andere kleur worden getoond. In eerste instantie zouden de gevolgde paden in het doolhof gemarkeerd worden als "Unknown". 
Nadien zou dan de route gereconstrueerd worden en deze paden te markeren als "Correctpath". Hiervoor zou ik een tree bijhouden waarbij elke node een kruispunt is, 
zodat achteraf eenvoudig de route kan opnieuw worden afgelegd, maar niet meer voldoende tijd gehad om dit aan de praat te krijgen.  