$(document).ready(function() {
	    // traduz o texto do datatable bootstrap para PT-BR
        $('#example').DataTable( {
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Portuguese-Brasil.json"
            }
        } );
        
        
        //ocuta menssagem apos um tempo
        $(".alert").fadeTo(1, 1).removeClass('hidden');
  	     window.setTimeout(function() {
  	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
  	        $(".alert").addClass('hidden');
  	    });
  	    }, 1000); 
} );

$('.table #remover').click( function (event) {
    event.preventDefault();
    var href = $(this).attr('href');
    $('#exampleModal #btnRemover').attr('href', href);
    $('#exampleModal').modal();
});