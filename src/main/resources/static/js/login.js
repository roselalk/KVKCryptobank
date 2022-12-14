let buttonLogin = document.querySelector("#buttonLogin");
let buttonRegister = document.querySelector("#buttonRegister");
let passwordVisibility = document.querySelector("#passwordVisibility");

passwordVisibility.addEventListener("click", () => {
    togglePasswordVisibility();
});
buttonRegister.addEventListener("click", () => {
    location.href = "../html/registratie.html";
});
buttonLogin.addEventListener("click", () => {
    login();
});

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

function login() {
    let emailInput = document.querySelector('#email').value;
    let passwordInput = document.querySelector('#password').value;

    let raw = JSON.stringify({
        "email": emailInput,
        "password": passwordInput
    });

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'

    };fetch("http://localhost:8080/login", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
    location.href = "../html/market.html";
}

function togglePasswordVisibility(){
    let x = document.querySelector("#password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
