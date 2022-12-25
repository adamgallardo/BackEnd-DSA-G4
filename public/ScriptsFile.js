let URL='http://147.83.7.206:8080';//http://localhost:8080  http://147.83.7.206:8080
function signup() {

    var email = $('#email').val();
    var username = $('#username').val();
    var password = $('#password').val();
    var password2 = $('#password2').val();

    if (password == password2) {

        $.ajax({
            contentType: "application/json",
            type: 'POST',
            url: URL + '/dsaApp/user/signUp',
            data: JSON.stringify({"email": email, "username": username, "password": password}),
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
    else alert("Check the password.");
}
function login() {
    var username = $('#username').val();
    var password = $('#password').val();
    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: URL+'/dsaApp/user/logIn',
        data: JSON.stringify({ "username": username, "password": password }),
        dataType: 'json',
        success: function(result){
            console.log(result);
            alert("Inicio de sesión correcto");
            console.log(result);
            localStorage.setItem("activeUser", username);
            localStorage.setItem("id", result.id);
            localStorage.setItem("coins", result.coins);
            window.location.href = "Main.html";
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
function eradicate() {
    var id = localStorage.getItem("id");
    if(confirm("Are you sure? There is no way back!")==true) {
        $.ajax({
            contentType: "application/json",
            type: 'DELETE',
            url: URL + '/dsaApp/user/delete/' + id,
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("id", null);
                window.location.href = "index.html";
            }
        });
    }
}
function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}
function updateUsername() {
    var oldUsername = $('#oldUsername').val();
    var newUsername = $('#newUsername').val();
    $.ajax({
        contentType: "application/json",
        type: 'PUT',
        url: URL + '/dsaApp/user/updateUser/' + oldUsername + newUsername,
        data: JSON.stringify({"oldUsername": oldUsername, "newUsername": newUsername}),
        dataType: 'json',
        success: function (result) {
            alert("Done!");
            localStorage.setItem("activeUser", newUsername);
            window.location.href = "Main.html";
        },
        error: function (error) {
            if (oldUsername == "" || newUsername == "")
                alert("You left something blank. Please try again!");
            else{
                alert("Username already in use. Please try again!");
            }
        }
    });
}
function updatePassword(){
    var oldPassword = $('#oldPassword').val();
    var newPassword = $('#newPassword').val();
    var id = localStorage.getItem("id");
    $.ajax({
        contentType: "application/json",
        type: 'PUT',
        url: URL + '/dsaApp/user/updatePassword/' + id + oldPassword + newPassword,
        data: JSON.stringify({"id": id, "oldPassword": oldPassword, "newPassword": newPassword}),
        dataType: 'json',
        success: function (result) {
            alert("Done!");
            window.location.href = "Main.html";
        },
        error: function (error) {
            if (oldPassword == "" || newPassword == "")
                alert("You left something blank. Please try again!");
            else{
                alert("Wrong password");
            }
        }
    });
}
