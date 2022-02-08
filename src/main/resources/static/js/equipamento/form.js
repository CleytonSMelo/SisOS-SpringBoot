 $(document).ready(function() {
      $(".alert").fadeTo(1, 1).removeClass('hidden');
    	   window.setTimeout(function() {
    	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
    	        $(".alert").addClass('hidden');
    	    });
      }, 1000); 

    
      $("#btn-gerarns").click(function() {
  	       event.preventDefault(); 
           var ns = 'PC' + moment().year() + moment().unix() + 'RN';

           $("#inputNumeroSerie").val(ns);
      });

      $("#btn-gerartombo").click(function() {
     	   event.preventDefault();
           var tombo = moment().year() + moment().unix();

           $("#inputTombo").val(tombo);
      });    
              
  });
 
  var somenteNumeros = function(event) {
	  return ((event.charCode >= 48 && event.charCode <= 57))
  }