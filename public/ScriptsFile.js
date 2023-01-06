let URL='http://localhost:8080';//http://localhost:8080  http://147.83.7.206:8080
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
            localStorage.setItem("activeUser", username);
            localStorage.setItem("id", result.id);
            localStorage.setItem("coins", result.coins);
            window.location.href = "Main.html";
        },
        error: function(error){
            if (username == "" || password == "")
                alert("You left something blank. Please try again!");
            else{
                alert("Wrong username or password. Please try again!");
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
        url: URL + '/dsaApp/user/updateUser/' + oldUsername +'/'+ newUsername,
        data: JSON.stringify({"oldUsername": oldUsername, "newUsername": newUsername}),
        dataType: 'json',
        success: function (result) {
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
        url: URL + '/dsaApp/user/updatePassword',
        data: JSON.stringify({"id": id, "oldPassword": oldPassword, "newPassword": newPassword}),
        dataType: 'json',
        success: function (result) {
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
function getItemsList(){
    $.ajax({
        type: 'GET',
        url: "/dsaApp/item/itemsList",
        dataType: 'json',
        success:function (result) {
            let content = "<table><tr><th></th><th>Name</th><th>Description</th><th>Price</th><th>image</th></tr>";
            for (let i = 0; i < result.length; i++) {
               //content += '<tr><td>' + '<img src ="images/' + result[i].image + '.png" alt="Image" style="width:30% height:30%">' +
                $("#itemsTable").append(
                    "<tr> <td>" + result[i].name +
                    "</td> <td>" + result[i].description +
                    "</td> <td>" + result[i].price +
                   "</td><td>" +  '<img src ="images/' + result[i].image + '.png" alt="Image" style="width:30% height:30%">' + "</td></tr>" +
                    '</td><td>' + '<button type = "button" id ="' + result[i].name + '"' + '"onclick="PurchaseItem(this.id)">Purchase</button>' + '</td> </tr>');

            }},

        error: function (error) {
            alert("Unable to get Shop data." + error);
            console.log(error);
            window.location.href = "Main.html";
        }
    })
}

//function PurchaseItem (item){};
function getRanking(){
    $.ajax({
        type: 'GET',
        url: "/dsaApp/match/ranking",
        dataType: 'json',
        success:function (result) {
            //let content = "<table><tr><th></th><th>Name</th><th>Description</th><th>Price</th><th>image</th></tr>";
            for (let i = 0; i < result.length; i++) {
                //content += '<tr><td>' + '<img src ="images/' + result[i].image + '.png" alt="Image" style="width:30% height:30%">' +
                $("#rankingTable").append(
                    "<tr> <td>" + result[i].UserName +
                    "</td> <td>" + result[i].points + '</td> </tr>');
                   /* "</td> <td>" + result[i].price +
                    "</td><td>" +  '<img src ="images/' + result[i].image + '.png" alt="Image" style="width:30% height:30%">' + "</td></tr>" +
                    '</td><td>' + '<button type = "button" id ="' + result[i].name + '"' + '"onclick="PurchaseItem(this.id)">Purchase</button>' + '</td> </tr>');*/

            }},

        error: function (error) {
            alert("Unable to get Shop data." + error);
            console.log(error);
            window.location.href = "Main.html";
        }
    })
}



