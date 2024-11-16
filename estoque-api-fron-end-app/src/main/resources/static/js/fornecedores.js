document.addEventListener("DOMContentLoaded", function() {
    axios.get('http://localhost:8081/fornecedores')  // URL da sua API
        .then(function (response) {
			console.log(response.data);  // Verifique o que está sendo retornado
            const fornecedores = response.data;
            let tableBody = '';
            fornecedores.forEach(fornecedor => {
                tableBody += `
                    <tr>
                        <td>${fornecedor.id}</td>
                        <td>${fornecedor.nome}</td>
                        <td>${fornecedor.email}</td>
                        <td>${fornecedor.fone}</td>
                        <td>
                            <a href="edit.html?id=${fornecedor.id}" class="btn btn-warning btn-sm">Editar</a>
                            <button onclick="deleteFornecedor(${fornecedor.id})" class="btn btn-danger btn-sm">Excluir</button>
                        </td>
                    </tr>
                `;
            });
            document.getElementById('fornecedores-table').innerHTML = tableBody;
        })
        .catch(function (error) {
            console.error('Erro ao carregar fornecedores', error);
        });
});

function deleteFornecedor(id) {
    if (confirm('Você tem certeza que deseja excluir este fornecedor?')) {
        axios.delete(`http://localhost:8081/fornecedores/${id}`)
            .then(response => {
                alert('Fornecedor excluído com sucesso');
                window.location.reload();
            })
            .catch(error => {
                console.error('Erro ao excluir fornecedor', error);
            });
    }
}
