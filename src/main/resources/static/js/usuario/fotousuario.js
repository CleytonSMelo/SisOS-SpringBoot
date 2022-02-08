$(document).ready(function () {
	buscarfoto();
});
	function buscarfoto(){
        var url ='/SisOS/Home/Usuario/img';
        console.log(url);
            $.ajax( {           	
                type : 'GET',               
                url : url 
            }).done(function (data) {
                 //console.log(data);
                if (data == "") {
                	$("#fotoUser").attr('src',"/img/undraw_profile.svg");               	
                } else {               	
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
            });
    }