<jsp:include page="/header"/>
<jsp:include page="/menu"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<div class="alert alert-warning" role="alert">
    ${mensagem}
</div>


<form method="POST" action="PessoaJuridicaServlet">
    <h5>Dados Cadastrais Pessoa Jurídica</h5>
    <hr>
    <div class="form-row">
        <div class="form-group col-md-2"> 
            <label for="codigo">Código</label>
            <input type="hidden" name="codigo" value="${pj.codigo}"/>
            <input type="text" class="form-control form-control-sm" id="codigo" disabled  name="codigo" value="${pj.codigo}">
        </div>  
        <div class="form-group col-md-5">
            <label for="nomeFantasia">Nome Fantasia</label>
            <input type="text" class="form-control form-control-sm" id="inputEmail4"  name="nomeFantasia" value="${pj.nomeFantasia}">
        </div>
        <div class="form-group col-md-5">
            <label for="razaoSocial">Razão Social</label>
            <input type="text" class="form-control form-control-sm" id="razaoSocial"  name="razaoSocial" value="${pj.razaoSocial}">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-2">
            <label for="cnpj">CNPJ</label>
            <input type="text" class="form-control form-control-sm" id="cnpj" name="cnpj" value="${pj.cnpj}">
        </div>  
        <div class="form-group col-md-2">
            <label for="inscricaoEstadual">Inscrição Estadual</label>
            <input type="text" class="form-control form-control-sm" id="inscricaoEstadual"  name="inscricaoEstadual" value="${pj.inscricaoEstadual}">
        </div>
        <div class="form-group col-md-2">
            <label for="inscricaoMunicipal">Inscrição Municipal</label>
            <input type="text" class="form-control form-control-sm" id="inscricaoMunicipal"  name="inscricaoMunicipal" value="${pj.inscricaoMunicipal}">
        </div>
    </div>
    <input class="btn btn-success" type="submit" value="Salvar">
    <input class="btn btn-light" type="reset" value="Limpar">



</form>


<table class="table table-hover">
    <thead>
        <tr>
            <th>Código</th>
            <th>Nome Fantasia</th>
            <th>Razão Social</th>
            <th>CNPJ</th>
            <th>Inscrição Estadual</th>
            <th>Inscrição Municipal</th>
            <th>Edição</th> 
        </tr>
    </thead>
    <tbody>
        <c:forEach var="pj" items="${pjs}">
            <tr>
                <td>${pj.codigo}</td>
                <td>${pj.nomeFantasia}</td>
                <td>${pj.razaoSocial}</td>
                <td>${pj.cnpj}</td>
                <td>${pj.inscricaoEstadual}</td>
                <td>${pj.inscricaoMunicipal}</td>
                <td><a class="btn btn-primary" href="PessoaJuridicaServlet?acao=editar&codigo=${pj.codigo}">Editar</a>
                    <a class="btn btn-danger" href="PessoaJuridicaServlet?acao=excluir&codigo=${pj.codigo}">Excluir</a>
                    <a class="btn btn-light" href="PessoaJuridicaServlet?acao=informacoes&codigo=${pj.codigo}">Informações</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>




<jsp:include page="/footer"/>
