// EXIBIR LISTA DE PRODUTOS
function ListaProdutos() {
	$("#ListaProdutos").modal();
}

// EXIBIR MENSAGEM DE PRODUTO CADASTRADO
$(document).ready(function(){
	
	if($("#mensagem").val() != ""){
		$("#produto_cadastrado").modal();
	}
	
});