// RECEBE APENAS PONTO E NÚMEROS
function BloquearCaracteres(e){

	if (document.all){
		var evt = event.keyCode;

	} else {
		var evt = e.charCode;
	}

	var valido = '0123456789.';

	// RECEBE O VALOR DIGITADO
	var chr = String.fromCharCode(evt);

	// VERIFICA SE É IGUAL A VARIAVÉL VALIDO
	if (valido.indexOf(chr)>-1){
		return true;
	}
	return false
}