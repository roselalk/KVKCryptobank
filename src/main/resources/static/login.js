let button = document.querySelector("#button");

button.addEventListener("click", () => {
    login();
});

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

function login() {
    let emailInput = document.querySelector('#email').value;
    let passwordInput = document.querySelector('#pw').value;

    let raw = JSON.stringify({
        "email": emailInput,
        "password": passwordInput
    });

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };
    console.log(raw)
    fetch("http://localhost:8080/login", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));


    console.log("email: " + emailInput)
    console.log("wachtwoord: " + passwordInput)


}
