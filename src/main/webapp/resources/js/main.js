function deleteDatensatz() {
	var result = confirm("Möchten Sie Datensatz löschen?");
	
	if(result) return true;
	else return false;
	
	
	
}
    
    
function sayHello() {
	
	alert("Herzlich Wilkommen!");
}

function testDialog() {
	
	var result = prompt("Möchten Sie wirklich abmelden?");
	
	if(result) {
		
		alert("Sie haben erfolgreich ausgeloggt.");
	} else {
		
		alert("Bye!");
	}
}

function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}

function vorOBFormKontrolle() {
	var kunde = document.vorOBForm.kunde.value;
	var ort = document.vorOBForm.ort.value;
	var user = document.vorOBForm.user.value;
	
	var kundeError = true;
	var ortError = true;
	var userError = true;
	
	
	if(kunde == 0) {
		printError("kundeError","Wählen Sie einen Kunde aus.");
		} else {
		printError("kundeError","");
		kundeError=false;
		}
	
	if(ort == 0) {
		printError("ortError","Wählen Sie einen Ort aus.");
		} else {
		printError("ortError","");
		ortError=false;
		}
	
	if(user == 0) {
		printError("userError","Wählen Sie einen Mitarbeiter aus.");
		} else {
		printError("userError","");
		userError=false;
		}
	
	if(kundeError==true || ortError==true || userError==true) return false;
	else return true;
}

function vonbisKontrolle() {
	
	var von = document.vonbisForm.von.value;
	var bis = document.vonbisForm.bis.value;
	
	if( von == "" || bis == "" || von > bis) {
		printError("vonbisError", "Geben Sie ein gültiges Zeitraum.");
		return false;
	} else {return true;}
}




function validateForm() {
	
	var name = document.taetigkeitForm.name.value;
	
	var nameError = true;
	
	 // Validate name
    if(name == "") {
        printError("nameError", "Geben Sie ein Tätigkeitsname ein.");
       
    } else {
        
            printError("nameError", "");
            nameError = false;
        }
    
    
    
	 
	 if(nameError == true) {return false;}
}

function deleteTaetigkeit() {
	var result = confirm("Möchten Sie Tätigkeit löschen?");
	
	if(result) return true;
	else return false;
	
	
	
}

function deleteNutzen() {
	var result = confirm("Möchten Sie Datensatz löschen?");
	
	if(result) return true;
	else return false;
	
	
	
}

function validateNutzenForm() {
	
	var datum = document.nutzenForm.datum.value;
	var kunde = document.nutzenForm.kunde.value;
	var ort = document.nutzenForm.ort.value;
	var taetigkeit = document.nutzenForm.taetigkeit.value;
	
	var stunde = document.nutzenForm.stunde.value;
	var pattern = /^\d+.?\d*$/;
	var stundeMatcher = stunde.match(pattern);
	
	var datumError=true;
	var kundeError=true;
	var ortError=true;
	var taetigkeitError=true;
	var stundeError=true;
	
	if(datum=="") { printError("datumError","Geben Sie ein gültiges Datum ein.");}
	else {
		printError("datumError","");
		datumError=false;}
	
	if(kunde==0) {printError("kundeError","Wählen Sie einen Kunde aus.");}
	else {
		printError("kundeError","");
		kundeError=false;}
	
	if(stunde==0 || !stundeMatcher) {printError("stundeError","Die Stunde ist ungültig.");}
	else {
		printError("stundeError","");
		stundeError=false;}
	
	if(ort==0) {printError("ortError","Wählen Sie einen Ort aus.");}
	else {
		printError("ortError","");
		ortError=false;}
	
	if(taetigkeit==0) {printError("taetigkeitError","Wählen Sie eine Tätigkeit aus.");}
	else {
		printError("taetigkeitError","");
		taetigkeitError=false;}
	
	
	
	
	if(datumError==true || kundeError==true || stundeError==true || ortError==true || taetigkeitError==true) {return false;} else  {return true;}
	
	
}
