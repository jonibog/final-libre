const btnLogin = document.getElementById('btnLogin');
const inputUsername = document.getElementById('input_username');
const inputPassword = document.getElementById('input_password');
const divErrorMessage = document.getElementById('error_message');
const baseUrl = 'http://localhost:8081';
const users = [];

btnLogin.addEventListener('click', async () => {
    const response = await fetch(`${baseUrl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            user: inputUsername.value,
            password: inputPassword.value
        })
    });

    if (response.ok) {
        await getAllUsers();
        users.forEach(user => {
            if (user.user === inputUsername.value) {
                if (user.role === 'user')
                    window.location.href = 'inicio.html';
                else
                    window.location.href = 'admin.html';
            }
        });
    }
});

async function getAllUsers() {
    await fetch(`${baseUrl}/getAll`)
        .then(response => response.json())
        .then(data => {
            data.forEach(user => {
                users.push(user);
            });
        });
}