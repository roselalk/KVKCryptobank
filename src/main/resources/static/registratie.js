let button = document.querySelector("#register");
let inputFields = document.querySelectorAll("input");

let red = "rgb(232, 159, 159)";
let blue = "rgb(238, 243, 245)";

let timer;
let doneTypingInterval = 1000;

// Check if all fields are filled
button.addEventListener("click", () => {
    checkInput("#fname");
    checkInput("#lname");
    checkInput("#bsn");
    checkInput("#date");
    checkInput("#zip");
    checkInput("#street");
    checkInput("#city");
    checkInput("#number");
    checkInput("#email");
    checkInput("#pw");
    checkBirthDate();
    checkEmail();
    checkNoNumbers();
});

for (let i = 0; i < inputFields.length; i++) {
    inputFields[i].addEventListener("mousedown", () => {
        inputFields[i].style.backgroundColor = blue;
    })
}

function checkInput(id) {
    let input = document.querySelector(id);
    if (input.value == "") {
        input.style.backgroundColor = red;
        return false;
    } else {
        return true;
    }
}

//Check reasonable birthdate (under 115, over 18)
function checkBirthDate() {
    let date = document.querySelector("#date");
    let birthDate = new Date(date.value);
    let maxDate = new Date("1907-01-01");
    let minDate = new Date("2004-01-01");

    if (birthDate < maxDate || birthDate > minDate) {
        date.style.backgroundColor = red;
    }
}

//Check email
function checkEmail() {
    let email = document.querySelector("#email");
    if (!validateEmail(email.value)) {
        email.style.backgroundColor = red;
    }
}

function validateEmail(emailInput) {
    return emailInput.match(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
}

//Check dat voornaam, tussenvoegsel en achternaam geen getallen bevatten
function checkNoNumbers() {
    let fname = document.querySelector("#fname");
    let prefix = document.querySelector("#prefix");
    let lname = document.querySelector("#lname");

    if (!/^[a-zA-Z]*$/g.test(fname.value)) {
        fname.style.backgroundColor = red;
    }
    if (!/^[a-zA-Z]*$/g.test(prefix.value)) {
        prefix.style.backgroundColor = red;
    }
    if (!/^[a-zA-Z]*$/g.test(lname.value)) {
        lname.style.backgroundColor = red;
    }
}

//Check BSN
let bsnField = document.querySelector("#bsn");

bsnField.addEventListener("keyup", () => {
    clearTimeout(timer);
    if (bsnField.value) {
        console.log(bsnField.value);
        timer = setTimeout(checkBSN, doneTypingInterval);
    }
});

function checkBSN() {
    let bsn = bsnField.value;
    if (bsn.length < 8 || bsn.length > 9) {
        bsnField.style.backgroundColor = "orange";
        return false;
    }

    let sum = 0;
    let multiplier = 10;
    for (let i = 0; i < (bsn.length - 1); i++) {
        sum += bsn[i] * multiplier;
        multiplier--
    }
    sum += (bsn.slice(-1) * 1)

    if (sum % 11 != 0) {
        bsnField.style.backgroundColor = red;
        return false;
    } else {
        bsnField.style.backgroundColor = blue;
    }
}

// Auto-fill address details
let nummer = document.querySelector("#number");
let zip = document.querySelector("#zip");

nummer.addEventListener("keyup", () => {
    if (zip.value != "") {
        clearTimeout(timer);
        if (nummer.value) {
            timer = setTimeout(loadAddress, doneTypingInterval);
        }
    }
});

function loadAddress() {
    let postcode = document.querySelector("#zip").value;
    let huisnummer = document.querySelector("#number").value;
    let data = { postcode: postcode, number: huisnummer };

    console.log("Voor fetch data:" + data);
    console.log("Voor fetch JSON data:" + JSON.stringify(data));

    fetch(
        `https://postcode.tech/api/v1/postcode?postcode=${postcode}&number=${huisnummer}`,
        {
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                Authorization: "Bearer 1d3dc31e-842e-4d3d-b2c8-bcd127cb0232",
            },
        }
    )
        .then((response) => response.json())
        .then((json) => {
            JSON.stringify(json);
            console.log("Na fetch json: " + json);
            renderData(json.street, json.city);
        })
        .catch((error) => {
            console.error("Foutje", error);
        });
}

function renderData(street, city) {
    let streetField = document.querySelector("#street");
    let cityField = document.querySelector("#city");
    if (street != undefined && city != undefined) {
        streetField.value = street;
        cityField.value = city;
    } else {
        nummer.style.backgroundColor = "rgb(232, 159, 159)";
        zip.style.backgroundColor = "rgb(232, 159, 159)";
    }
}
