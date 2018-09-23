<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title>T-Shirt Games - Cadastrar Produtos</title>
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css">
	<link rel="stylesheet" href="resources/css/style.cadastroproduto.css">

	<script type="text/javascript" src="resources/js/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="resources/js/jquery/jquery.mask.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap/popper.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="resources/js/script.preimagem.js"></script>
	<script type="text/javascript" src="resources/js/script.validacaocampos.js"></script>
	<script type="text/javascript" src="resources/js/script.formatacaodecampos.js"></script>
	<script type="text/javascript" src="resources/js/script.exibirmodal.js"></script>
</head>

<body>
	<c:import url="resources/template/modal.mensagem.jsp"/>
	
	<div class="container">

		<!-- FORMULÁRIO DE CASTRO DO PRODUTO -->
		<form id="form-produto" enctype="multipart/form-data" accept-charset="ISO-8859-1">
		<div class="row">
			<div class="col-md-4 mt-4">
				<div class="card m-2 mb-4">
					<div id="valida_imagem" class="card m-3">
						<img id="img-pre-visualiza" class="card-img-top m-4 mx-auto d-inline-block">
						<p class="text-center" id="msg_imagem" style="display: none;">Insira uma imagem!</p>
					</div>

					<div class="card m-3">
						<div class="custom-file">
							<input name="imagem" id="pre-imagem" onchange="PreviewImage(); RetornoValidaImagem();" class="w-100 custom-file-input" type="file">
							<span id="input-imagem" class="custom-file-label text-truncate"></span>
						</div>
					</div>
				</div>

				<div class="card m-2">
					<div class="form-group m-2">
						<label for="exampleFormControlTextarea1"><h4>Descrição do Produto</h4></label>
						<textarea name="descricao" id="descricao" onfocus="RetornoValidaDescricao()" class="form-control" rows="4" placeholder="Digite a descrição do produto..." required>${descricao}</textarea>
					</div>
				</div>
			</div>

			<div class="card col-md-8 mt-4">
				<div class="row mt-3">
					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Produto</h5></label>
						<input formenctype="application/x-www-form-urlencoded" name="produto" id="produto" onfocus="RetornoValidaProduto()" type="text" class="form-control" placeholder="Nome do Produto" required>
					</div>
					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Modelo</h5></label>
						<input name="modelo" id="modelo" onfocus="RetornoValidaModelo()" type="text" class="form-control" placeholder="Modelo do Produto" required>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md">
						<label for="exampleFormControlSelect1"><h5>Genero</h5></label>
						<select name="genero" id="genero" onfocus="RetornoValidaGenero()" class="form-control" required>
							<option value="" label="Selecione o Genero">
							<option value="Feminino" label="Feminino">
							<option value="Masculino" label="Masculino">
						</select>
					</div>

					<div class="form-group col-md">
						<label for="exampleFormControlSelect1"><h5>Categoria</h5></label>
						<select name="categoria" id="categoria" onfocus="RetornoValidaCategoria()" class="form-control" required>
							<option value="" label="Selecione uma Categoria">
							<option value="Series" label="Series">
							<option value="Games" label="Games">
							<option value="Animes" label="Animes">
							<option value="Filmes" label="Filmes">
						</select>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Tamanho</h5></label>
						<input name="tamanho" id="tamanho" onfocus="RetornoValidaTamanho()" type="text" class="form-control" placeholder="Tamanho do Produto" required>
					</div>

					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Cor</h5></label>
						<input name="cor" id="cor" onfocus="RetornoValidaCor()" type="text" class="form-control" placeholder="Cor do Produto" required>
					</div>

					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Quantidade</h5></label>
						<input name="quantidade" id="quantidade" onfocus="RetornoValidaQuantidade()" type="number" class="form-control" placeholder="00" min="1" required>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Valor Custo</h5></label>
						<input name="valor_custo" id="valor_custo" onfocus="RetornoValidaValorCusto()" onkeypress="return BloquearCaracteres(event)" type="text" class="form-control" placeholder="R$ 00.00" pattern="[0-9]{,2}" title="Formato deve ser 0.00" required>
					</div>

					<div class="form-group col-md">
						<label for="formGroupExampleInput"><h5>Valor Venda</h5></label>
						<input name="valor_venda" id="valor_venda" onfocus="RetornoValidaValorVenda()" onkeypress="return BloquearCaracteres(event)" type="text" class="form-control" placeholder="R$ 00.00" pattern="[0-9]{,2}" title="Formato deve ser 0.00" required>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-4 mt-3 mb-3 center">
						<button formaction="AlterarProduto" formmethod="get" class="btn btn-warning" formnovalidate>Alterar Produto ➔</button>
					</div>
					<div class="col-md-8 ml-auto center mb-3 mt-3">
 						<button id="CadastrarProduto" type="submit" class="btn btn-success" formaction="InserirProduto" formmethod="post" name="button" onclick="ValidaCadastroProduto()">Cadastrar</button>
						<a href="index.jsp"><button type="button" class="btn btn-danger">Cancelar</button></a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>

	<c:import url="resources/template/footer.jsp" />
</body>
</html>
