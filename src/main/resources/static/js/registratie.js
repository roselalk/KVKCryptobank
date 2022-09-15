let buttonRegister = document.querySelector("#button");

buttonRegister.addEventListener("click", () => {
    register();
});

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

function register() {
    let emailInput = document.querySelector('#email').value;
    let passwordInput = document.querySelector('#password').value;
    let firstNameInput = document.querySelector('#firstName').value;
    let prefixInput = document.querySelector('#prefix').value;
    let nameInput = document.querySelector('#name').value;
    let BSNInput = document.querySelector('#BSN').value;
    let dateOfBirthInput = document.querySelector('#dateOfBirth').value;
    let streetInput = document.querySelector('#street').value;
    let houseNumberInput = document.querySelector('#houseNumber').value;
    let zipCodeInput = document.querySelector('#zipCode').value;
    let cityInput = document.querySelector('#city').value;

    let raw = JSON.stringify({
        "email": emailInput,
        "password": passwordInput,
        "firstName" : firstNameInput ,
        "prefix" : prefixInput,
        "name" : nameInput,
        "BSN" : BSNInput,
        "dateOfBirth" : dateOfBirthInput,
        "street" : streetInput,
        "houseNumber" : houseNumberInput,
        "zipCode" : zipCodeInput,
        "city" : cityInput,
        // TODO: isActive is set to "1" by default, is this the proper way of handling this?
        "isActive": 1
    });
    console.log(raw)

    let requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };
    console.log(raw)
    fetch("http://localhost:8080/traders/register", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
    console.log(raw)


}
