const params = new URLSearchParams(window.location.search);
const fornecedorId = params.get('id');
console.log('API_BASE_URL edit.js:', API_BASE_URL);
if (fornecedorId) {
    document.getElementById('form-title').textContent = 'Editar Fornecedor';
    //axios.get(`http://localhost:8081/fornecedores/${fornecedorId}`)
    console.log('API_BASE_URL:', API_BASE_URL);
    axios.get(`${API_BASE_URL}/fornecedores/${fornecedorId}`)
        .then(response => {
            document.getElementById('nome').value = response.data.nome;
            document.getElementById('email').value = response.data.email;
            document.getElementById('fone').value = response.data.fone;
        })
        .catch(error => console.error('Erro ao carregar fornecedor para edição', error));
}

document.getElementById('fornecedor-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const fone = document.getElementById('fone').value;

    const fornecedorData = { nome, email, fone };

    if (fornecedorId) {
        //axios.put(`http://localhost:8081/fornecedores/${fornecedorId}`, fornecedorData)
        axios.put(`${API_BASE_URL}/fornecedores/${fornecedorId}`, fornecedorData)
            .then(response => {
                alert('Fornecedor atualizado com sucesso');
                window.location.href = 'fornecedores.html';
            })
            .catch(error => console.error('Erro ao atualizar fornecedor', error));
    } else {
        //axios.post('http://localhost:8081/fornecedores', fornecedorData)
        axios.post(`${API_BASE_URL}/fornecedores`, fornecedorData)
            .then(response => {
                alert('Fornecedor criado com sucesso');
                window.location.href = 'fornecedores.html';
            })
            .catch(error => console.error('Erro ao criar fornecedor', error));
    }
});
