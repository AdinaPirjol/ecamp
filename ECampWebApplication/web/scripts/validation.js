function validate() {

    var username = document.forms["registerForm"]["username"];
    var fname = document.forms["registerForm"]["firstName"];
    var lname = document.forms["registerForm"]["lastName"];
    var email = document.forms["registerForm"]["email"];
    var password = document.forms["registerForm"]["passwordRegister"];
    var repassword = document.forms["registerForm"]["repeatPasswordRegister"];
    var address = document.forms["registerForm"]["address"];
    var phone = document.forms["registerForm"]["phone"];


    var userNameError = document.getElementById("username-error");
    var firstNameError = document.getElementById("firstname-error");
    var lastNameError = document.getElementById("lastname-error");
    var emailError = document.getElementById("email-error");
    var passwordError = document.getElementById("pass-error");
    var repeatPasswordError = document.getElementById("repeatpass-error");
    var addressError = document.getElementById("address-error");
    var phoneError = document.getElementById("phone-error")

    if (fname.value === null || fname.value === "") {
        fname.parentNode.className = "form-element error-element";
        firstNameError.style.display = "inline";
        firstNameError.innerHTML = "Please enter first name";
        return false;
    }
    else {
        fname.parentNode.className = "form-element";
        firstNameError.innerHTML = "";
    }

    if (lname.value === null || lname.value === "") {
        lname.parentNode.className = "form-element error-element";
        lastNameError.style.display = "inline";
        lastNameError.innerHTML = "Please enter last name";
        return false;
    }
    else {
        lname.parentNode.className = "form-element";
        lastNameError.innerHTML = "";
    }

if (username.value === null || username.value === "") {
        username.parentNode.className = "form-element error-element";
        userNameError.style.display = "inline";
        userNameError.innerHTML = "Please enter a username";
        return false;
    }
    else {
        username.parentNode.className = "form-element";
        userNameError.innerHTML = "";
    }
    
    if (email.value === null || email.value === "") {
        email.parentNode.className = "form-element error-element";
        emailError.style.display = "inline";
        emailError.innerHTML = "Please enter email";
        return false;
    }
    else {
        email.parentNode.className = "form-element";
        emailError.innerHTML = "";
    }

    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!(email.value.match(re))) {
        email.parentNode.className = "form-element error-element";
        emailError.style.display = "inline";
        emailError.innerHTML = "Please enter correct mail format";
        return false;
    }
    else {
        email.parentNode.className = "form-element";
        emailError.innerHTML = "";
    }

    if (password.value === null || password.value === "") {
        password.parentNode.className = "form-element error-element";
        passwordError.style.display = "inline";
        passwordError.innerHTML = "Please enter password";
        return false;
    }
    else {
        password.parentNode.className = "form-element";
        passwordError.innerHTML = "";
    }

    if (password.value.length < 8) {
        password.parentNode.className = "form-element error-element";
        passwordError.style.display = "inline";
        passwordError.innerHTML = "Must have minimum 8 characters";
        return false;
    }
    else {
        password.parentNode.className = "form-element";
        passwordError.innerHTML = "";
    }



    if (repassword.value === null || repassword.value === "") {
        repassword.parentNode.className = "form-element error-element";
        repeatPasswordError.style.display = "inline";
        repeatPasswordError.innerHTML = "Please re-enter password";
        return false;
    }
    else {
        repassword.parentNode.className = "form-element";
        repeatPasswordError.innerHTML = "";
    }

    if (repassword.value !== password.value) {
        repassword.parentNode.className = "form-element error-element";
        repeatPasswordError.style.display = "inline";
        repeatPasswordError.innerHTML = "Password mismatch";
        return false;
    }

    if(address.value === null || address.value === ""){
        address.parentNode.className = "form-element error-element";
        addressError.style.display="inline";
        addressError.innerHTML="Please provide a valid address";
        return false;
    }else{
        address.parentNode.className = "form-element";
        addressError.innerHTML = "";
    }

    if (phone.value === null || phone.value === "") {
        phone.parentNode.className = "form-element error-element";
        phoneError.style.display = "inline";
        phoneError.innerHTML = "Please provide a valid phone number";
        return false;
    } else {
        phone.parentNode.className = "form-element";
        phoneError.innerHTML = "";
    }
    return true;
}