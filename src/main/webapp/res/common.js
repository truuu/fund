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

	$("#sponsorNo").change(function(){
		$("#commitmentSearch").submit();
	});
	
	$("[class=money]").mask("000,000,000,000,000,000",{reverse: true});

	$("form").submit(function() {
		$("[class=money]").unmask();
	});

});

