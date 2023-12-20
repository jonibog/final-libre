const baseUrl = 'http://localhost:8081';
const users = [];
const divErrorMessage = document.getElementById('error_message');
const btnRegister = document.getElementById('btnRegister');
const usersTableBody = document.getElementById('users_table_body');

async function getAllUsers() {
    await fetch(`${baseUrl}/getAll`)
        .then(response => response.json())
        .then(data => {
            data.forEach(user => {
                users.push(user);
            });
        });
}

async function registerUser() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;
    const user = {
        user: username,
        password: password,
        role: role
    };
    await fetch(`${baseUrl}/registry`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
        if (response.ok) {
            window.location.href = 'admin.html';
        } else {
            divErrorMessage.innerHTML = 'Revisá los campos o que el usuario no exista.';
        }
    });
}

btnRegister.addEventListener('click', registerUser);

window.onload = async () => {
    await getAllUsers();
    users.forEach(user => {
        const tr = document.createElement('tr');
        const tdUsername = document.createElement('td');
        const tdPassword = document.createElement('td');
        const tdRole = document.createElement('td');
        tdUsername.innerHTML = user.user;
        tdRole.innerHTML = user.role;
        tdPassword.innerHTML = "********";
        tr.appendChild(tdUsername);
        tr.appendChild(tdPassword);
        tr.appendChild(tdRole);
        usersTableBody.appendChild(tr);
    });
}