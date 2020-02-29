<%@ include file="../common/header.jspf"%> <%@ include
file="../common/navigation.jspf"%>
<% String id = request.getParameter("k_id");%>
<div class="container">

	<form:form method="POST" modelAttribute="vertragForm" class="form-signin" enctype="multipart/form-data"
		name="vertragForm" >
		<h2 class="form-signin-heading" style="font-size: 20pt">
		<spring:message code="label.newContract"/></h2>
		<input type="hidden" value="${vertragForm.id}">
		
		<div>
			<c:set var="kid" value="<%= id %>"/>
			<select id="kunde" name="kunde" class="form-control" >
				
				
					<option value="${vertragForm.kunde.kundeId}" >
					${vertragForm.kunde.name} / ${vertragForm.kunde.land}</option>
				
				<select>

					<div class="error" id="kundeError"></div>
					
		</div>
		<br>
		
		
		<div>
			
			<select path="ort" name="ort" class="form-control" >
			
				<c:forEach items="${listLocation}" var="ort">
				
					<c:forEach items="${listVorOB}" var="vorOB">
					
						<c:choose>
							<c:when test="${vorOB.kunde.kundeId==vertragForm.kunde.kundeId && vorOB.ort.ortId==ort.ortId}">
								
														
								<option value="${ort.ortId}" ${ort.ortId == vertragForm.ort.ortId ? 'selected' :''} >${ort.ortname} / ${ort.land}</option>
							</c:when>
							
						</c:choose>
						
					</c:forEach>
				
				
				</c:forEach>
				
			</select>
			


		</div>
		<br>
		<div>
            	
            	<textarea id="inhalt" name="inhalt" rows="15" style="width: 600px" class="form-control" placeholder="<spring:message code="label.content"/>">${vertragForm.inhalt}</textarea>
           		
            </div>
            <br>
            <div>
            
            <c:forEach items="${listDatei}" var="files">
            
            
            <a  type="button" class="btn btn-success" href="../downloadFile/${files.fileName}"><spring:message code="label.buttonDownload"/></a>&nbsp&nbsp
            <a  type="button" class="btn btn-warning" href="../delete-file/${vertragForm.id}/${files.id}"><spring:message code="label.buttonDelete"/></a>
            &nbsp&nbsp&nbsp&nbsp
            <a href="../downloadFile/${files.fileName}" ><spring:message code="label.file"/>-${files.fileName}</a>
       	       	<br><br>
            </c:forEach>
            </div>
            <input type="file" name="file" id="file1" path="file1" class="form-control" onchange="fileValidation1()"><br>
            
            <div id="mes_size1"  ><p id="size1" class="alert-success" ></div>
            
            <input type="file" name="file" id="file2" class="form-control" onchange="fileValidation2()"><br>
            
            <div id="mes_size2"  ><p id="size2" class="alert-success" ></div>
            <input type="file" name="file" id="file3" class="form-control" onchange="fileValidation3()"><br>
            <div id="mes_size3"  ><p id="size3" class="alert-success" ></div>
            <form:errors path="file1" class="error"></form:errors>
            
            
			











		<input class="btn btn-lg btn-primary btn-block" type="submit"
			value="<spring:message code="label.buttonUpdate"/>">
	</form:form>

</div>
<script>
window.onload = function() {
	
	document.getElementById("mes_size1").style.display = "none";
	document.getElementById("mes_size2").style.display = "none";
	document.getElementById("mes_size3").style.display = "none";
	document.getElementById("kundeErrorDiv").style.display = "none";

		
	}

	function formKontrolle() {
		 
		 var selectedKunde = document.getElementById("kunde");
		 
		 if(selectedKunde.value == "null") {
			 
			 document.getElementById("kundeErrorDiv").style.display = "block";
			 return false;
		 } else {
			 
			 return true;
		 }
		 
	}
		
		
		

		
		
		//FileList
		
		function listFile() {
			
			var files = document.getElementById("listFileNames").value;
			
			var list = files.split(",");
			document.getElementById("d").innerHTML = list[2];
		}
		
		
		//FileValidation1
		
		function fileValidation1() {
			
			const file = document.getElementById("file1");
			var size1 = document.getElementById("size1");
			var mes_size1 = document.getElementById("mes_size1");
			if(file.files.length > 0) {
				
				const fsize = file.files.item(0).size;
				const fi = Math.round((fsize / 1024));
				if(fi >= 10240) {
					
					mes_size1.style.display = "block";
					size1.classList.remove("alert-success");
					size1.classList.add("alert-danger");
					size1.innerHTML = "Dateigröße : <b>" + fi + "</b> KB - Datei ist zu groß, bitte wählen Sie eine Datei mit weniger als 10 MB" ;
					file.value="";
					
				} else {
					
					mes_size1.style.display = "block";
					size1.classList.remove("alert-danger");
					size1.classList.add("alert-success");
					
					size1.innerHTML = "Dateigröße : <b>" + fi + "</b> KB - Datei ist OK." ;
				}
			}
			
		}
		
	//FileValidation1
		
		function fileValidation2() {
			
			const file = document.getElementById("file2");
			var size1 = document.getElementById("size2");
			var mes_size1 = document.getElementById("mes_size2");
			if(file.files.length > 0) {
				
				const fsize = file.files.item(0).size;
				const fi = Math.round((fsize / 1024));
				if(fi >= 10240) {
					
					mes_size1.style.display = "block";
					size1.classList.remove("alert-success");
					size1.classList.add("alert-danger");
					size1.innerHTML = "<b>" + fi + "</b> KB : Datei zu groß, bitte wählen Sie eine Datei mit weniger als 10 MB" ;
					size1.innerHTML = "Dateigröße : <b>" + fi + "</b> KB - Datei ist zu groß, bitte wählen Sie eine Datei mit weniger als 10 MB" ;
					file.value="";
				} else {
					
					mes_size1.style.display = "block";
					size1.classList.remove("alert-danger");
					size1.classList.add("alert-success");
					
					size1.innerHTML = "Dateigröße : <b>" + fi + "</b> KB - Datei ist OK." ;
				}
			}
			
		}
		
	//FileValidation1
		
		function fileValidation3() {
			
			const file = document.getElementById("file3");
			var size1 = document.getElementById("size3");
			var mes_size1 = document.getElementById("mes_size3");
			if(file.files.length > 0) {
				
				const fsize = file.files.item(0).size;
				const fi = Math.round((fsize / 1024));
				if(fi >= 10240) {
					
					mes_size1.style.display = "block";
					size1.classList.remove("alert-success");
					size1.classList.add("alert-danger");
					size1.innerHTML = "<b>" + fi + "</b> KB : Datei zu groß, bitte wählen Sie eine Datei mit weniger als 10 MB" ;
					size1.innerHTML = "Dateigröße : <b>" + fi + "</b> KB - Datei ist zu groß, bitte wählen Sie eine Datei mit weniger als 10 MB" ;
					file.value="";
				} else {
					
					mes_size1.style.display = "block";
					size1.classList.remove("alert-danger");
					size1.classList.add("alert-success");
					
					size1.innerHTML = "Dateigröße : <b>" + fi + "</b> KB - Datei ist OK." ;
				}
			}
			
		}

	var kunde = document.getElementById("kunde");
	
	kunde.addEventListener ("change", function() {
		
		window.location.replace('?k_id=' + this.value);
	});
</script>

<%@ include file="../common/footer.jspf"%>
