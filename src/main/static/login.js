let button = document.querySelector("#button");
let emailInput = document.querySelector("#email").value;
let passwordInput = document.querySelector("#pw").value;

button.addEventListener("click", () => {
    login();
});

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

let raw = JSON.stringify({
    "email": `${emailInput}`,
    "password": `${passwordInput}`
});

function login() {


    // let data = {email: emailInput, password: passwordInput};

    // console.log(data);
    // console.log(JSON.stringify(data));

    console.log("pre-fetch")
    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };
    console.log("post-reqOpt")
    fetch("http://localhost:8080/login", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));


}
