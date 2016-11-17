

$(function() {
    $("tr[data-url]").click(function() {
        location.href = $(this).attr("data-url");
    });

    $("div.pagination a").click(function() {
        $("input[name=pg]").val($(this).attr("data-page"));
        $("form").submit();
    });
 
    
    $("[data-auto-submit=true]").change(function() {
        $(this).parents("form").submit();
    });
});

$(function() {                          
	$("[class=money]").mask("000,000,000,000,000,000",{reverse: true});

	$("form").submit(function() {
		$("[class=money]").unmask();
	});
})

$(function(){
	
	$("#datepicker1").datepicker({
		format : 'yyyy-mm-dd'		
	});
	$("#datepicker2").datepicker({
		format : 'yyyy-mm-dd'		
	});
	$("#datepicker3").datepicker({
		format : 'yyyy-mm-dd'		
	});
})