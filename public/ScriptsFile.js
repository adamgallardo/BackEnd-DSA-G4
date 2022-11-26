let URL='http://localhost:8080';
function signup() {

    var email = $('#email').val();
    var username = $('#username').val();
    var password = $('#password').val();

    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: URL+'/dsaApp/user/signUp',
        data: JSON.stringify({ "email": email,"username": username, "password": password}),
        dataType: 'json',
        success: function (result) {
            alert("Sign up completed. Nice to meet you, " + username);
            localStorage.setItem("activeUser", username);
        },
        error: function (error) {
            console.log(error);
            if (email == null || username == null || password == null)
                alert("You left something blank. Please try again!");
            else
                alert("Username or email already in use. Please try again!");
        }
    });
}
function login() {
    var username = $('#username').val();
    var password = $('#password').val();
    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: '/dsaApp/user/logIn',
        data: JSON.stringify({ "username": username, "password": password }),
        dataType: 'json',
        success: function(result){
            alert("Inicio de sesión correcto");
        },
        error: function(error){
            if (username == "" || password == "")
                alert("No has rellenado algun campo");
            else{
                alert("Error");
            }
        }
    });
}